/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roosa
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void saldoTulostuu() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void tulostaaMyydytEdukkaat() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void tulostaaMyydytMaukkaat() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void käteismaksuSaldoNouseeJosMaksuRiittääEdullinen() {
        paate.syoEdullisesti(5000);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void käteismaksuOikeaVaihtorahaJosMaksuRiittääEdullinen() {
        assertEquals(60, paate.syoEdullisesti(300));
    }
    
    @Test
    public void käteismaksuMyytyjenLounaidenMääräKasvaaJosMaksuRiittääEdullinen() {
        paate.syoEdullisesti(500);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void käteismaksuSaldoEiNouseJosMaksuEiRiitäEdukas() {
        paate.syoEdullisesti(10);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void käteismaksuOikeaVaihtorahaJosMaksuEiRiitäEdukas() {
        assertEquals(10, paate.syoEdullisesti(10));
    }
    
    @Test
    public void käteismaksuMyytyjenLounaidenMääräEiKasvaJosMaksuEiRiitäEdukas() {
        paate.syoEdullisesti(10);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void käteismaksuSaldoNouseeJosMaksuRiittääMaukas() {
        paate.syoMaukkaasti(5000);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void käteismaksuOikeaVaihtorahaJosMaksuRiittääMaukas() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void käteismaksuMyytyjenLounaidenMääräKasvaaJosMaksuRiittääMaukas() {
        paate.syoMaukkaasti(500);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void käteismaksuSaldoEiNouseJosMaksuEiRiitäMaukas() {
        paate.syoMaukkaasti(10);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void käteismaksuOikeaVaihtorahaJosMaksuEiRiitäMaukas() {
        assertEquals(10, paate.syoMaukkaasti(10));
    }
    
    @Test
    public void käteismaksuMyytyjenLounaidenMääräEiKasvaJosMaksuEiRiitäMaukas() {
        paate.syoMaukkaasti(10);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    // KORTTIOSTOKSET
    
    @Test
    public void korttiostoSummaVeloitetaanJosTarpeeksiSaldoaEdukas() {
        paate.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void korttiostoPalauttaaTrueJosTarpeeksiSaldoaEdukas() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoKasvattaaMyytyjäLounaitaJosTarpeeksiSaldoaEdukas() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiMuutaKortinSaldoaJosEiTarpeeksiSaldoaEdukas() {
        kortti = new Maksukortti(2);
        paate.syoEdullisesti(kortti);
        assertEquals(2, kortti.saldo());
    }
    
    @Test
    public void korttiostoMyytyjenLounaidenMääräEiMuutuJosEiTarpeeksiSaldoaEdukas() {
        kortti = new Maksukortti(2);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoPalauttaaFalseJosEiTarpeeksiSaldoaEdukas() {
        kortti = new Maksukortti(2);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void korttiostoPaatteenSaldoEiMuutuJosTarpeeksiSaldoaEdukas() {
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void korttiostoPaatteenSaldoEiMuutuJosEiTarpeeksiSaldoaEdukas() {
        kortti = new Maksukortti(1);
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void korttiostoSummaVeloitetaanJosTarpeeksiSaldoaMau() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void korttiostoPalauttaaTrueJosTarpeeksiSaldoaMaukas() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void korttiostoKasvattaaMyytyjäLounaitaJosTarpeeksiSaldoaMaukas() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEiMuutaKortinSaldoaJosEiTarpeeksiSaldoaMaukas() {
        kortti = new Maksukortti(2);
        paate.syoMaukkaasti(kortti);
        assertEquals(2, kortti.saldo());
    }
    
    @Test
    public void korttiostoMyytyjenLounaidenMääräEiMuutuJosEiTarpeeksiSaldoaMaukas() {
        kortti = new Maksukortti(2);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoPalauttaaFalseJosEiTarpeeksiSaldoaMaukas() {
        kortti = new Maksukortti(2);
        assertEquals(false, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void korttiostoPaatteenSaldoEiMuutuJosTarpeeksiSaldoaMaukas() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void korttiostoPaatteenSaldoEiMuutuJosEiTarpeeksiSaldoaMaukas() {
        kortti = new Maksukortti(1);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    //LataaRahaa
    
    @Test
    public void rahanLatausMuuttaaPaatteenSaldoa() {
        paate.lataaRahaaKortille(kortti, 50);
        assertEquals(100050, paate.kassassaRahaa());
    }
    
    @Test
    public void rahanLatausMuuttaaKortinSaldoa() {
        paate.lataaRahaaKortille(kortti, 50);
        assertEquals(550, kortti.saldo());
    }
    
    @Test
    public void rahanLatausNegatiivinenEiMuutaPaatteenSaldoa() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahanLatausNegatiivinenEiMuutaKortinSaldoa() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(500, kortti.saldo());
    }
}

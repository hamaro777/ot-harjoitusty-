package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikeinLuodussaKortissa() {
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void saldoKasvaaOikein() {
        
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void saldoVäheneeOikein() {
        
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    @Test
    public void saldoEiVähennäNollasta() {
        
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());
        
    }
    
    @Test
    public void rahanOttaminenTulostaaTrueJosRahaa() {
        
        assertEquals(true, kortti.otaRahaa(5));
        
    }
    
    @Test
    public void rahanOttaminenTulostaaFalseJosEiRahaa() {
        
        assertEquals(false, kortti.otaRahaa(50));
        
    }
    
    @Test
    public void saldoTulostuuOikein() {
        
        assertEquals(10, kortti.saldo());
        
    }
}

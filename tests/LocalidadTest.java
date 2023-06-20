package tests;

import static org.junit.Assert.assertFalse;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import grafos.Localidad;


public class LocalidadTest {
    
    @Test
    public void dosLocalidasIguales(){
        Localidad localidad1 = new Localidad("Cordoba","Cordoba",-34,-70);
        Localidad localidad2 = new Localidad("Cordoba","Cordoba",-34,-70);
        
        assertTrue(localidad1.equals(localidad2));
    }
    @Test
    public void dosLocalidasDiferentes(){
        Localidad localidad1 = new Localidad("Formosa","Formosa",-26,-58);
        Localidad localidad2 = new Localidad("Cordoba","Cordoba",-34,-70);
        
        assertFalse(localidad1.equals(localidad2));
    }
    
}
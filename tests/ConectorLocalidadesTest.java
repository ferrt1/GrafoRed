package tests;


import org.junit.Test;

import grafos.ConectorLocalidades;
import grafos.Localidad;

public class ConectorLocalidadesTest {
    
    ConectorLocalidades conector;
    Localidad Formosa;
    Localidad Cordoba;


    @Test (expected = IllegalArgumentException.class)
    public void valorNullLocalidad1() {
        conector = new ConectorLocalidades(null,Formosa,230,20);
    }
    
  
    @Test (expected = IllegalArgumentException.class)
    public void valorNullLocalidad2() {
        conector = new ConectorLocalidades(Cordoba,null,230,20);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void valorNullLocalidadEnAmbas() {
        conector = new ConectorLocalidades(null,null,230,20);
    
    }
}

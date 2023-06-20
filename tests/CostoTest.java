package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import grafos.*;

public class CostoTest {

    @Test
    public void testCalcularDistancia() {
        Localidad origen = new Localidad("Buenos Aires", "Buenos Aires", -34.6037f, -58.3816f);
        Localidad destino = new Localidad("Córdoba", "Córdoba", -31.4201f, -64.1888f);

        double distancia = Costo.calcularDistancia(origen, destino);

        assertTrue(distancia > 600 && distancia < 700);
    }
    
    @Test
    public void testObtenerCostoVariasLocalidades() {
        Localidad origen = new Localidad("Achiras", "Cordoba", -33.16667f, -65.0f);
        Localidad destino = new Localidad("Alcaraz", "Entre Rios", -31.46667f, -59.6f);

        double costo = Costo.obtenerCosto(origen, destino, 0.5, 0.2, 100);
        System.out.println("Costo de Achiras a Alcaraz: " + costo);

        destino = new Localidad("A de la Cruz", "Buenos Aires", -34.33333f, -59.11667f);
        costo = Costo.obtenerCosto(origen, destino, 0.5, 0.2, 100);
        System.out.println("Costo de Achiras a A de la Cruz: " + costo);

        destino = new Localidad("Abramo", "La Pampa", -37.88333f, -63.85f);
        costo = Costo.obtenerCosto(origen, destino, 0.5, 0.2, 100);
        System.out.println("Costo de Achiras a Abramo: " + costo);

        destino = new Localidad("Achico", "Neuquen", -40.33333f, -70.33333f);
        costo = Costo.obtenerCosto(origen, destino, 0.5, 0.2, 100);
        System.out.println("Costo de Achiras a Achico: " + costo);
    }
}

package tests;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import grafos.*;

public class AGMTest {

    private Grafo grafo;
    private Localidad buenosAires;
    private Localidad cordoba;
    private Localidad rosario;
    private Localidad mendoza;

    @Before
    public void setUp() {
        buenosAires = new Localidad("Buenos Aires", "Buenos Aires", -34.6037f, -58.3816f);
        cordoba = new Localidad("Córdoba", "Córdoba", -31.4201f, -64.1888f);
        rosario = new Localidad("Rosario", "Santa Fe", -32.9468f, -60.6393f);
        mendoza = new Localidad("Mendoza", "Mendoza", -32.8908f, -68.8272f);

        grafo = new Grafo(1.0, 0.1, 0.2, buenosAires, cordoba, rosario, mendoza);
        grafo.agregarArista(buenosAires, cordoba);
        grafo.agregarArista(buenosAires, rosario);
        grafo.agregarArista(buenosAires, mendoza);
        grafo.agregarArista(cordoba, rosario);
        grafo.agregarArista(cordoba, mendoza);
        grafo.agregarArista(rosario, mendoza);
    }

    @Test
    public void testGenerarPorPrim() {
        Grafo agm = AGM.generarPorPrim(grafo);
        
        assertTrue(agm.existeArista(buenosAires, rosario));
        assertTrue(agm.existeArista(mendoza, cordoba));
        assertTrue(agm.existeArista(rosario, buenosAires));
        assertTrue(agm.existeArista(rosario, cordoba));
        assertTrue(agm.existeArista(cordoba, mendoza));
        assertTrue(agm.existeArista(cordoba, rosario));
        assertEquals(4, agm.tamanio());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerarPorPrimGrafoNulo() {
        Grafo grafoNulo = null;
        AGM.generarPorPrim(grafoNulo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerarPorPrimGrafoNoConexo() {
        Grafo grafoNoConexo = new Grafo(1.0, 0.1, 0.2, buenosAires, mendoza);
        AGM.generarPorPrim(grafoNoConexo);
    }
}
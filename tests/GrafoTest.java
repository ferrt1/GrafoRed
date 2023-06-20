package tests;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

import grafos.*;

public class GrafoTest {

    private Grafo grafo;
    private LinkedList<Localidad> localidades;

    @Before
    public void setUp() {
        localidades = new LinkedList<>();
        localidades.add(new Localidad("Buenos Aires", "Buenos Aires", -34.6037f, -58.3816f));
        localidades.add(new Localidad("Córdoba", "Córdoba", -31.4201f, -64.1888f));
        localidades.add(new Localidad("Rosario", "Santa Fe", -32.9468f, -60.6393f));
        localidades.add(new Localidad("Mendoza", "Mendoza", -32.8908f, -68.8272f));

        grafo = new Grafo(localidades, 1.0, 0.1, 0.2);
        grafo.agregarArista(localidades.get(0), localidades.get(1));
        grafo.agregarArista(localidades.get(0), localidades.get(2));
        grafo.agregarArista(localidades.get(1), localidades.get(3));
    }

    @Test
    public void testAgregarVertice() {
        Localidad laPlata = new Localidad("La Plata", "Buenos Aires", -34.9228f, -57.9556f);
        grafo.agregarVertice(laPlata);

        assertEquals(5, grafo.tamanio());
    }

    @Test
    public void testAgregarArista() {
        Localidad sanJuan = new Localidad("San Juan", "San Juan", -31.5375f, -68.5364f);
        grafo.agregarVertice(sanJuan);
        grafo.agregarArista(localidades.get(1), sanJuan);

        assertTrue(grafo.existeArista(localidades.get(1), sanJuan));
        assertFalse(grafo.existeArista(localidades.get(2), sanJuan));
    }

    @Test
    public void testVecinos() {
        LinkedList<Localidad> vecinos = grafo.vecinos(localidades.get(0));

        assertEquals(3, vecinos.size());
        assertTrue(vecinos.contains(localidades.get(1)));
        assertTrue(vecinos.contains(localidades.get(2)));
    }

    @Test
    public void testTamanio() {
        assertEquals(4, grafo.tamanio());
    }
}
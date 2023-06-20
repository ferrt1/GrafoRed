package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import grafos.BFS;
import grafos.Grafo;
import grafos.Localidad;

public class BFSTest {

    private Grafo grafo;
    private Localidad buenosAires;
    private Localidad cordoba;
    private Localidad rosario;
    private Localidad mendoza;

    @Before
    public void setUp() {
        // Crear el grafo de ejemplo
        buenosAires = new Localidad("Buenos Aires", "Buenos Aires", -34.6037f, -58.3816f);
        cordoba = new Localidad("Córdoba", "Córdoba", -31.4201f, -64.1888f);
        rosario = new Localidad("Rosario", "Santa Fe", -32.9468f, -60.6393f);
        mendoza = new Localidad("Mendoza", "Mendoza", -32.8908f, -68.8272f);

        grafo = new Grafo(1.0, 0.1, 0.2, buenosAires, cordoba, rosario, mendoza);
        grafo.agregarArista(buenosAires, cordoba);
        grafo.agregarArista(buenosAires, rosario);
        grafo.agregarArista(cordoba, mendoza);
    }

    @Test
    public void testEsConexo() {
        assertTrue(BFS.esConexo(grafo));
    }

    @Test
    public void testObtenerAlcanzables() {
        Set<Localidad> alcanzables = BFS.obtenerAlcanzables(grafo, buenosAires);

        Set<Localidad> expectedAlcanzables = new HashSet<>();
        expectedAlcanzables.add(buenosAires);
        expectedAlcanzables.add(cordoba);
        expectedAlcanzables.add(rosario);
        expectedAlcanzables.add(mendoza);
        assertEquals(expectedAlcanzables, alcanzables);
    }
    @Test
    public void testEsConexoGrafoNoConexo() {
        Grafo grafoNoConexo = new Grafo(1.0, 0.1, 0.2, buenosAires, cordoba, rosario, mendoza);
        assertFalse(BFS.esConexo(grafoNoConexo));
    }

    @Test (expected = NullPointerException.class)
    public void testObtenerAlcanzablesDesdeLocalidadNoExistente() {
        Localidad localidadNoExistente = new Localidad("San Juan", "San Juan", -31.5375f, -68.5364f);
        Set<Localidad> alcanzables = BFS.obtenerAlcanzables(grafo, localidadNoExistente);
        assertTrue(alcanzables.isEmpty());
    }

    @Test
    public void testObtenerAlcanzablesDesdeLocalidadSinVecinos() {
        Localidad localidadSinVecinos = new Localidad("Misiones", "Misiones", -26.8754f, -54.4583f);
        grafo.agregarVertice(localidadSinVecinos);
        Set<Localidad> alcanzables = BFS.obtenerAlcanzables(grafo, localidadSinVecinos);
        assertTrue(alcanzables.contains(localidadSinVecinos));
    }
}
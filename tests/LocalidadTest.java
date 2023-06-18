package tests;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import grafos.Localidad;


public class LocalidadTest {
	
	Localidad localidad;
	
	@Test
	public void dosLocalidadesSonIguales() {
		Localidad localidad_1 = new Localidad("Prueba", "Buenos Aires", 60, 40);
		Localidad localidad_2 = new Localidad("Prueba", "Buenos Aires", 60, 40);
		assertTrue(localidad_1.equals(localidad_2));
	}
	
	@Test
	public void dosLocalidadesNoSonIgualesNombre() {
		Localidad localidad_1 = new Localidad("Prueba", "Buenos Aires", 60, 40);
		Localidad localidad_2 = new Localidad("PruebaNOIGUAL", "Buenos Aires", 60, 40);
		assertFalse(localidad_1.equals(localidad_2));
	}
	
	@Test
	public void dosLocalidadesNoSonIgualesProvincia() {
		Localidad localidad_1 = new Localidad("Prueba", "Buenos Aires", 60, 40);
		Localidad localidad_2 = new Localidad("Prueba", "Buenos AiresNOIGUAL", 60, 40);
		assertFalse(localidad_1.equals(localidad_2));
	}
	
	@Test
	public void dosLocalidadesNoSonIgualesLatitud() {
		Localidad localidad_1 = new Localidad("Prueba", "Buenos Aires", 80, 40);
		Localidad localidad_2 = new Localidad("Prueba", "Buenos Aires", 60, 40);
		assertFalse(localidad_1.equals(localidad_2));
	}
	
	@Test
	public void dosLocalidadesNoSonIgualesLongitud() {
		Localidad localidad_1 = new Localidad("Prueba", "Buenos Aires", 60, 40);
		Localidad localidad_2 = new Localidad("Prueba", "Buenos Aires", 60, 80);
		assertFalse(localidad_1.equals(localidad_2));
	}
	
}

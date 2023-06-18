package tests;

import static org.junit.Assert.assertTrue;


import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Before;
import org.junit.Test;

import grafos.Localidad;


public class CableDeRedTest {
	private Localidad san_miguel;
	private Localidad jc_paz;
	private Localidad muniz;
	private Localidad morris;

	double costoPorKm = 20;
	double porcentajeSupera300Km = 0.2;
	double costoProvinciasDistintas = 20;
	
	@Before
	public void setup() {
		san_miguel = new Localidad("San Miguel", "Buenos Aires", 50, 30);

		jc_paz = new Localidad("Jc.Paz", "Buenos Aires", 50, 29);
		muniz = new Localidad("Mu�iz", "Buenos Aires", 10, 30);
		morris= new Localidad("Morris", "Cordoba", 10, 30);
	}

}

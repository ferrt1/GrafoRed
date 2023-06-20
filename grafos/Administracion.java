package grafos;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.Set;

public class Administracion {

	private Grafo grafoAGM;
	private Grafo grafoCompleto;
	private Set<Localidad> localidadesCargadas;
	private double costoPorKm= 0;
	private double porcentajeSupera300Km= 0;
	private double costoProvinciasDistintas = 0;

	public Administracion() {
	    localidadesCargadas = new HashSet<Localidad>();
	}
	
	public void finalizarCargaLocalidades() {
	    grafoCompleto = new Grafo(new LinkedList<Localidad>(getLocalidadesCargadas()), costoPorKm,
	                    porcentajeSupera300Km, costoProvinciasDistintas);
	}

	public boolean crearGrafoAGM() {
		if (grafoCompleto == null || grafoCompleto.tamanio() <= 0) {
			throw new RuntimeException("Debe agregar vertices para poder crear el AGM");
		}
		if (grafoCompleto != null) {
			grafoAGM = AGM.generarPorPrim(grafoCompleto);
			return true;
		}
		return false;
	}

	public Grafo obtenerGrafoAGM() {
		finalizarCargaLocalidades();
		crearGrafoAGM();
		return grafoAGM;
	}
	
	public Grafo obtenerGrafoCompleto() {
		return grafoCompleto;
	}


	public boolean cargarLocalidad(String nombre, String provincia, Float latitud, Float longitud) {
		Localidad localidad = new Localidad(nombre, provincia, latitud, longitud);
		return getLocalidadesCargadas().add(localidad);
	}
	
	public double obtenerCostoTotalAGM() {
		return grafoAGM.getCostoTotal();
	}

	public void setCostoPorKm(double precio) {
		if (precio <= 0) {
			throw new IllegalArgumentException("El costo por KILOMETRO no puede ser menor o igual a cero.");
		}
		costoPorKm = precio;
	}
	
	public void setPorcentajeSupera300Km(double precio) { 
		if (precio < 0 || precio > 2 ) {
			throw new IllegalArgumentException("El porcentaje agregado si supera 300KM no"
					+ " puede ser negativo o mayor a 2.");
		}
		porcentajeSupera300Km = precio;
	}
	
	public void setCostoProvinciaDistinta(double precio) {
		if (precio < 0) {
			throw new IllegalArgumentException(
					"El costo fijo agregado por provincias distintas no puede ser negativo.");
		}
		costoProvinciasDistintas = precio;
	}

	public Set<Localidad> getLocalidadesCargadas() {
		return localidadesCargadas;
	}
	
}

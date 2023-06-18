package grafos;

import java.util.HashSet;
import java.util.LinkedList;

import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Sistema {

	private Grafo grafoAGM;
	private Grafo grafoCompleto;
	private Set<Localidad> localidadesCargadas;
	private Set<ConectorLocalidades> conexionesEstablecidas;
	private double costoPorKm= 0;
	private double porcentajeSupera300Km= 0;
	private double costoProvinciasDistintas = 0;

	public Sistema() {
	    localidadesCargadas = new HashSet<Localidad>(); // Usamos un Set ya que asi no se agregan localidades repetidas
	    conexionesEstablecidas = new HashSet<ConectorLocalidades>();
	}
	
	public void finalizarCargaLocalidades() {
	    grafoCompleto = new Grafo(new LinkedList<Localidad>(localidadesCargadas), costoPorKm,
	                    porcentajeSupera300Km, costoProvinciasDistintas);
	    actualizarConexionesEstablecidas(grafoCompleto);
	}

	public boolean crearGrafoAGM() {
		if (grafoCompleto == null || grafoCompleto.tamanio() <= 0) {
			throw new RuntimeException("Debe agregar vertices para poder crear el AGM");
		}
		if (grafoCompleto != null) {
			grafoAGM = AGM.generarPorPrim(grafoCompleto);
			actualizarConexionesEstablecidas(grafoAGM);
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

	private void actualizarConexionesEstablecidas(Grafo grafoActual) {
		conexionesEstablecidas = new HashSet<ConectorLocalidades>();
		for (ConectorLocalidades arista : grafoActual.getAristas()) {
			conexionesEstablecidas.add(arista);
		}
	}

	public boolean cargarLocalidad(String nombre, String provincia, Float latitud, Float longitud) {
		Localidad localidad = new Localidad(nombre, provincia, latitud, longitud);
		return localidadesCargadas.add(localidad);
	}

	
	public Coordinate obtenerCoordenadasLocalidad(String nombre, String provincia) {
		for (Localidad localidad : localidadesCargadas) {
			if (localidad.getNombre().equals(nombre) && localidad.getProvincia().equals(provincia)) {
				return new Coordinate(localidad.getLatitud(), localidad.getLongitud());
			}
		}
		return null;
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
		if (precio < 0 ) {
			throw new IllegalArgumentException("El porcentaje agregado si supera 300KM no puede ser negativo.");
		}
		porcentajeSupera300Km=precio;
	}
	
	public void setCostoProvinciaDistinta(double precio) {
		if (precio < 0) {
			throw new IllegalArgumentException(
					"El costo fijo agregado por provincias distintas no puede ser negativo.");
		}
		costoProvinciasDistintas=precio;
	}
	
}

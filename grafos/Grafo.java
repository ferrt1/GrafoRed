package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Grafo {

	private LinkedList<ConectorLocalidades> aristas;
	protected Map<Localidad, LinkedList<Localidad>> vecinos;

	private double costoTotal;
	private double costoPorKm;
	private double costoPorProvincia;
	private double mayor300k;

	public Grafo(double costo, double costoPorProvincia, double porcentajeSupera300km, Localidad... localidades) {
		vecinos = new HashMap<Localidad, LinkedList<Localidad>>();
		aristas = new LinkedList<ConectorLocalidades>();
		costoTotal = 0.0;
		this.costoPorKm = costo;
		this.costoPorProvincia = costoPorProvincia;
		this.mayor300k = porcentajeSupera300km;
		for (Localidad localidad : localidades)
			vecinos.put(localidad, new LinkedList<Localidad>());
	}
	

	public Grafo(LinkedList<Localidad> listaVertices, double costo, double costoPorProvincia, double porcentajeSupera300km) {
		this.costoPorKm = costo;
		this.costoPorProvincia = costoPorProvincia;
		this.mayor300k = porcentajeSupera300km;
		this.vecinos = new HashMap<Localidad, LinkedList<Localidad>>();
		this.aristas = new LinkedList<ConectorLocalidades>();
		costoTotal = 0.0;
		
		for (Localidad localidad : listaVertices) {
			agregarVertice(localidad);
		}
		generarTodasLasAristas();
	}

	private void generarTodasLasAristas() {
	    LinkedList<Localidad> listaVertices = new LinkedList<>(vecinos.keySet());
	    for (int i = 0; i < listaVertices.size(); i++) {
	        for (int j = i + 1; j < listaVertices.size(); j++) {
	            agregarArista(listaVertices.get(i), listaVertices.get(j));
	        }
	    }
	}

	public void agregarVertice(Localidad vertice) {
		if (vertice != null && !vecinos.containsKey(vertice))
			vecinos.put(vertice, new LinkedList<Localidad>());
	}

	public void agregarArista(Localidad vertice1, Localidad vertice2) {
		if (verificarVertices(vertice1, vertice2))
			return;

		double costoArista = Costo.obtenerCosto(vertice1, vertice2, costoPorKm, mayor300k, costoPorProvincia);
		costoArista = Math.round(costoArista * 100.0) / 100.0;
		ConectorLocalidades nuevoCable = new ConectorLocalidades(vertice1, vertice2,
				Costo.calcularDistancia(vertice1, vertice2), costoArista);
		if (!aristas.contains(nuevoCable)) {
			aristas.add(nuevoCable);
			vecinos.get(vertice1).add(vertice2);
			vecinos.get(vertice2).add(vertice1);
			sumarCosto(costoArista);
		}
	}

	private void sumarCosto(double costo) {
		costoTotal += costo;
		costoTotal = Math.round(costoTotal * 100.0) / 100.0;  
	}


	public boolean existeArista(Localidad vertice1, Localidad vertice2) {
		if (vertice1 == null || vertice2 == null) {
			return false;
		}
		return aristas.contains(new ConectorLocalidades(vertice1, vertice2, Costo.calcularDistancia(vertice1, vertice2), 
				Costo.obtenerCosto(vertice1, vertice2, costoPorKm, mayor300k, costoPorKm)));
	}

	public LinkedList<Localidad> vecinos(Localidad v) {
		return vecinos.get(v);
	}

	public int tamanio() {
		return vecinos.size();
	}


	private boolean verificarVertices(Localidad v1, Localidad v2) {
		return v1 == null || v2 == null || !vecinos.keySet().contains(v1) || !vecinos.keySet().contains(v2);
	}

	public ArrayList<Localidad> getLocalidades() {
		return new ArrayList<Localidad>(vecinos.keySet());
	}

	public LinkedList<ConectorLocalidades> getAristas() {
		return aristas;
	}

	public void imprimirLocalidades() {
		for (Localidad localidad : vecinos.keySet())
			System.out.println(localidad.toString());
	}
	
	@Override
	public String toString() {
	    StringBuilder cadena = new StringBuilder();
	    
	    cadena.append("Costo por kilómetro: ").append(costoPorKm).append("\n");
	    cadena.append("Costo por provincias distintas: ").append(costoPorProvincia).append("\n");
	    cadena.append("Porcentaje si supera 300KM: ").append(mayor300k).append("\n");

	    for (Localidad localidad : vecinos.keySet()) {
	        cadena.append(localidad.getNombre()).append(" (").append(localidad.getProvincia()).append("): ");
	        for (Localidad vecino : vecinos.get(localidad)) {
	            cadena.append(vecino.getNombre()).append(" -- ");
	        }
	        cadena.append("\n");
	    }

	    for (ConectorLocalidades conexion : aristas) {
	        cadena.append(conexion.getVertice1().getNombre()).append(" (").append(conexion.getVertice1().getProvincia())
	                .append(") ------ ").append(conexion.getVertice2().getNombre()).append(" (")
	                .append(conexion.getVertice2().getProvincia()).append(") : ").append(conexion.getDistancia())
	                .append("KM $").append(conexion.getCosto());
	        cadena.append("\n");
	    }

	    return cadena.toString();
	}

	public boolean isAGM() {
		return aristas.size() == tamanio() - 1;
	}

	public Map<Localidad, LinkedList<Localidad>> getVecinos() {
		return vecinos;
	}

	public void setVecinos(Map<Localidad, LinkedList<Localidad>> vecinos) {
		this.vecinos = vecinos;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public double getCostoPorProvincia() {
		return costoPorProvincia;
	}

	public void setCostoPorProvincia(double costoPorProvincia) {
		this.costoPorProvincia = costoPorProvincia;
	}

	public double getPorcentajeSupera300km() {
		return mayor300k;
	}

	public void setMayor300k(double mayor300k) {
		this.mayor300k = mayor300k;
	}


}
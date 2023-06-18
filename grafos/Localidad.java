package grafos;

import java.util.HashMap;
import java.util.LinkedList;

public class Localidad {

	private String nombre;
	private String provincia;

	private float latitud;
	private float longitud;
	private HashMap<Integer, Localidad> localidades;


	public Localidad(String nombre, String provincia, float latitud, float longitud) {

		this.nombre = nombre;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;

		localidades = new HashMap<Integer, Localidad>();
	}

	public void agregarLocalidad(Localidad localidad, LinkedList<Localidad> l) {
		l.add(localidad);
	}

	public String buscarNombreLocalidad(Integer i) {
		return localidades.get(i).getNombre();
	}

	@Override
	public boolean equals(Object otraLocalidad) {
		if(otraLocalidad == null || otraLocalidad.getClass() != this.getClass()) {
			return false;
		}
		Localidad otra = (Localidad) otraLocalidad;
		return this.provincia.equals(otra.getProvincia()) && this.nombre.equals(otra.getNombre())
				&& this.latitud == otra.getLatitud() && this.longitud == otra.getLongitud();
	}

	public void agregarLocalidad(Integer i, Localidad l) {
		localidades.put(i-1, l);
	}

	public int tamanio() {
		return localidades.size();
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public HashMap<Integer, Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(HashMap<Integer, Localidad> localidades) {
		this.localidades = localidades;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return "" +
				"" + nombre + " " +
				"," + provincia + " " +
						"," + latitud +
						", " + longitud;
	}
}

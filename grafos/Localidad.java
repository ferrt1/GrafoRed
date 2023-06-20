package grafos;


public class Localidad {
	private String nombre;
	private String provincia;
	private float latitud;
	private float longitud;

	public Localidad(String nombre, String provincia, float latitud, float longitud) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
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

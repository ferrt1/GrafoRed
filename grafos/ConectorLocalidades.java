package grafos;

public class ConectorLocalidades {

	private Localidad localidad1;
	private Localidad localidad2;
	private Double distanciaEnKm;
	private Double costo;

	public ConectorLocalidades(Localidad local1, Localidad local2, double distancia, double costo) {
		if(local1 == null || local2 == null)
			throw new IllegalArgumentException("Faltan valores");
			
		localidad1 = local1;
		localidad2 = local2;
		distanciaEnKm = distancia;
		this.costo = costo;
	}

	@Override
	public boolean equals(Object conectorLocalidad) {
		if (conectorLocalidad == null || conectorLocalidad.getClass() != this.getClass())
			return false;
		ConectorLocalidades conector = (ConectorLocalidades) conectorLocalidad;
		return (this.localidad1.equals(conector.localidad1)
				&& this.localidad2.equals(conector.localidad2))
				|| (this.localidad2.equals(conector.localidad1))
						&& this.localidad1.equals(conector.localidad2);
	}


	public Double getCosto() {
		return costo;
	}

	public Double getDistancia() {
		return distanciaEnKm;
	}

	public Localidad getVertice1() {
		return localidad1;
	}

	public Localidad getVertice2() {
		return localidad2;
	}

}

package grafos;

public class Costo {

    private static final double RADIO_TIERRA = 6371;

    public static double calcularDistancia(Localidad origen, Localidad destino) {
        double latitudOrigen =  Math.toRadians(origen.getLatitud());
        double longitudOrigen =  Math.toRadians(origen.getLongitud());
        double latitudDestino =  Math.toRadians(destino.getLatitud());
        double longitudDestino =  Math.toRadians(destino.getLongitud());

        double restaLatitud = latitudDestino - latitudOrigen;
        double restaLongitud = longitudDestino - longitudOrigen;

        double haversine = calcularHaversine(restaLatitud, restaLongitud, latitudOrigen, latitudDestino);

        return 2 * RADIO_TIERRA * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
    }

    private static double calcularHaversine(double restaLatitud, double restaLongitud, double latitudOrigen, double latitudDestino) {
        double sinRestaLatitud = Math.sin(restaLatitud / 2);
        double sinRestaLongitud = Math.sin(restaLongitud / 2);

        return Math.pow(sinRestaLatitud, 2) + Math.cos(latitudOrigen) * Math.cos(latitudDestino) * Math.pow(sinRestaLongitud, 2);
    }
    
    public static double obtenerCosto(Localidad origen, Localidad destino, double costoPorKm, double porcentajeExtra, double costoExtraProvincias) {
        double distancia = calcularDistancia(origen, destino);
        double costo = distancia * costoPorKm;

        if (distancia > 300) {
            costo += costo * porcentajeExtra;
        }

        if (!origen.getProvincia().equals(destino.getProvincia())) {
            costo += costoExtraProvincias;
        }

        return costo;
    }
}

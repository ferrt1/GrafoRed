package grafos;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class BFS {

    private static List<Localidad> localidadesPendientes;

    public static boolean esConexo(Grafo grafo){
        if(grafo == null)
            throw new IllegalArgumentException("El grafo no puede ser nulo.");

        return grafo.tamanio() == 0 || obtenerAlcanzables(grafo, grafo.getLocalidades().get(0)).size() == grafo.tamanio();
    }

    public static Set<Localidad> obtenerAlcanzables(Grafo grafo, Localidad origen){
        Set<Localidad> localidadesVisitadas = new HashSet<Localidad>();
        localidadesPendientes = new LinkedList<Localidad>();
        localidadesPendientes.add(origen);

        while(!localidadesPendientes.isEmpty()) {
            Localidad actual = localidadesPendientes.get(0);
            localidadesVisitadas.add(actual);
            agregarVecinosNoVisitados(localidadesVisitadas, grafo.vecinos(actual));
            localidadesPendientes.remove(0);
        }

        return localidadesVisitadas;
    }

    private static void agregarVecinosNoVisitados(Set<Localidad> localidadesVisitadas, List<Localidad> vecinos) {
        for(Localidad vecino: vecinos) {
            if(!localidadesVisitadas.contains(vecino))
                localidadesPendientes.add(vecino);
        }
    }
}

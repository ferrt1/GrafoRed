package grafos;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AGM {
    static Grafo arbolMinimoGenerado;
    static LinkedList<Localidad> localidadesVisitadas;
    static PriorityQueue<ConectorLocalidades> conexionesPrioritarias;

    public static Grafo generarPorPrim(Grafo grafo) {
        if (grafo == null || !BFS.esConexo(grafo)) 
            throw new IllegalArgumentException();
        
        Localidad origen = grafo.getLocalidades().get(0);
        arbolMinimoGenerado = new Grafo(grafo.getCostoPorKm(), 
        		grafo.getPorcentajeSupera300km(), grafo.getCostoPorProvincia(), origen);
        localidadesVisitadas = new LinkedList<Localidad>();
        localidadesVisitadas.add(origen);

        conexionesPrioritarias = new PriorityQueue<>(Comparator.comparing(ConectorLocalidades::getCosto));

        actualizarConexionesPrioritarias(origen, grafo);

        while (!conexionesPrioritarias.isEmpty()) {
            ConectorLocalidades conexionMinima = conexionesPrioritarias.poll();
            Localidad localidadNoVisitada = obtenerNoVisitada(conexionMinima);

            if (localidadNoVisitada != null) {
                arbolMinimoGenerado.agregarVertice(localidadNoVisitada);
                localidadesVisitadas.add(localidadNoVisitada);
                arbolMinimoGenerado.agregarArista(conexionMinima.getVertice1(), conexionMinima.getVertice2());
                
                actualizarConexionesPrioritarias(localidadNoVisitada, grafo);
            }
        }
        return arbolMinimoGenerado;
    }
    
    private static void actualizarConexionesPrioritarias(Localidad localidad, Grafo grafo) {
        for (ConectorLocalidades conexion : grafo.getAristas()) {
            if (conexion.getVertice1().equals(localidad) && !localidadesVisitadas.contains(conexion.getVertice2()) ||
                conexion.getVertice2().equals(localidad) && !localidadesVisitadas.contains(conexion.getVertice1())) {
                conexionesPrioritarias.add(conexion);
            }
        }
    }
    
    private static Localidad obtenerNoVisitada(ConectorLocalidades conexion) {
        if (!localidadesVisitadas.contains(conexion.getVertice1()))
            return conexion.getVertice1();
        if (!localidadesVisitadas.contains(conexion.getVertice2()))
            return conexion.getVertice2();
        return null;
    }

}


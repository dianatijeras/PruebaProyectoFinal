package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Atraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Sendero;

import java.util.*;

public class Grafo {

    private final Map<String, List<Sendero>> listaAdyacencia;
    private final Map<String, Atraccion> nodos;

    public Grafo() {
        this.listaAdyacencia = new HashMap<>();
        this.nodos = new HashMap<>();
    }

    public void agregarNodo(Atraccion atraccion) {
        if (!nodos.containsKey(atraccion.getId())) {
            nodos.put(atraccion.getId(), atraccion);
            listaAdyacencia.put(atraccion.getId(), new ArrayList<>());
        }
    }

    public void agregarSendero(Atraccion origen, Atraccion destino, double peso) {
        agregarNodo(origen);
        agregarNodo(destino);

        listaAdyacencia.get(origen.getId()).add(new Sendero(origen, destino, peso));
        listaAdyacencia.get(destino.getId()).add(new Sendero(destino, origen, peso));
    }

    public List<Sendero> getVecinos(String idAtraccion) {
        return listaAdyacencia.getOrDefault(idAtraccion, new ArrayList<>());
    }

    public List<Atraccion> dijkstra(String idOrigen, String idDestino) {
        if (!nodos.containsKey(idOrigen) || !nodos.containsKey(idDestino)) {
            return new ArrayList<>();
        }

        Atraccion destino = nodos.get(idDestino);
        if (destino.getEstado() == co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoAtraccion.CERRADA) {
            System.out.println("[GRAFO] La atracción destino '" + destino.getNombre() + "' está CERRADA.");
            return new ArrayList<>();
        }

        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> anterior = new HashMap<>();

        for (String id : nodos.keySet()) {
            distancias.put(id, Double.MAX_VALUE);
        }
        distancias.put(idOrigen, 0.0);

        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(a -> a[0]));
        pq.offer(new double[]{0.0, idOrigen.hashCode()});

        Map<Integer, String> hashAId = new HashMap<>();
        for (String id : nodos.keySet()) {
            hashAId.put(id.hashCode(), id);
        }

        Set<String> visitados = new HashSet<>();

        while (!pq.isEmpty()) {
            double[] actual = pq.poll();
            String idActual = hashAId.get((int) actual[1]);

            if (idActual == null || visitados.contains(idActual)) continue;
            visitados.add(idActual);

            if (idActual.equals(idDestino)) break;

            for (Sendero sendero : getVecinos(idActual)) {
                String idVecino = sendero.getDestino().getId();
                double nuevaDist = distancias.get(idActual) + sendero.getPeso();

                if (nuevaDist < distancias.get(idVecino)) {
                    distancias.put(idVecino, nuevaDist);
                    anterior.put(idVecino, idActual);
                    pq.offer(new double[]{nuevaDist, idVecino.hashCode()});
                }
            }
        }

        return reconstruirRuta(anterior, idOrigen, idDestino);
    }

    public List<Atraccion> bfs(String idOrigen, String idDestino) {
        if (!nodos.containsKey(idOrigen) || !nodos.containsKey(idDestino)) {
            return new ArrayList<>();
        }

        Map<String, String> anterior = new HashMap<>();
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        cola.add(idOrigen);
        visitados.add(idOrigen);

        while (!cola.isEmpty()) {
            String idActual = cola.poll();

            if (idActual.equals(idDestino)) break;

            for (Sendero sendero : getVecinos(idActual)) {
                String idVecino = sendero.getDestino().getId();
                if (!visitados.contains(idVecino)) {
                    visitados.add(idVecino);
                    anterior.put(idVecino, idActual);
                    cola.add(idVecino);
                }
            }
        }

        return reconstruirRuta(anterior, idOrigen, idDestino);
    }

    private List<Atraccion> reconstruirRuta(Map<String, String> anterior,
                                            String idOrigen, String idDestino) {
        List<Atraccion> ruta = new ArrayList<>();
        String actual = idDestino;

        if (!actual.equals(idOrigen) && !anterior.containsKey(actual)) {
            return ruta;
        }

        while (actual != null) {
            ruta.add(0, nodos.get(actual));
            actual = anterior.get(actual);
        }

        return ruta;
    }

    public Map<String, Atraccion> getNodos() {
        return nodos;
    }

    public int getNumeroNodos() {
        return nodos.size();
    }

    public int getNumeroAristas() {
        int total = 0;
        for (List<Sendero> senderos : listaAdyacencia.values()) {
            total += senderos.size();
        }
        return total / 2;
    }

    public void imprimirGrafo() {
        System.out.println("=== MAPA DEL PARQUE (Lista de Adyacencia) ===");
        for (Map.Entry<String, List<Sendero>> entry : listaAdyacencia.entrySet()) {
            String idAtraccion = entry.getKey();
            String nombreAtraccion = nodos.get(idAtraccion).getNombre();
            System.out.print("  [" + nombreAtraccion + "] → ");
            for (Sendero s : entry.getValue()) {
                System.out.print(s.getDestino().getNombre() + "(peso=" + s.getPeso() + ") ");
            }
            System.out.println();
        }
    }

    public Map<String, List<Sendero>> getListaAdyacencia() {
        return listaAdyacencia;
    }

    @Override
    public String toString() {
        return "Grafo{" +
                "listaAdyacencia=" + listaAdyacencia +
                ", nodos=" + nodos +
                '}';
    }
}

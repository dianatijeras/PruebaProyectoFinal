package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Sendero;

import java.util.List;
import java.util.Map;

public class Grafo {

    private List<NodoGrafo> nodos;
    private Map<NodoGrafo, List<Sendero>> listaAdyacencia;
}

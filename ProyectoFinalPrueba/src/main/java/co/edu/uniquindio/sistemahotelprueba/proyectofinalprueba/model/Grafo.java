package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Grafo {

    private List<NodoGrafo> nodos;
    private Map<NodoGrafo, List<Sendero>> listaAdyacencia;
}

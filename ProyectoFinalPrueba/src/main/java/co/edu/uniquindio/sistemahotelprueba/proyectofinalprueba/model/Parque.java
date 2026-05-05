package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Parque {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private int aforoActual;
    private List<Zona> zonas;
    private Grafo mapa;

}

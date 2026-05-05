package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Zona {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private int aforoActual;
    private List<Atraccion> atracciones;
    private ListaEnlazada<Operador> operadoresAsignados;
}

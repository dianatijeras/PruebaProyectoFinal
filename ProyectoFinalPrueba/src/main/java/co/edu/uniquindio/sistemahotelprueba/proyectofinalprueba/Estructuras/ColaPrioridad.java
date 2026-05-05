package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;


import java.util.ArrayList;

public class ColaPrioridad<T extends Comparable<T>> {

    private final ArrayList<T> heap;

    public ColaPrioridad() {
        this.heap = new ArrayList<>();
    }
}

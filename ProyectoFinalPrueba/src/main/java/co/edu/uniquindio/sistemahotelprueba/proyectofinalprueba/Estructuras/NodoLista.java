package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;


public class NodoLista<T> {

    public T dato;
    public NodoLista<T> siguiente;

    public NodoLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoLista{" +
                "dato=" + dato +
                ", siguiente=" + siguiente +
                '}';
    }
}

package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;

public class NodoArbol<T> {

    public String clave;
    public T dato;
    public NodoArbol<T> izquierdo;
    public NodoArbol<T> derecho;

    public NodoArbol(String clave, T dato) {
        this.clave = clave;
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoArbol<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol<T> derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return "NodoArbol{" +
                "clave='" + clave + '\'' +
                ", dato=" + dato +
                ", izquierdo=" + izquierdo +
                ", derecho=" + derecho +
                '}';
    }
}

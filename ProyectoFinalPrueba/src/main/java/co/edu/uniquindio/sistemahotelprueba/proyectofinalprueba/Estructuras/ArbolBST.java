package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArbolBST<T> {

    private NodoArbol<T> raiz;
    private int tamanio;

    public ArbolBST() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public void insertar(String clave, T dato) {
        raiz = insertarRec(raiz, clave, dato);
    }

    private NodoArbol<T> insertarRec(NodoArbol<T> nodo, String clave, T dato) {
        if (nodo == null) {
            tamanio++;
            return new NodoArbol<>(clave, dato);
        }
        int cmp = clave.compareTo(nodo.clave);
        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, clave, dato);
        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, clave, dato);
        } else {
            nodo.dato = dato;
        }
        return nodo;
    }

    public T buscar(String clave) {
        NodoArbol<T> nodo = buscarNodo(raiz, clave);
        return (nodo != null) ? nodo.dato : null;
    }

    private NodoArbol<T> buscarNodo(NodoArbol<T> nodo, String clave) {
        if (nodo == null) return null;
        int cmp = clave.compareTo(nodo.clave);
        if (cmp < 0)  return buscarNodo(nodo.izquierdo, clave);
        if (cmp > 0)  return buscarNodo(nodo.derecho, clave);
        return nodo;
    }

    public boolean contiene(String clave) {
        return buscarNodo(raiz, clave) != null;
    }

    public boolean eliminar(String clave) {
        if (!contiene(clave)) return false;
        raiz = eliminarRec(raiz, clave);
        tamanio--;
        return true;
    }

    private NodoArbol<T> eliminarRec(NodoArbol<T> nodo, String clave) {
        if (nodo == null) return null;
        int cmp = clave.compareTo(nodo.clave);
        if (cmp < 0) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, clave);
        } else if (cmp > 0) {
            nodo.derecho = eliminarRec(nodo.derecho, clave);
        } else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null)   return nodo.izquierdo;

            NodoArbol<T> sucesor = minimoNodo(nodo.derecho);
            nodo.clave = sucesor.clave;
            nodo.dato  = sucesor.dato;
            nodo.derecho = eliminarRec(nodo.derecho, sucesor.clave);
        }
        return nodo;
    }

    private NodoArbol<T> minimoNodo(NodoArbol<T> nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    public List<T> inorden() {
        List<T> resultado = new ArrayList<>();
        inordenRec(raiz, resultado);
        return resultado;
    }

    private void inordenRec(NodoArbol<T> nodo, List<T> resultado) {
        if (nodo == null) return;
        inordenRec(nodo.izquierdo, resultado);
        resultado.add(nodo.dato);
        inordenRec(nodo.derecho, resultado);
    }

    public List<T> preorden() {
        List<T> resultado = new ArrayList<>();
        preordenRec(raiz, resultado);
        return resultado;
    }

    private void preordenRec(NodoArbol<T> nodo, List<T> resultado) {
        if (nodo == null) return;
        resultado.add(nodo.dato);
        preordenRec(nodo.izquierdo, resultado);
        preordenRec(nodo.derecho, resultado);
    }

    public void forEachInorden(Consumer<T> accion) {
        inorden().forEach(accion);
    }

    public T minimo() {
        if (raiz == null) return null;
        return minimoNodo(raiz).dato;
    }

    public T maximo() {
        if (raiz == null) return null;
        NodoArbol<T> nodo = raiz;
        while (nodo.derecho != null) nodo = nodo.derecho;
        return nodo.dato;
    }

    public boolean estaVacio() {
        return tamanio == 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public NodoArbol<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol<T> raiz) {
        this.raiz = raiz;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "ArbolBST{" +
                "raiz=" + raiz +
                ", tamanio=" + tamanio +
                '}';
    }
}

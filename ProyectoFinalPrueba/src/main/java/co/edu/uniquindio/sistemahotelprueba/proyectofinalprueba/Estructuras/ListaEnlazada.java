package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;

public class ListaEnlazada<T> {

    private NodoLista<T> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void insertarAlFrente(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        tamanio++;
    }

    public void insertarAlFinal(T dato) {
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoLista<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;

        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }

        NodoLista<T> actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.dato.equals(dato)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamanio--;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public T buscarPrimero(java.util.function.Predicate<T> condicion) {
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            if (condicion.test(actual.dato)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean contiene(java.util.function.Predicate<T> condicion) {
        return buscarPrimero(condicion) != null;
    }

    public void forEach(java.util.function.Consumer<T> accion) {
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            accion.accept(actual.dato);
            actual = actual.siguiente;
        }
    }


    public java.util.List<T> aLista() {
        java.util.List<T> resultado = new java.util.ArrayList<>();
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            resultado.add(actual.dato);
            actual = actual.siguiente;
        }
        return resultado;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public int getTamanio() {
        return tamanio;
    }

    public NodoLista<T> getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoLista<T> cabeza) {
        this.cabeza = cabeza;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListaEnlazada[");
        NodoLista<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) sb.append(" -> ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}

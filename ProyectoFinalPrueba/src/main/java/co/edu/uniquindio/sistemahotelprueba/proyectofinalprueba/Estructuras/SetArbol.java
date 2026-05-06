package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras;

import java.util.List;

public class SetArbol<T> {

    private final ArbolBST<T> arbol;

    public SetArbol() {
        this.arbol = new ArbolBST<>();
    }

    public void agregar(String clave, T valor) {
        arbol.insertar(clave, valor);
    }

    public boolean eliminar(String clave) {
        return arbol.eliminar(clave);
    }

    public boolean contiene(String clave) {
        return arbol.contiene(clave);
    }

    public List<T> listar(){
        return arbol.inorden();
    }

    public int getTamanio() {
        return arbol.getTamanio();
    }

    public boolean estaVacio() {
        return arbol.estaVacio();
    }

    public ArbolBST<T> getArbol() {
        return arbol;
    }

    @Override
    public String toString() {
        return "SetArbol{" +
                "arbol=" + arbol +
                '}';
    }
}

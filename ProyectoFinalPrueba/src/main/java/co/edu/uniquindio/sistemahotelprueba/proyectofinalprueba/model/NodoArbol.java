package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NodoArbol<T> {

    private String clave;
    private T dato;
    private NodoArbol<T> izquierdo;
    private NodoArbol<T> derecho;
}

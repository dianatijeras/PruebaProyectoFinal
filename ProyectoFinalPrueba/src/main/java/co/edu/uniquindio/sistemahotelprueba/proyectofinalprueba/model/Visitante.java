package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Visitante extends Usuario{

    private double estatura;
    private double saldoVirtual;
    private String foto;
    private NodoGrafo ubicacionActual;
    private Ticket ticketActivo;
    private ListaEnlazada historialVisitas;
    private Set<Atraccion> favoritos;
    private List notificaciones;

    public Visitante(String idUsuario, String nombre, String documento, int edad, String password, RolEnum rol) {
        super(idUsuario, nombre, documento, edad, password, rol);
    }
}

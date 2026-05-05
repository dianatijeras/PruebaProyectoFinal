package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class EntradaEnCola {

    private Visitante visitante;
    private Ticket ticket;
    private Date horaIngreso;
    private int prioridad;

}

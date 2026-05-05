package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Ticket {
    private String id;
    private TipoTicket tipo;
    private double precio;
    private LocalDateTime fechaCompra;
    private EstadoTicket estado;
    private int prioridad;
    private Visitante visitante;

}

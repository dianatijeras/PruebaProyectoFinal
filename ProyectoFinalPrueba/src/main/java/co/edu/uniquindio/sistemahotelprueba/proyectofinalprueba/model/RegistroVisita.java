package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class RegistroVisita {

    private Atraccion atraccion;
    private Date fechaHora;
    private TipoTicket tipoTicket;
    private double costoDeducido;
}

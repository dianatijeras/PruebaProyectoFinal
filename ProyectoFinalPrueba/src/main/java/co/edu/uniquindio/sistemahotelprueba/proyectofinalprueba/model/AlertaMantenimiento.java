package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class AlertaMantenimiento {

    private String id;
    private Atraccion atraccion;
    private Date fechaGeneracion;
    private boolean atendida;

}

package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ValidacionAcceso {

    private Visitante visitante;
    private Atraccion atraccion;
    private Resultado resultado;
    private String motivoRechazo;

}

package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class RevisionTecnica {

    private String id;
    private Atraccion atraccion;
    private Operador operador;
    private Date fechaHora;
    private String descripcion;
    private ResultadoRevision resultado;

}

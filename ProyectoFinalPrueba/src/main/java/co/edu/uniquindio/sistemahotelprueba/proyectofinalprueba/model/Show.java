package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Show {

    private String id;
    private String nombre;
    private Zona zona;
    private Date horario;
    private int duracion;
    private EstadoShow estado;

}

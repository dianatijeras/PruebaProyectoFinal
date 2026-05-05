package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AlertaClimatica {

    private String id;
    private TipoClim tipo;
    private Date fechHora;
    private boolean activa;
    private List<Atraccion> atraccionesAfectadas;
}

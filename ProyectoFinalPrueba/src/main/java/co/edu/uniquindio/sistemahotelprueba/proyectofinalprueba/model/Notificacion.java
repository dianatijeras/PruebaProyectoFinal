package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Notificacion {

    private String id;
    private String mensaje;
    private TipoNotif tipo;
    private Date fechaHora;
    private List<Visitante> destinatarios;
    private boolean leida;
}

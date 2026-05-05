package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoNotif;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notificacion {

    private String id;
    private String mensaje;
    private TipoNotif tipo;
    private LocalDateTime fechaHora;
    private List<Visitante> destinatarios;
    private boolean leida;

    public Notificacion(String id, String mensaje, TipoNotif tipo) {
        this.id = id;
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
        this.destinatarios = new ArrayList<>();
        this.leida = false;
    }

    public void agregarDestinatario(Visitante visitante){
        destinatarios.add(visitante);
    }

    public void marcarLeida(){
        this.leida = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public TipoNotif getTipo() {
        return tipo;
    }

    public void setTipo(TipoNotif tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public List<Visitante> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<Visitante> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "id='" + id + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", tipo=" + tipo +
                ", fechaHora=" + fechaHora +
                ", destinatarios=" + destinatarios +
                ", leida=" + leida +
                '}';
    }
}

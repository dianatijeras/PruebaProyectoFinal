package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import java.time.LocalDateTime;

public class AlertaMantenimiento implements Comparable<AlertaMantenimiento> {

    private String id;
    private Atraccion atraccion;
    private LocalDateTime fechaGeneracion;
    private boolean atendida;

    public AlertaMantenimiento(String id, Atraccion atraccion) {
        this.id = id;
        this.atraccion = atraccion;
        this.fechaGeneracion = LocalDateTime.now();
        this.atendida = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public boolean isAtendida() {
        return atendida;
    }

    public void setAtendida(boolean atendida) {
        this.atendida = atendida;
    }

    @Override
    public String toString() {
        return "AlertaMantenimiento{" +
                "id='" + id + '\'' +
                ", atraccion=" + atraccion +
                ", fechaGeneracion=" + fechaGeneracion +
                ", atendida=" + atendida +
                '}';
    }

    @Override
    public int compareTo(AlertaMantenimiento other) {
        return this.fechaGeneracion.compareTo(other.fechaGeneracion);
    }

    public void marcarAtendida(){
        this.atendida = true;
    }
}

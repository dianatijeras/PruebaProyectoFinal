package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoTicket;

import java.time.LocalDateTime;
import java.util.Date;

public class RegistroVisita {

    private Atraccion atraccion;
    private LocalDateTime fechaHora;
    private Ticket ticket;
    private double costoDeducido;

    public RegistroVisita(Atraccion atraccion, Ticket ticket, double costoDeducido) {
        this.atraccion = atraccion;
        this.fechaHora = LocalDateTime.now();
        this.ticket = ticket;
        this.costoDeducido = costoDeducido;
    }

    public Atraccion getAtraccion() {
        return atraccion;
    }

    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public double getCostoDeducido() {
        return costoDeducido;
    }

    public void setCostoDeducido(double costoDeducido) {
        this.costoDeducido = costoDeducido;
    }

    @Override
    public String toString() {
        return "RegistroVisita{" +
                "atraccion=" + atraccion +
                ", fechaHora=" + fechaHora +
                ", ticket=" + ticket +
                ", costoDeducido=" + costoDeducido +
                '}';
    }
}

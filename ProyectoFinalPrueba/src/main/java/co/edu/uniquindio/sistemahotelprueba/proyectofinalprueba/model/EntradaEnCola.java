package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;
import java.util.Date;

public class EntradaEnCola implements Comparable<EntradaEnCola> {

    private Visitante visitante;
    private Ticket ticket;
    private LocalDateTime horaIngreso;
    private int prioridad;

    public EntradaEnCola(Visitante visitante, Ticket ticket) {
        this.visitante = visitante;
        this.ticket = ticket;
        this.horaIngreso = LocalDateTime.now();
        this.prioridad = ticket.getPrioridad();
    }

    @Override
    public int compareTo(EntradaEnCola other) {
        if(this.prioridad != other.prioridad){
            return Integer.compare(this.prioridad, other.prioridad);
        }
        return this.horaIngreso.compareTo(other.horaIngreso);
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "EntradaEnCola{" +
                "visitante=" + visitante +
                ", ticket=" + ticket +
                ", horaIngreso=" + horaIngreso +
                ", prioridad=" + prioridad +
                '}';
    }
}

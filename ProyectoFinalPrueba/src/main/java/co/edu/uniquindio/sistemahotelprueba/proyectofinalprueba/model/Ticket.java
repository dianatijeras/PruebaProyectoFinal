package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoTicket;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoTicket;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private TipoTicket tipo;
    private double precio;
    private LocalDateTime fechaCompra;
    private EstadoTicket estado;
    private Visitante visitante;

    public Ticket(String id, TipoTicket tipo, double precio, Visitante visitante) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.fechaCompra = LocalDateTime.now();
        this.estado = EstadoTicket.ACTIVO;
        this.visitante = visitante;
    }

    public int getPrioridad(){
        return(tipo == TipoTicket.FAST_PASS) ? 1 : 2;
    }

    public boolean estaActivo(){
        return estado == EstadoTicket.ACTIVO;
    }

    public void marcarUsado(){
        this.estado = EstadoTicket.USADO;
    }

    public void marcarExpirado(){
        this.estado = EstadoTicket.EXPIRADO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoTicket getTipo() {
        return tipo;
    }

    public void setTipo(TipoTicket tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", precio=" + precio +
                ", fechaCompra=" + fechaCompra +
                ", estado=" + estado +
                ", visitante=" + visitante +
                '}';
    }
}

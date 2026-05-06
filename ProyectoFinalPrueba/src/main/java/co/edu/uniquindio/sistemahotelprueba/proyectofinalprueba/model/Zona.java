package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.ListaEnlazada;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Zona {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private int aforoActual;
    private final List<Atraccion> atracciones;
    private final ListaEnlazada<Operador> operadoresAsignados;

    public Zona(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.aforoActual = 0;
        this.atracciones = new ArrayList<>();
        this.operadoresAsignados = new ListaEnlazada<>();
    }

    public void agregarAtraccion (Atraccion atraccion){
        atracciones.add(atraccion);
        atraccion.setZona(this);
    }

    public boolean contieneAtraccion(String idAtraccion){
        return atracciones.stream().anyMatch(a -> a.getId().equals(idAtraccion));
    }

    public Atraccion buscarAtraccion (String idAtraccion){
        return atracciones.stream()
                .filter(a -> a.getId().equals(idAtraccion))
                .findFirst()
                .orElse(null);
    }

    public void agregarOperador (Operador operador){
        if (! operadoresAsignados.contiene(op -> op.getDocumento().equals(operador.getDocumento()))){
            operadoresAsignados.insertarAlFinal(operador);
            operador.setZonaAsignada(this);
        }
    }

    public boolean quitarOperador (Operador operador){
        if (operadoresAsignados.getTamanio() <= 1){
            System.out.println("[ZONA] No se puede quitar: la zona quedaria sin operador");
            return false;
        }
        boolean eliminado = operadoresAsignados.eliminar(operador);
        if (eliminado) operador.setZonaAsignada(null);
        return eliminado;
    }

    public ListaEnlazada<Operador> getOperadoresAsignados (){
        return operadoresAsignados;
    }

    public boolean tieneAforoDisponible (){
        return aforoActual < capacidadMaxima;
    }

    public void incrementarAforo (){
        if (aforoActual < capacidadMaxima){
            aforoActual ++;
        }
    }

    public void decrementarAforo (){
        if (aforoActual > 0){
            aforoActual --;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getAforoActual() {
        return aforoActual;
    }

    public void setAforoActual(int aforoActual) {
        this.aforoActual = aforoActual;
    }

    public List<Atraccion> getAtracciones() {
        return atracciones;
    }

    @Override
    public String toString() {
        return "Zona{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", aforoActual=" + aforoActual +
                ", atracciones=" + atracciones +
                ", operadoresAsignados=" + operadoresAsignados +
                '}';
    }
}

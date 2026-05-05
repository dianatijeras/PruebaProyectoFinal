package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoShow;
import jdk.vm.ci.meta.Local;

import java.time.LocalDateTime;
import java.util.Date;

public class Show {

    private String id;
    private String nombre;
    private Zona zona;
    private LocalDateTime horario;
    private int duracion;
    private EstadoShow estado;

    public Show(String id, String nombre, Zona zona, LocalDateTime horario, int duracion) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.horario = horario;
        this.duracion = duracion;
        this.estado = EstadoShow.PROGRAMADO;
    }

    public void iniciar(){
        this.estado = EstadoShow.EN_CURSO;
    }

    public void finalizar(){
        this.estado = EstadoShow.FINALIZADO;
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public EstadoShow getEstado() {
        return estado;
    }

    public void setEstado(EstadoShow estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", zona=" + zona +
                ", horario=" + horario +
                ", duracion=" + duracion +
                ", estado=" + estado +
                '}';
    }
}

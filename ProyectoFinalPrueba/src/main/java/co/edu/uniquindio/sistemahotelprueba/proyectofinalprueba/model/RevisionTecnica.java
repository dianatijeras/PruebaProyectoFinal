package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.ResultadoRevision;

import java.time.LocalDateTime;


public class RevisionTecnica {

    private String id;
    private Atraccion atraccion;
    private Operador operador;
    private LocalDateTime fechaHora;
    private String descripcion;
    private ResultadoRevision resultado;

    public RevisionTecnica(String id, Atraccion atraccion, Operador operador, String descripcion, ResultadoRevision resultado) {
        this.id = id;
        this.atraccion = atraccion;
        this.operador = operador;
        this.fechaHora = LocalDateTime.now();
        this.descripcion = descripcion;
        this.resultado = resultado;
    }

    public boolean esSatisfactoria(){
        return resultado == ResultadoRevision.SATIFACTORIA;
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

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ResultadoRevision getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoRevision resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "RevisionTecnica{" +
                "id='" + id + '\'' +
                ", atraccion=" + atraccion +
                ", operador=" + operador +
                ", fechaHora=" + fechaHora +
                ", descripcion='" + descripcion + '\'' +
                ", resultado=" + resultado +
                '}';
    }
}

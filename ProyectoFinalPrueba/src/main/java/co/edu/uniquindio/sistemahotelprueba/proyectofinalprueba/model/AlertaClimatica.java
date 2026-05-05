package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoClima;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlertaClimatica {

    private String id;
    private TipoClima tipo;
    private LocalDateTime fechHora;
    private boolean activa;
    private List<Atraccion> atraccionesAfectadas;

    public AlertaClimatica(String id, TipoClima tipo) {
        this.id = id;
        this.tipo = tipo;
        this.fechHora = LocalDateTime.now();
        this.activa = true;
        this.atraccionesAfectadas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoClima getTipo() {
        return tipo;
    }

    public void setTipo(TipoClima tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechHora() {
        return fechHora;
    }

    public void setFechHora(LocalDateTime fechHora) {
        this.fechHora = fechHora;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public List<Atraccion> getAtraccionesAfectadas() {
        return atraccionesAfectadas;
    }

    public void setAtraccionesAfectadas(List<Atraccion> atraccionesAfectadas) {
        this.atraccionesAfectadas = atraccionesAfectadas;
    }

    @Override
    public String toString() {
        return "AlertaClimatica{" +
                "id='" + id + '\'' +
                ", tipo=" + tipo +
                ", fechHora=" + fechHora +
                ", activa=" + activa +
                ", atraccionesAfectadas=" + atraccionesAfectadas +
                '}';
    }
}

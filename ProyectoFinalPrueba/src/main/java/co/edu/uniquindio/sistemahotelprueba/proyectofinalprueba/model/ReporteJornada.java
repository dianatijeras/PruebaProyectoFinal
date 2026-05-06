package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import java.time.LocalDate;
import java.util.*;


public class ReporteJornada {

    private LocalDate fecha;
    private double ingresosDiarios;
    private List<Atraccion> atraccionesMasVisitadas;
    private Map<Atraccion, Double> tiemposPromedioEspera;
    private int cierresPorClima;
    private int alertasMantenimiento;
    private List<Atraccion> atraccionesConMasAccidentes;

    public ReporteJornada(LocalDate fecha) {
        this.fecha = fecha;
        this.ingresosDiarios = 0.0;
        this.atraccionesMasVisitadas = new ArrayList<>();
        this.tiemposPromedioEspera = new LinkedHashMap<>();
        this.cierresPorClima = 0;
        this.alertasMantenimiento = 0;
        this.atraccionesConMasAccidentes = new ArrayList<>();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getIngresosDiarios() {
        return ingresosDiarios;
    }

    public void setIngresosDiarios(double ingresosDiarios) {
        this.ingresosDiarios = ingresosDiarios;
    }

    public List<Atraccion> getAtraccionesMasVisitadas() {
        return atraccionesMasVisitadas;
    }

    public void setAtraccionesMasVisitadas(List<Atraccion> atraccionesMasVisitadas) {
        this.atraccionesMasVisitadas = atraccionesMasVisitadas;
    }

    public Map<Atraccion, Double> getTiemposPromedioEspera() {
        return tiemposPromedioEspera;
    }

    public void setTiemposPromedioEspera(Map<String, Double> tiemposPromedioEspera) {
        this.tiemposPromedioEspera = tiemposPromedioEspera;
    }

    public int getCierresPorClima() {
        return cierresPorClima;
    }

    public void setCierresPorClima(int cierresPorClima) {
        this.cierresPorClima = cierresPorClima;
    }

    public int getAlertasMantenimiento() {
        return alertasMantenimiento;
    }

    public void setAlertasMantenimiento(int alertasMantenimiento) {
        this.alertasMantenimiento = alertasMantenimiento;
    }

    public List<Atraccion> getAtraccionesConMasAccidentes() {
        return atraccionesConMasAccidentes;
    }

    public void setAtraccionesConMasAccidentes(List<Atraccion> atraccionesConMasAccidentes) {
        this.atraccionesConMasAccidentes = atraccionesConMasAccidentes;
    }

    @Override
    public String toString() {
        return "ReporteJornada{" +
                "fecha=" + fecha +
                ", ingresosDiarios=" + ingresosDiarios +
                ", atraccionesMasVisitadas=" + atraccionesMasVisitadas +
                ", tiemposPromedioEspera=" + tiemposPromedioEspera +
                ", cierresPorClima=" + cierresPorClima +
                ", alertasMantenimiento=" + alertasMantenimiento +
                ", atraccionesConMasAccidentes=" + atraccionesConMasAccidentes +
                '}';
    }
}

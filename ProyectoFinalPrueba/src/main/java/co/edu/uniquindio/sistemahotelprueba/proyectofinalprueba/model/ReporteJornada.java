package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ReporteJornada {

    private Date fecha;
    private double ingresosDiarios;
    private List<Atraccion> atraccionesMasVisitadas;
    private Map<Atraccion, Double> tiemposPromedioEspera;
    private int cierresPorClima;
    private int alertasMantenimiento;
    private List<Atraccion> atraccionesConMasAccidentes;
}

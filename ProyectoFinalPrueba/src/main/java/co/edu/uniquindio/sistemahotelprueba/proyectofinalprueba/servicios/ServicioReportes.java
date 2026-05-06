package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Atraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Parque;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.ReporteJornada;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Visitante;

import java.time.LocalDate;
import java.util.*;

public class ServicioReportes {

    private final Parque parque;
    private final ServicioAlertas servicioAlertas;

    public ServicioReportes(Parque parque, ServicioAlertas servicioAlertas){
        this.parque = parque;
        this.servicioAlertas = servicioAlertas;
    }

    public ReporteJornada generarReporteJornada() {
        ReporteJornada reporte = new ReporteJornada(LocalDate.now());

        List<Atraccion> todasAtracciones = parque.todasLasAtracciones();

        Map<String, Double> tiemposPromedio = new LinkedHashMap<>();
        for (Atraccion a : todasAtracciones) {
            tiemposPromedio.put(a.getNombre(), (double) a.getTiempoEstimadoEspera());
        }
        reporte.setTiemposPromedioEspera(tiemposPromedio);

        double[] ingresos = {0.0}; // Array para mutarlo en lambda
        Map<String, Integer> conteoVisitas = new HashMap<>();

        parque.getCatalogoUsuarios().forEachInorden(usuario -> {
            if (usuario instanceof Visitante) {
                Visitante v = (Visitante) usuario;
                v.getHistorialVisitas().forEach(reg -> {
                    ingresos[0] += reg.getCostoDeducido();
                    String nombreAtrac = reg.getAtraccion().getNombre();
                    conteoVisitas.merge(nombreAtrac, 1, Integer::sum);
                });
            }
        });
        reporte.setIngresosDiarios(ingresos[0]);

        List<Atraccion> masVisitadas = new ArrayList<>(todasAtracciones);
        masVisitadas.sort((a1, a2) ->
                Integer.compare(a2.getContadorAcumuladoVisitantes(),
                        a1.getContadorAcumuladoVisitantes())
        );
        reporte.setAtraccionesMasVisitadas(masVisitadas.subList(0, Math.min(5, masVisitadas.size())));

        reporte.setCierresPorClima(servicioAlertas.getHistorialClimatico().size());

        reporte.setAlertasMantenimiento(servicioAlertas.getTotalAlertasMantenimiento());

        List<Atraccion> masIncidentes = new ArrayList<>(todasAtracciones);
        masIncidentes.sort((a1, a2) ->
                Integer.compare(a2.getContadorAcumuladoVisitantes(),
                        a1.getContadorAcumuladoVisitantes())
        );
        reporte.setAtraccionesConMasAccidentes(
                masIncidentes.subList(0, Math.min(3, masIncidentes.size()))
        );

        return reporte;
    }


}

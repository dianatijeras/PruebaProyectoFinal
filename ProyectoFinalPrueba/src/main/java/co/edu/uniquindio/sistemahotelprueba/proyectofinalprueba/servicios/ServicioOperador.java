package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.ResultadoRevision;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.*;

import javax.xml.transform.Result;
import java.awt.image.RescaleOp;

public class ServicioOperador {

    private final ServicioAlertas servicioAlertas;
    private final ServicioAcceso servicioAcceso;
    private final ServicioColas servicioColas;

    private int contadorRevisiones = 0;

    public ServicioOperador(ServicioAlertas servicioAlertas, ServicioAcceso servicioAcceso, ServicioColas servicioColas) {
        this.servicioAlertas = servicioAlertas;
        this.servicioAcceso = servicioAcceso;
        this.servicioColas = servicioColas;
    }

    public ResultadoAcceso procesarAcceso(Operador operador, Visitante visitante, Atraccion atraccion, Ticket ticket){
        if(!operador.tieneAutoridadSobre(atraccion)) {
            System.out.println("[OPERADOR] " + operador.getNombre() + " no tiene autoridad sobre '" + atraccion.getNombre() + "'");
            return ResultadoAcceso.denegado("operador no autorizado para esta atracción");
        }

        return servicioAcceso.procesarAcceso(visitante, atraccion, ticket);

    }

    public EntradaEnCola llamarSiguienteEnCola(Operador operador, Atraccion atraccion) {
        if (!operador.tieneAutoridadSobre(atraccion)) {
            System.out.println("[OPERADOR] Sin autoridad.");
            return null;
        }
        return servicioColas.llamarSiguiente(atraccion);
    }

    public boolean cambiarEstadoAtraccion(Operador operador, Atraccion atraccion,
                                          EstadoAtraccion nuevoEstado, String motivo) {
        if (!operador.tieneAutoridadSobre(atraccion)) {
            System.out.println("[OPERADOR]" + operador.getNombre() +
                    " sin autoridad sobre '" + atraccion.getNombre() + "'");
            return false;
        }

        if (nuevoEstado == EstadoAtraccion.CERRADA &&
                (motivo == null || motivo.trim().isEmpty())) {
            System.out.println("[OPERADOR] El motivo de cierre es obligatorio.");
            return false;
        }

        atraccion.cambiarEstado(nuevoEstado, motivo);
        System.out.println("[OPERADOR]" + operador.getNombre() + " cambió estado de '" +
                atraccion.getNombre() + "' a " + nuevoEstado +
                (motivo != null ? " (" + motivo + ")" : ""));
        return true;
    }

    public RevisionTecnica registrarRevisionTecnica(Operador operador,
                                                    Atraccion atraccion,
                                                    String descripcion,
                                                    ResultadoRevision resultado) {
        if (!operador.tieneAutoridadSobre(atraccion)) {
            System.out.println("[OPERADOR] Sin autoridad para revisar '" +
                    atraccion.getNombre() + "'");
            return null;
        }

        String id = "REV-" + (++contadorRevisiones);
        RevisionTecnica revision = new RevisionTecnica(id, atraccion, operador,
                descripcion, resultado);

        // Delegar al servicio de alertas para que reactive si corresponde
        servicioAlertas.procesarRevisionTecnica(revision);

        System.out.println("[OPERADOR]" + operador.getNombre() +
                " registró revisión en '" + atraccion.getNombre() +
                "': " + resultado);
        return revision;
    }
}

package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoClima;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoNotif;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.ColaPrioridad;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.*;

import java.util.ArrayList;
import java.util.List;

public class ServicioAlertas {

    private final ColaPrioridad<AlertaMantenimiento> colaMantenimiento;

    private final List<AlertaClimatica> historialClimatico;

    private int contadorAlertasMant = 0;
    private int contadorAlertasClim = 0;

    public ServicioAlertas() {
        this.colaMantenimiento = new ColaPrioridad<>();
        this.historialClimatico = new ArrayList<>();
    }

    public AlertaMantenimiento crearAlertaMantenimiento(Atraccion atraccion) {
        String id = "ALM-" + (++contadorAlertasMant);
        AlertaMantenimiento alerta = new AlertaMantenimiento(id, atraccion);
        colaMantenimiento.insertar(alerta);
        System.out.println("[ALERTA] Mantenimiento requerido: '" + atraccion.getNombre() + "' bloqueada. Alerta: " + id);
        return alerta;
    }

    public boolean procesarRevisionTecnica(RevisionTecnica revision) {
        if (!revision.esSatisfactoria()) {
            System.out.println("[ALERTA] Revision FALLIDA en '" + revision.getAtraccion().getNombre() + "'. Permanece bloqueada.");
            return false;
        }
        Atraccion atraccion = revision.getAtraccion();

        List<AlertaMantenimiento> todas = colaMantenimiento.obtenerTodos();
        AlertaMantenimiento alertaCorrespondiente = null;
        for (AlertaMantenimiento alerta : todas) {
            if (alerta.getAtraccion().getId().equals(atraccion.getId()) && !alerta.isAtendida()) {
                alertaCorrespondiente = alerta;
                break;
            }
        }

        if (alertaCorrespondiente != null) {
            alertaCorrespondiente.marcarAtendida();
        }

        atraccion.resetearContador();
        atraccion.cambiarEstado(EstadoAtraccion.ACTIVA, null);

        System.out.println("[ALERTA] Revisión SATISFACTORIA: '" +
                atraccion.getNombre() + "' reactivada. Contador reseteado.");
        return true;
    }

    public AlertaMantenimiento verProximaAlerta() {
        return colaMantenimiento.peek();
    }

    public AlertaMantenimiento extraerAlertaUrgente() {
        return colaMantenimiento.extraerMinimo();
    }

    public AlertaClimatica activarAlertaClimatica(TipoClima tipo, List<Atraccion> todasLasAtracciones) {
        String id = "ALC-" + (++contadorAlertasClim);
        AlertaClimatica alerta = new AlertaClimatica(id, tipo);

        int cerradas = 0;
        for (Atraccion atraccion : todasLasAtracciones) {
            if (atraccion.getTipo() == TipoAtraccion.ACUATICA ||
                    atraccion.getTipo() == TipoAtraccion.MECANICA_ALTURA) {

                if (atraccion.getEstado() == EstadoAtraccion.ACTIVA) {
                    notificarVisitantesEnCola(atraccion, tipo);

                    atraccion.cambiarEstado(EstadoAtraccion.CERRADA,
                            "Alerta climática: " + tipo);
                    alerta.agregarAfectada(atraccion);
                    cerradas++;
                }
            }
        }

        historialClimatico.add(alerta);

        System.out.println("[CLIMA] Alerta " + tipo + " activada. " +
                cerradas + " atracciones cerradas.");
        return alerta;
    }

    private void notificarVisitantesEnCola(Atraccion atraccion, TipoClima tipoClima) {
        ColaPrioridad<EntradaEnCola> cola = atraccion.getColaVirtual();
        if (cola.estaVacia()) return;

        String mensajeNotif = "La atracción '" + atraccion.getNombre() +
                "' fue cerrada por alerta climática: " + tipoClima;

        Notificacion notif = new Notificacion(
                "NOTIF-" + System.currentTimeMillis(),
                mensajeNotif,
                TipoNotif.CLIMA
        );

        List<EntradaEnCola> enCola = cola.obtenerTodos();
        for (EntradaEnCola entrada : enCola) {
            notif.agregarDestinatario(entrada.getVisitante());
            entrada.getVisitante().recibirNotificacion(notif);
        }

        cola.vaciar();

        System.out.println("[CLIMA]   → " + enCola.size() + " visitantes notificados y removidos de cola en '" +
                atraccion.getNombre() + "'");
    }

    public ColaPrioridad<AlertaMantenimiento> getColaMantenimiento() {
        return colaMantenimiento;
    }

    public List<AlertaClimatica> getHistorialClimatico() {
        return historialClimatico;
    }

    public int getTotalAlertasMantenimiento() {
        return contadorAlertasMant;
    }

    public int getTotalAlertasClimaticas() {
        return contadorAlertasClim;
    }


}



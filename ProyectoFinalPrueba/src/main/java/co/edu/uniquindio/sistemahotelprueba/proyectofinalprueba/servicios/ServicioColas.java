package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Atraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.EntradaEnCola;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Ticket;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.Visitante;

public class ServicioColas {

    public boolean unirseACola(Visitante visitante, Atraccion atraccion, Ticket ticket) {

        // RN-C05: verificar estado
        if (atraccion.getEstado() != EstadoAtraccion.ACTIVA) {
            System.out.println("[COLA] " + visitante.getNombre() +
                    " no puede unirse: '" + atraccion.getNombre() +
                    "' no está activa (" + atraccion.getEstado() + ")");
            return false;
        }

        // RN-T05: verificar ticket activo
        if (!ticket.estaActivo()) {
            System.out.println("[COLA] Ticket inactivo: " + ticket.getEstado());
            return false;
        }

        EntradaEnCola entrada = new EntradaEnCola(visitante, ticket);
        atraccion.agregarAColaCola(entrada);

        System.out.println("[COLA] " + visitante.getNombre() +
                " en cola de '" + atraccion.getNombre() +
                "' (prioridad=" + entrada.getPrioridad() +
                ", posicion aprox.=" + atraccion.getColaVirtual().getTamanio() +
                ", espera=" + atraccion.getTiempoEstimadoEspera() + " min)");
        return true;
    }

    public EntradaEnCola llamarSiguiente(Atraccion atraccion) {
        EntradaEnCola entrada = atraccion.extraerSiguienteDeCola();

        if (entrada == null) {
            System.out.println("[COLA] Cola vacía en '" + atraccion.getNombre() + "'");
            return null;
        }

        System.out.println("[COLA] Llamando a: " + entrada.getVisitante().getNombre() +
                " (tipo=" + entrada.getTicket().getTipo() + ")");
        return entrada;
    }

    public EntradaEnCola verProximo(Atraccion atraccion) {
        return atraccion.getColaVirtual().peek();
    }

    public void imprimirCola(Atraccion atraccion) {
        System.out.println("=== Cola de '" + atraccion.getNombre() + "' (" +
                atraccion.getColaVirtual().getTamanio() + " en espera) ===");
        System.out.println("  Tiempo estimado: " + atraccion.getTiempoEstimadoEspera() + " min");
        System.out.println("  " + atraccion.getColaVirtual());
    }
}

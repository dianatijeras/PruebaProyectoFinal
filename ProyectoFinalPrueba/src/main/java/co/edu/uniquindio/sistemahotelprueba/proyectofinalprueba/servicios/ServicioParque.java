package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;


import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoClima;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoTicket;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.*;


public class ServicioParque {

    private final Parque parque;
    private final ServicioAlertas servicioAlertas;

    private int contadorTickets = 0;

    public ServicioParque(Parque parque, ServicioAlertas servicioAlertas) {
        this.parque = parque;
        this.servicioAlertas = servicioAlertas;
    }

    public Ticket venderTicket(Visitante visitante, TipoTicket tipo) {

        if (!parque.tieneAforoDisponible()) {
            System.out.println("[PARQUE] Aforo máximo alcanzado. No se puede vender ticket.");
            return null;
        }

        double precio = calcularPrecio(tipo);

        String id = "TKT-" + (++contadorTickets);
        Ticket ticket = new Ticket(id, tipo, precio, visitante);
        visitante.setTicketActivo(ticket);

        if (parque.buscarUsuarioPorDocumento(visitante.getDocumento()) == null) {
            parque.registrarUsuario(visitante);
        }

        System.out.println("[PARQUE] Ticket vendido: " + ticket +
                " a " + visitante.getNombre() +
                " por $" + precio);
        return ticket;
    }

    public boolean registrarIngreso(Visitante visitante, String idZonaEntrada) {
        if (visitante.getTicketActivo() == null ||
                !visitante.getTicketActivo().estaActivo()) {
            System.out.println("[PARQUE]" + visitante.getNombre() +
                    " no tiene ticket activo.");
            return false;
        }

        if (!parque.tieneAforoDisponible()) {
            System.out.println("[PARQUE] Aforo máximo alcanzado.");
            return false;
        }

        Zona zona = parque.buscarZona(idZonaEntrada);
        if (zona != null) {
            if (!zona.tieneAforoDisponible()) {
                System.out.println("[PARQUE] Zona '" + zona.getNombre() + "' llena.");
                return false;
            }
            zona.incrementarAforo();
        }

        parque.incrementarAforo();
        visitante.entrarAlParque(zona);

        System.out.println("[PARQUE] " + visitante.getNombre() +
                " ingresó al parque" +
                (zona != null ? " → zona '" + zona.getNombre() + "'" : ""));
        return true;
    }

    public void registrarSalida(Visitante visitante) {
        if (visitante.getZonaActual() != null) {
            visitante.getZonaActual().decrementarAforo();
        }
        parque.decrementarAforo();
        visitante.salirDelParque();
        System.out.println("[PARQUE]" + visitante.getNombre() + " salió del parque.");
    }

    public boolean asignarOperador(Operador operador, String idZona) {
        Zona nuevaZona = parque.buscarZona(idZona);
        if (nuevaZona == null) {
            System.out.println("[PARQUE] Zona no encontrada: " + idZona);
            return false;
        }

        Zona zonaActual = operador.getZonaAsignada();
        if (zonaActual != null && !zonaActual.getId().equals(idZona)) {
            boolean quitado = zonaActual.quitarOperador(operador);
            if (!quitado) {
                System.out.println("[PARQUE] No se puede reasignar: zona '" +
                        zonaActual.getNombre() + "' quedaría sin operador.");
                return false;
            }
        }

        nuevaZona.agregarOperador(operador);
        System.out.println("[PARQUE] " + operador.getNombre() +
                " asignado a zona '" + nuevaZona.getNombre() + "'");
        return true;
    }

    public AlertaClimatica activarAlertaClimatica(TipoClima tipo) {
        return servicioAlertas.activarAlertaClimatica(tipo, parque.todasLasAtracciones());
    }

    private double calcularPrecio(TipoTicket tipo) {
        switch (tipo) {
            case FAST_PASS: return 80_000.0;
            case FAMILIAR:  return 55_000.0;
            case GENERAL:
            default:        return 45_000.0;
        }
    }


    public Parque getParque() {
        return parque;
    }

    public void imprimirEstadoParque() {
        System.out.println("\n" + parque);
        System.out.println("Zonas:");
        for (Zona z : parque.getZonas()) {
            System.out.println("  " + z);
            for (Atraccion a : z.getAtracciones()) {
                System.out.println("    " + a);
            }
        }
    }
}

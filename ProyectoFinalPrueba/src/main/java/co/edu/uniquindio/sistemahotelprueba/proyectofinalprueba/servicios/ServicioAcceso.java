package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoTicket;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.*;

public class ServicioAcceso {

    private final ServicioAlertas servicioAlertas;

    public ServicioAcceso(ServicioAlertas servicioAlertas){
        this.servicioAlertas = servicioAlertas;
    }

    public ResultadoAcceso procesarAcceso(Visitante visitante, Atraccion atraccion, Ticket ticket){
        if(atraccion.getEstado() != EstadoAtraccion.ACTIVA){
            return ResultadoAcceso.denegado(
                    "la atraccion '" + atraccion.getNombre() + "' no esta activa. " + "Estado actual: " +atraccion.getEstado() + (atraccion.getMotivoCierre() != null ? " (" + atraccion.getMotivoCierre() + ")" : "")

            );
        }

        if (visitante.getEdad() < atraccion.getEdadMinima()) {
            return ResultadoAcceso.denegado(
                    "Edad insuficiente. Requerida: " + atraccion.getEdadMinima() +
                            " años. Visitante: " + visitante.getEdad() + " años."
            );
        }

        if (visitante.getEstatura() < atraccion.getAlturaMinima()) {
            return ResultadoAcceso.denegado(
                    "Estatura insuficiente. Requerida: " + atraccion.getAlturaMinima() +
                            "m. Visitante: " + visitante.getEstatura() + "m."
            );
        }

        double costoDeducido = 0.0;
        if (atraccion.getCostoAdicional() > 0 &&
                ticket.getTipo() == TipoTicket.GENERAL) {

            if (!visitante.tieneSaldoSuficiente(atraccion.getCostoAdicional())) {
                return ResultadoAcceso.denegado(
                        "Saldo insuficiente. Requerido: $" + atraccion.getCostoAdicional() +
                                ". Saldo actual: $" + String.format("%.2f", visitante.getSaldoVirtual())
                );
            }
            visitante.descontarSaldo(atraccion.getCostoAdicional());
            costoDeducido = atraccion.getCostoAdicional();
        }

        RegistroVisita registro = new RegistroVisita(atraccion, ticket, costoDeducido);
        visitante.agregarVisita(registro);

        // Incrementar contador y verificar si se alcanzaron los 500 visitantes (RN-A02)
        boolean disparoMantenimiento = atraccion.registrarIngreso();
        if (disparoMantenimiento) {
            servicioAlertas.crearAlertaMantenimiento(atraccion);
        }

        System.out.println("[ACCESO] ✅ " + visitante.getNombre() +
                " ingresó a '" + atraccion.getNombre() + "'" +
                (costoDeducido > 0 ? " — cobrado $" + costoDeducido : ""));

        return ResultadoAcceso.autorizado();

    }
}

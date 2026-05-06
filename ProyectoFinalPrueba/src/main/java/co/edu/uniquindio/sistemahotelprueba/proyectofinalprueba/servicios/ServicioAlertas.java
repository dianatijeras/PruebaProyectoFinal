package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.servicios;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.ColaPrioridad;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.AlertaClimatica;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model.AlertaMantenimiento;

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
}

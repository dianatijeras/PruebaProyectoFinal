package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Atraccion {
    private String id;
    private String nombre;
    private TipoAtraccion tipo;
    private int capacidadMaximaPorCiclo;
    private double alturaMinima;
    private int edadMinima;
    private double costoAdicional;
    private int contadorAcumuladoVisitantes;
    private int tiempoEstimadoEspera;
    private EstadoAtraccion estado;
    private String motivoCierre;
    private Zona zona;
    private ColaPrioridad<EntradaEnCola> colaVirtual;

}

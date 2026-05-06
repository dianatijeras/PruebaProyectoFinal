package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.EstadoTicket;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.TipoAtraccion;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.ColaPrioridad;


public class Atraccion {

    public static final int LIMITE_MANTENIMIENTO = 500;
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

    private final ColaPrioridad<EntradaEnCola> colaVirtual;

    public Atraccion(String id, String nombre, TipoAtraccion tipo, int capacidadMaximaPorCiclo, double alturaMinima, int edadMinima, double costoAdicional) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidadMaximaPorCiclo = capacidadMaximaPorCiclo;
        this.alturaMinima = alturaMinima;
        this.edadMinima = edadMinima;
        this.costoAdicional = costoAdicional;
        this.contadorAcumuladoVisitantes = 0;
        this.tiempoEstimadoEspera = 0;
        this.estado = EstadoAtraccion.ACTIVA;
        this.motivoCierre = null;
        this.zona = null;
        this.colaVirtual = new ColaPrioridad<>();
    }

    public void cambiarEstado(EstadoAtraccion nuevoEstado, String motivo){
        this.estado = nuevoEstado;
        if(nuevoEstado == EstadoAtraccion.CERRADA || nuevoEstado == EstadoAtraccion.EN_MANTENIMIENTO){
            this.motivoCierre = (motivo != null) ? motivo : "sin especificar";
        } else {
            this.motivoCierre = null;
        }
    }

    public boolean estaActiva(){
        return estado == EstadoAtraccion.ACTIVA;
    }

    public boolean registrarIngreso(){
        contadorAcumuladoVisitantes++;
        if(contadorAcumuladoVisitantes >= LIMITE_MANTENIMIENTO){
            cambiarEstado(EstadoAtraccion.EN_MANTENIMIENTO, "Mantenimiento preventivo: 500 visitantes acumulados");
            return true;
        }
        return false;
    }

    public void resetearContador() {
        contadorAcumuladoVisitantes = 0;
    }


    public void agregarAColaCola(EntradaEnCola entrada){
        colaVirtual.insertar(entrada);
        actualizarTiempoEspera();
    }

    public EntradaEnCola extraerSiguienteDeCola(){
        EntradaEnCola entrada = colaVirtual.extraerMinimo();
        actualizarTiempoEspera();
        return entrada;
    }

    public void actualizarTiempoEspera(){
        if(capacidadMaximaPorCiclo > 0){
            int ciclosNecesarios = (int) Math.ceil((double) colaVirtual.getTamanio() / capacidadMaximaPorCiclo);
            this.tiempoEstimadoEspera = ciclosNecesarios * 5;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAtraccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtraccion tipo) {
        this.tipo = tipo;
    }

    public int getCapacidadMaximaPorCiclo() {
        return capacidadMaximaPorCiclo;
    }

    public void setCapacidadMaximaPorCiclo(int capacidadMaximaPorCiclo) {
        this.capacidadMaximaPorCiclo = capacidadMaximaPorCiclo;
    }

    public double getAlturaMinima() {
        return alturaMinima;
    }

    public void setAlturaMinima(double alturaMinima) {
        this.alturaMinima = alturaMinima;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    public double getCostoAdicional() {
        return costoAdicional;
    }

    public void setCostoAdicional(double costoAdicional) {
        this.costoAdicional = costoAdicional;
    }

    public int getContadorAcumuladoVisitantes() {
        return contadorAcumuladoVisitantes;
    }

    public void setContadorAcumuladoVisitantes(int contadorAcumuladoVisitantes) {
        this.contadorAcumuladoVisitantes = contadorAcumuladoVisitantes;
    }

    public int getTiempoEstimadoEspera() {
        return tiempoEstimadoEspera;
    }

    public void setTiempoEstimadoEspera(int tiempoEstimadoEspera) {
        this.tiempoEstimadoEspera = tiempoEstimadoEspera;
    }

    public EstadoAtraccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAtraccion estado) {
        this.estado = estado;
    }

    public String getMotivoCierre() {
        return motivoCierre;
    }

    public void setMotivoCierre(String motivoCierre) {
        this.motivoCierre = motivoCierre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public ColaPrioridad<EntradaEnCola> getColaVirtual() {
        return colaVirtual;
    }

    @Override
    public String toString() {
        return "Atraccion{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                ", capacidadMaximaPorCiclo=" + capacidadMaximaPorCiclo +
                ", alturaMinima=" + alturaMinima +
                ", edadMinima=" + edadMinima +
                ", costoAdicional=" + costoAdicional +
                ", contadorAcumuladoVisitantes=" + contadorAcumuladoVisitantes +
                ", tiempoEstimadoEspera=" + tiempoEstimadoEspera +
                ", estado=" + estado +
                ", motivoCierre='" + motivoCierre + '\'' +
                ", zona=" + zona +
                ", colaVirtual=" + colaVirtual +
                '}';
    }
}

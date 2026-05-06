package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.Rol;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.ListaEnlazada;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.SetArbol;

import java.util.List;

public class Visitante extends Usuario{

    private double estatura;
    private double saldoVirtual;
    private String foto;
    private boolean dentroDelParque;
    private Zona zonaActual;
    private Ticket ticketActivo;

    private final ListaEnlazada<RegistroVisita> historialVisitas;
    private final SetArbol<Atraccion> favoritos;
    private final ListaEnlazada<Notificacion> notificaciones;

    public Visitante(String idUsuario, String nombre, String documento, int edad, String password, Rol rol, double estatura, double saldoVirtual, String foto) {
        super(idUsuario, nombre, documento, edad, password, Rol.VISITANTE);
        this.estatura = estatura;
        this.saldoVirtual = saldoVirtual;
        this.foto = foto;
        this.dentroDelParque = false;
        this.zonaActual = null;
        this.ticketActivo = null;
        this.historialVisitas = new ListaEnlazada<>();
        this.favoritos = new SetArbol<>();
        this.notificaciones = new ListaEnlazada<>();
    }

    public boolean descontarSaldo(double monto){
        if(saldoVirtual >= monto){
            saldoVirtual -= monto;
            return true;
        }
        return false;
    }

    public void recargarSaldo(double monto){
        if(monto > 0){
            saldoVirtual += monto;
        }
    }

    public boolean tieneSaldoSuficiente(double monto){
        return saldoVirtual >= monto;
    }

    public void agregarVisita(RegistroVisita registro){
        historialVisitas.insertarAlFrente(registro);
    }

    public ListaEnlazada<RegistroVisita> getHistorialVisitas(){
        return historialVisitas;
    }

    public void agregarFavorito(Atraccion atraccion){
        favoritos.agregar(atraccion.getId(), atraccion);
    }

    public boolean quitarFavorito(String idAtraccion){
        return favoritos.eliminar(idAtraccion);
    }

    public boolean esFavorita(String idAtraccion){
        return favoritos.contiene(idAtraccion);
    }

    public List<Atraccion> getFavoritos(){
        return favoritos.listar();
    }

    public void recibirNotificacion(Notificacion notificacion){
        notificaciones.insertarAlFrente(notificacion);
    }

    public ListaEnlazada<Notificacion> getNotificaciones(){
        return notificaciones;
    }

    public void entrarAlParque (Zona zona){
        this.dentroDelParque = true;
        this.zonaActual = zona;
    }

    public void salirDelParque (){
        this.dentroDelParque = false;
        this.zonaActual = null;
    }

    public void cambiarDeZona(Zona nuevaZona){
        this.zonaActual = nuevaZona;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getSaldoVirtual() {
        return saldoVirtual;
    }

    public void setSaldoVirtual(double saldoVirtual) {
        this.saldoVirtual = saldoVirtual;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isDentroDelParque() {
        return dentroDelParque;
    }

    public void setDentroDelParque(boolean dentroDelParque) {
        this.dentroDelParque = dentroDelParque;
    }

    public Zona getZonaActual() {
        return zonaActual;
    }

    public void setZonaActual(Zona zonaActual) {
        this.zonaActual = zonaActual;
    }

    public Ticket getTicketActivo() {
        return ticketActivo;
    }

    public void setTicketActivo(Ticket ticketActivo) {
        this.ticketActivo = ticketActivo;
    }

    @Override
    public String toString() {
        return "Visitante{" +
                "estatura=" + estatura +
                ", saldoVirtual=" + saldoVirtual +
                ", foto='" + foto + '\'' +
                ", dentroDelParque=" + dentroDelParque +
                ", zonaActual=" + zonaActual +
                ", ticketActivo=" + ticketActivo +
                ", historialVisitas=" + historialVisitas +
                ", favoritos=" + favoritos +
                ", notificaciones=" + notificaciones +
                '}';
    }
}

package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.ArbolBST;
import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Estructuras.Grafo;

import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.filter;

public class Parque {
    private String id;
    private String nombre;
    private int capacidadMaxima;
    private int aforoActual;
    private List<Zona> zonas;
    private Grafo mapa;

    private final ArbolBST<Atraccion> catalogoAtracciones;
    private final ArbolBST<Usuario> catalogoUsuarios;

    public Parque(String id, String nombre, int capacidadMaxima) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.aforoActual = 0;
        this.zonas = new ArrayList<>();
        this.mapa = new Grafo();
        this.catalogoAtracciones = new ArbolBST<>();
        this.catalogoUsuarios = new ArbolBST<>();
    }

    public void agregarZona(Zona zona){
        zonas.add(zona);
    }

    public Zona buscarZona(String idZona) {
        return zonas.stream()
                .filter(z -> z.getId().equals(idZona))
                .findFirst()
                .orElse(null);
    }

    public void registrarAtraccion(Atraccion atraccion){
        catalogoAtracciones.insertar(atraccion.getId(), atraccion);
        mapa.agregarNodo(atraccion);
    }

    public Atraccion buscarAtraccion(String idAtraccion){
        return catalogoAtracciones.buscar(idAtraccion);
    }

    public List<Atraccion> todasLasAtracciones(){
        return catalogoAtracciones.inorden();
    }

    public void registrarUsuario(Usuario usuario){
        catalogoUsuarios.insertar(usuario.getDocumento(), usuario);
    }

    public Usuario buscarUsuarioPorDocumento(String idUsuario){
        return catalogoUsuarios.buscar(idUsuario);
    }

    public boolean tieneAforoDisponible(){
        return aforoActual < capacidadMaxima;
    }

    public void incrementarAforo(){
        if(aforoActual < capacidadMaxima){
            aforoActual++;
        }
    }

    public void decrementarAforo(){
        if(aforoActual > 0){
            aforoActual--;
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

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getAforoActual() {
        return aforoActual;
    }

    public void setAforoActual(int aforoActual) {
        this.aforoActual = aforoActual;
    }

    public List<Zona> getZonas() {
        return zonas;
    }

    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    public Grafo getMapa() {
        return mapa;
    }

    public void setMapa(Grafo mapa) {
        this.mapa = mapa;
    }

    public ArbolBST<Atraccion> getCatalogoAtracciones() {
        return catalogoAtracciones;
    }

    public ArbolBST<Usuario> getCatalogoUsuarios() {
        return catalogoUsuarios;
    }

    @Override
    public String toString() {
        return "Parque{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacidadMaxima=" + capacidadMaxima +
                ", aforoActual=" + aforoActual +
                ", zonas=" + zonas +
                ", mapa=" + mapa +
                ", catalogoAtracciones=" + catalogoAtracciones +
                ", catalogoUsuarios=" + catalogoUsuarios +
                '}';
    }
}

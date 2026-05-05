package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.Rol;

public class Operador extends Usuario{

    private Zona zonaAsignada;

    public Operador(String idUsuario, String nombre, String documento, int edad, String password, Rol rol, Zona zonaAsignada) {
        super(idUsuario, nombre, documento, edad, password, Rol.OPERADOR);
        this.zonaAsignada = null;
    }

    public boolean tieneAutoridadSobre(Atraccion atraccion){
        if(zonaAsignada == null){
            return false;
        }
        return zonaAsignada.contieneAtraccion(atraccion.getId());
    }

    public Zona getZonaAsignada() {
        return zonaAsignada;
    }

    public void setZonaAsignada(Zona zonaAsignada) {
        this.zonaAsignada = zonaAsignada;
    }

    @Override
    public String toString() {
        return "Operador{" +
                "zonaAsignada=" + zonaAsignada +
                '}';
    }
}

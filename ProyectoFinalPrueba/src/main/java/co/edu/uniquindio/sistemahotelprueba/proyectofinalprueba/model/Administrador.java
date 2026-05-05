package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.Enum.Rol;

public class Administrador extends Usuario{

    private Parque parqueGestionado;

    public Administrador(String idUsuario, String nombre, String documento, int edad, String password, Rol rol, Parque parqueGestionado) {
        super(idUsuario, nombre, documento, edad, password, Rol.ADMINISTRADOR);
        this.parqueGestionado = null;
    }

    public Parque getParqueGestionado() {
        return parqueGestionado;
    }

    public void setParqueGestionado(Parque parqueGestionado) {
        this.parqueGestionado = parqueGestionado;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "parqueGestionado=" + parqueGestionado +
                '}';
    }
}

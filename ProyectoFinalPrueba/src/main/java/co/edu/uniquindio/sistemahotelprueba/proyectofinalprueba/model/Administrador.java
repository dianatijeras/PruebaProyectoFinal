package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrador extends Usuario{

    private Parque parqueGestionado;

    public Administrador(String idUsuario, String nombre, String documento, int edad, String password, RolEnum rol, Parque parqueGestionado) {
        super(idUsuario, nombre, documento, edad, password, rol);
        this.parqueGestionado = parqueGestionado;
    }
}

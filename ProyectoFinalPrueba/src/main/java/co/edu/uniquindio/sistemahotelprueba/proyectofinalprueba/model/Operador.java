package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Operador extends Usuario{

    private Zona zonaAsignada;

    public Operador(String idUsuario, String nombre, String documento, int edad, String password, RolEnum rol, Zona zonaAsignada) {
        super(idUsuario, nombre, documento, edad, password, rol);
        this.zonaAsignada = zonaAsignada;
    }
}

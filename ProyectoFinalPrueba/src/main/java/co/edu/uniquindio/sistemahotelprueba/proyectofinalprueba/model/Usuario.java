package co.edu.uniquindio.sistemahotelprueba.proyectofinalprueba.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Usuario {
    private String idUsuario;
    private String nombre;
    private String documento;
    private int edad;
    private String password;
    private RolEnum rol;
}

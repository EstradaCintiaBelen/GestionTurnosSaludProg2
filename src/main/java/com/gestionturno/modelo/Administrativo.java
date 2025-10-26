package com.gestionturno.modelo;

/**
 * Clase que representa un administrativo del sistema
 * Gestiona usuarios y turnos
 */
public class Administrativo extends Usuario {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public Administrativo() {
        super();
    }

    /**
     * Constructor con parámetros
     */
    public Administrativo(String dni, String apellido, String nombre, String userName, String password) {
        super(dni, apellido, nombre, userName, password);
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "dni='" + getDni() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", userName='" + getUserName() + '\'' +
                '}';
    }
}

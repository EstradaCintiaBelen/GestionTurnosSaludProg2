package com.gestionturno.modelo;

/**
 * Clase que representa un paciente del sistema
 */
public class Paciente extends Usuario {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto
     */
    public Paciente() {
        super();
    }

    /**
     * Constructor con parámetros
     */
    public Paciente(String dni, String apellido, String nombre, String userName, String password) {
        super(dni, apellido, nombre, userName, password);
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "dni='" + getDni() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", userName='" + getUserName() + '\'' +
                '}';
    }
}

package com.gestionturno.modelo;

/**
 * Clase que representa un médico del sistema
 */
public class Medico extends Usuario {
    private static final long serialVersionUID = 1L;
    
    private boolean disponibilidad;
    private Especialidad especialidad;

    /**
     * Constructor por defecto
     */
    public Medico() {
        super();
        this.disponibilidad = true;
    }

    /**
     * Constructor con parámetros
     */
    public Medico(String dni, String apellido, String nombre, String userName, String password, 
                  boolean disponibilidad, Especialidad especialidad) {
        super(dni, apellido, nombre, userName, password);
        this.disponibilidad = disponibilidad;
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "dni='" + getDni() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", disponibilidad=" + disponibilidad +
                ", especialidad=" + especialidad +
                '}';
    }
}

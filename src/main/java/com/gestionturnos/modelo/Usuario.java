package com.gestionturnos.modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase abstracta que representa un usuario del sistema
 */
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String dni;
    private String apellido;
    private String nombre;
    private String userName;
    private String password;

    /**
     * Constructor por defecto
     */
    public Usuario() {
    }

    /**
     * Constructor con parámetros
     */
    public Usuario(String dni, String apellido, String nombre, String userName, String password) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.userName = userName;
        this.password = password;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

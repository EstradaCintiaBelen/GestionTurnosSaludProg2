package com.gestionturnos.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un administrativo del sistema
 * que gestiona usuarios y turnos
 */
public class Administrativo extends Usuario {
    private static final long serialVersionUID = 1L;
    
    private List<Usuario> usuariosGestionados;
    private List<Turno> turnosGestionados;

    /**
     * Constructor por defecto
     */
    public Administrativo() {
        super();
        this.usuariosGestionados = new ArrayList<>();
        this.turnosGestionados = new ArrayList<>();
    }

    /**
     * Constructor con parámetros
     */
    public Administrativo(String dni, String apellido, String nombre, String userName, String password) {
        super(dni, apellido, nombre, userName, password);
        this.usuariosGestionados = new ArrayList<>();
        this.turnosGestionados = new ArrayList<>();
    }

    // Métodos para gestionar usuarios
    public boolean agregarUsuario(Usuario usuario) {
        if (usuario == null || usuariosGestionados.contains(usuario)) {
            return false;
        }
        return usuariosGestionados.add(usuario);
    }

    public boolean eliminarUsuario(Usuario usuario) {
        return usuariosGestionados.remove(usuario);
    }

    public List<Usuario> getUsuariosGestionados() {
        return new ArrayList<>(usuariosGestionados);
    }

    // Métodos para gestionar turnos
    public boolean agregarTurno(Turno turno) {
        if (turno == null || turnosGestionados.contains(turno)) {
            return false;
        }
        return turnosGestionados.add(turno);
    }

    public boolean eliminarTurno(Turno turno) {
        return turnosGestionados.remove(turno);
    }

    public List<Turno> getTurnosGestionados() {
        return new ArrayList<>(turnosGestionados);
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "dni='" + getDni() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", usuariosGestionados=" + usuariosGestionados.size() +
                ", turnosGestionados=" + turnosGestionados.size() +
                '}';
    }
}

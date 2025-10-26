package com.gestionturno.negocio;

import com.gestionturno.modelo.Usuario;
import com.gestionturno.modelo.Paciente;
import com.gestionturno.modelo.Medico;
import com.gestionturno.modelo.Administrativo;
import com.gestionturno.persistencia.PersistenciaUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase de la capa de negocio para gestionar usuarios
 */
public class GestionUsuario {
    private List<Usuario> usuarios;
    private PersistenciaUsuario persistenciaUsuario;

    /**
     * Constructor
     */
    public GestionUsuario() {
        this.usuarios = new ArrayList<>();
        this.persistenciaUsuario = new PersistenciaUsuario();
        cargarUsuarios();
    }

    /**
     * Agrega un nuevo usuario
     */
    public boolean agregarUsuario(Usuario usuario) {
        if (usuario != null && !existeUsuario(usuario.getDni())) {
            usuarios.add(usuario);
            guardarUsuarios();
            return true;
        }
        return false;
    }

    /**
     * Verifica si existe un usuario con el DNI especificado
     */
    private boolean existeUsuario(String dni) {
        return usuarios.stream().anyMatch(u -> u.getDni().equals(dni));
    }

    /**
     * Elimina un usuario
     */
    public boolean eliminarUsuario(String dni) {
        boolean resultado = usuarios.removeIf(u -> u.getDni().equals(dni));
        if (resultado) {
            guardarUsuarios();
        }
        return resultado;
    }

    /**
     * Busca un usuario por DNI
     */
    public Optional<Usuario> buscarUsuarioPorDni(String dni) {
        return usuarios.stream()
                .filter(u -> u.getDni().equals(dni))
                .findFirst();
    }

    /**
     * Busca un usuario por nombre de usuario
     */
    public Optional<Usuario> buscarUsuarioPorUserName(String userName) {
        return usuarios.stream()
                .filter(u -> u.getUserName().equals(userName))
                .findFirst();
    }

    /**
     * Autentica un usuario
     */
    public Optional<Usuario> autenticarUsuario(String userName, String password) {
        return usuarios.stream()
                .filter(u -> u.getUserName().equals(userName) && u.getPassword().equals(password))
                .findFirst();
    }

    /**
     * Obtiene todos los usuarios
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Obtiene todos los pacientes
     */
    public List<Paciente> getPacientes() {
        return usuarios.stream()
                .filter(u -> u instanceof Paciente)
                .map(u -> (Paciente) u)
                .toList();
    }

    /**
     * Obtiene todos los médicos
     */
    public List<Medico> getMedicos() {
        return usuarios.stream()
                .filter(u -> u instanceof Medico)
                .map(u -> (Medico) u)
                .toList();
    }

    /**
     * Obtiene todos los administrativos
     */
    public List<Administrativo> getAdministrativos() {
        return usuarios.stream()
                .filter(u -> u instanceof Administrativo)
                .map(u -> (Administrativo) u)
                .toList();
    }

    /**
     * Guarda los usuarios en el sistema de persistencia
     */
    private void guardarUsuarios() {
        persistenciaUsuario.guardarUsuarios(usuarios);
    }

    /**
     * Carga los usuarios desde el sistema de persistencia
     */
    private void cargarUsuarios() {
        List<Usuario> usuariosCargados = persistenciaUsuario.cargarUsuarios();
        if (usuariosCargados != null) {
            this.usuarios = usuariosCargados;
        }
    }
}

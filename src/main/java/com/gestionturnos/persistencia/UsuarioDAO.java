package com.gestionturnos.persistencia;

import com.gestionturnos.modelo.Usuario;

/**
 * DAO específico para gestionar la persistencia de usuarios
 */
public class UsuarioDAO extends DAO<Usuario> {
    private static final String ARCHIVO_USUARIOS = "usuarios.dat";

    /**
     * Constructor por defecto
     */
    public UsuarioDAO() {
        super(ARCHIVO_USUARIOS);
    }

    /**
     * Constructor con archivo personalizado
     */
    public UsuarioDAO(String archivo) {
        super(archivo);
    }
}

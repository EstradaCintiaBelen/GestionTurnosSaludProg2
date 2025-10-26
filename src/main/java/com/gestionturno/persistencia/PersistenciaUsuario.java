package com.gestionturno.persistencia;

import com.gestionturno.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la capa de persistencia para gestionar el almacenamiento de usuarios
 */
public class PersistenciaUsuario {
    private static final String ARCHIVO_USUARIOS = "usuarios.dat";

    /**
     * Guarda la lista de usuarios en un archivo
     */
    public void guardarUsuarios(List<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_USUARIOS))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de usuarios desde un archivo
     */
    @SuppressWarnings("unchecked")
    public List<Usuario> cargarUsuarios() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Elimina el archivo de persistencia de usuarios
     */
    public boolean eliminarArchivo() {
        File archivo = new File(ARCHIVO_USUARIOS);
        if (archivo.exists()) {
            return archivo.delete();
        }
        return false;
    }
}

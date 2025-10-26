package com.gestionturnos.persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica para persistencia de datos usando serialización
 */
public class DAO<T> {
    private String archivo;

    /**
     * Constructor con nombre de archivo
     */
    public DAO(String archivo) {
        this.archivo = archivo;
    }

    /**
     * Guarda una lista de objetos en el archivo
     */
    public boolean guardar(List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar datos: " + e.getMessage());
            return false;
        }
    }

    /**
     * Carga una lista de objetos desde el archivo
     */
    @SuppressWarnings("unchecked")
    public List<T> cargar() {
        File file = new File(archivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Elimina el archivo de persistencia
     */
    public boolean eliminar() {
        File file = new File(archivo);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * Verifica si el archivo existe
     */
    public boolean existe() {
        return new File(archivo).exists();
    }
}

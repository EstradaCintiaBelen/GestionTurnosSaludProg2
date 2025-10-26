package com.gestionturno.persistencia;

import com.gestionturno.modelo.Turno;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de la capa de persistencia para gestionar el almacenamiento de turnos
 */
public class PersistenciaTurno {
    private static final String ARCHIVO_TURNOS = "turnos.dat";

    /**
     * Guarda la lista de turnos en un archivo
     */
    public void guardarTurnos(List<Turno> turnos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_TURNOS))) {
            oos.writeObject(turnos);
        } catch (IOException e) {
            System.err.println("Error al guardar turnos: " + e.getMessage());
        }
    }

    /**
     * Carga la lista de turnos desde un archivo
     */
    @SuppressWarnings("unchecked")
    public List<Turno> cargarTurnos() {
        File archivo = new File(ARCHIVO_TURNOS);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_TURNOS))) {
            return (List<Turno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar turnos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Elimina el archivo de persistencia de turnos
     */
    public boolean eliminarArchivo() {
        File archivo = new File(ARCHIVO_TURNOS);
        if (archivo.exists()) {
            return archivo.delete();
        }
        return false;
    }
}

package com.gestionturnos.persistencia;

import com.gestionturnos.modelo.Turno;

/**
 * DAO específico para gestionar la persistencia de turnos
 */
public class TurnoDAO extends DAO<Turno> {
    private static final String ARCHIVO_TURNOS = "turnos.dat";

    /**
     * Constructor por defecto
     */
    public TurnoDAO() {
        super(ARCHIVO_TURNOS);
    }

    /**
     * Constructor con archivo personalizado
     */
    public TurnoDAO(String archivo) {
        super(archivo);
    }
}

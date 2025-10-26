package com.gestionturnos.negocio;

import com.gestionturnos.modelo.Turno;
import com.gestionturnos.modelo.Medico;
import com.gestionturnos.modelo.Paciente;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de negocio para gestionar turnos
 */
public class GestionTurno {
    private List<Turno> turnos;

    /**
     * Constructor por defecto
     */
    public GestionTurno() {
        this.turnos = new ArrayList<>();
    }

    /**
     * Constructor con lista de turnos
     */
    public GestionTurno(List<Turno> turnos) {
        this.turnos = turnos != null ? turnos : new ArrayList<>();
    }

    /**
     * Agrega un nuevo turno
     */
    public boolean agregarTurno(Turno turno) {
        if (turno == null) {
            return false;
        }
        // Verificar que no exista un turno duplicado
        if (existeTurno(turno)) {
            return false;
        }
        return turnos.add(turno);
    }

    /**
     * Elimina un turno
     */
    public boolean eliminarTurno(Turno turno) {
        return turnos.remove(turno);
    }

    /**
     * Verifica si existe un turno
     */
    public boolean existeTurno(Turno turno) {
        return turnos.contains(turno);
    }

    /**
     * Obtiene todos los turnos
     */
    public List<Turno> getTurnos() {
        return new ArrayList<>(turnos);
    }

    /**
     * Obtiene los turnos de un médico específico
     */
    public List<Turno> getTurnosPorMedico(Medico medico) {
        return turnos.stream()
                .filter(t -> t.getMedico().equals(medico))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los turnos de un paciente específico
     */
    public List<Turno> getTurnosPorPaciente(Paciente paciente) {
        return turnos.stream()
                .filter(t -> t.getPaciente().equals(paciente))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los turnos en un rango de fechas
     */
    public List<Turno> getTurnosPorRangoFecha(LocalDateTime inicio, LocalDateTime fin) {
        return turnos.stream()
                .filter(t -> !t.getFechaYHora().isBefore(inicio) && !t.getFechaYHora().isAfter(fin))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene la cantidad total de turnos
     */
    public int cantidadTurnos() {
        return turnos.size();
    }
}

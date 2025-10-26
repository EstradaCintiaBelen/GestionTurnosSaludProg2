package com.gestionturno.negocio;

import com.gestionturno.modelo.Turno;
import com.gestionturno.modelo.Medico;
import com.gestionturno.modelo.Paciente;
import com.gestionturno.persistencia.PersistenciaTurno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de la capa de negocio para gestionar turnos
 */
public class GestionTurno {
    private List<Turno> turnos;
    private PersistenciaTurno persistenciaTurno;

    /**
     * Constructor
     */
    public GestionTurno() {
        this.turnos = new ArrayList<>();
        this.persistenciaTurno = new PersistenciaTurno();
        cargarTurnos();
    }

    /**
     * Agrega un nuevo turno
     */
    public boolean agregarTurno(Turno turno) {
        if (turno != null && turno.getMedico() != null && turno.getPaciente() != null && turno.getFechaYHora() != null) {
            if (verificarDisponibilidad(turno)) {
                turnos.add(turno);
                guardarTurnos();
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si el médico está disponible en la fecha y hora especificada
     */
    private boolean verificarDisponibilidad(Turno nuevoTurno) {
        if (!nuevoTurno.getMedico().isDisponibilidad()) {
            return false;
        }
        
        for (Turno turno : turnos) {
            if (turno.getMedico().getDni().equals(nuevoTurno.getMedico().getDni()) &&
                turno.getFechaYHora().equals(nuevoTurno.getFechaYHora())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Elimina un turno
     */
    public boolean eliminarTurno(Turno turno) {
        boolean resultado = turnos.remove(turno);
        if (resultado) {
            guardarTurnos();
        }
        return resultado;
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
                .filter(t -> t.getMedico().getDni().equals(medico.getDni()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los turnos de un paciente específico
     */
    public List<Turno> getTurnosPorPaciente(Paciente paciente) {
        return turnos.stream()
                .filter(t -> t.getPaciente().getDni().equals(paciente.getDni()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene los turnos en un rango de fechas
     */
    public List<Turno> getTurnosPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return turnos.stream()
                .filter(t -> !t.getFechaYHora().isBefore(inicio) && !t.getFechaYHora().isAfter(fin))
                .collect(Collectors.toList());
    }

    /**
     * Guarda los turnos en el sistema de persistencia
     */
    private void guardarTurnos() {
        persistenciaTurno.guardarTurnos(turnos);
    }

    /**
     * Carga los turnos desde el sistema de persistencia
     */
    private void cargarTurnos() {
        List<Turno> turnosCargados = persistenciaTurno.cargarTurnos();
        if (turnosCargados != null) {
            this.turnos = turnosCargados;
        }
    }
}

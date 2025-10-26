package com.gestionturnos.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase que representa un turno médico
 */
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Medico medico;
    private Paciente paciente;
    private LocalDateTime fechaYHora;

    /**
     * Constructor por defecto
     */
    public Turno() {
    }

    /**
     * Constructor con parámetros
     */
    public Turno(Medico medico, Paciente paciente, LocalDateTime fechaYHora) {
        this.medico = medico;
        this.paciente = paciente;
        this.fechaYHora = fechaYHora;
    }

    // Getters y Setters
    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return Objects.equals(medico, turno.medico) &&
                Objects.equals(paciente, turno.paciente) &&
                Objects.equals(fechaYHora, turno.fechaYHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medico, paciente, fechaYHora);
    }

    @Override
    public String toString() {
        return "Turno{" +
                "medico=" + medico +
                ", paciente=" + paciente +
                ", fechaYHora=" + fechaYHora +
                '}';
    }
}

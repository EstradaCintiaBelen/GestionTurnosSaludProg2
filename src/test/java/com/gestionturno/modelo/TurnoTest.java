package com.gestionturno.modelo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TurnoTest {

    @Test
    void testTurnoCreation() {
        Medico medico = new Medico("12345678", "García", "María", "mgarcia", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("23456789", "López", "Ana", "alopez", "pac123");
        LocalDateTime fechaHora = LocalDateTime.of(2025, 10, 27, 10, 0);
        
        Turno turno = new Turno(medico, paciente, fechaHora);
        
        assertNotNull(turno);
        assertEquals(medico, turno.getMedico());
        assertEquals(paciente, turno.getPaciente());
        assertEquals(fechaHora, turno.getFechaYHora());
    }

    @Test
    void testTurnoDefaultConstructor() {
        Turno turno = new Turno();
        assertNotNull(turno);
        assertNull(turno.getMedico());
        assertNull(turno.getPaciente());
        assertNull(turno.getFechaYHora());
    }

    @Test
    void testTurnoSetters() {
        Turno turno = new Turno();
        Medico medico = new Medico("12345678", "García", "María", "mgarcia", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("23456789", "López", "Ana", "alopez", "pac123");
        LocalDateTime fechaHora = LocalDateTime.of(2025, 10, 27, 10, 0);
        
        turno.setMedico(medico);
        turno.setPaciente(paciente);
        turno.setFechaYHora(fechaHora);
        
        assertEquals(medico, turno.getMedico());
        assertEquals(paciente, turno.getPaciente());
        assertEquals(fechaHora, turno.getFechaYHora());
    }
}

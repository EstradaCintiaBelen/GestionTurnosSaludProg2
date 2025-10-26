package com.gestionturnos.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

class TurnoTest {

    @Test
    void testConstructorPorDefecto() {
        Turno turno = new Turno();
        assertNotNull(turno);
    }

    @Test
    void testConstructorConParametros() {
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        
        Turno turno = new Turno(medico, paciente, fecha);
        
        assertEquals(medico, turno.getMedico());
        assertEquals(paciente, turno.getPaciente());
        assertEquals(fecha, turno.getFechaYHora());
    }

    @Test
    void testSettersYGetters() {
        Turno turno = new Turno();
        
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.PEDIATRIA);
        Paciente paciente = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha = LocalDateTime.of(2024, 11, 15, 14, 0);
        
        turno.setMedico(medico);
        turno.setPaciente(paciente);
        turno.setFechaYHora(fecha);
        
        assertEquals(medico, turno.getMedico());
        assertEquals(paciente, turno.getPaciente());
        assertEquals(fecha, turno.getFechaYHora());
    }

    @Test
    void testEquals() {
        Medico medico1 = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                    true, Especialidad.CARDIOLOGIA);
        Paciente paciente1 = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        
        Turno turno1 = new Turno(medico1, paciente1, fecha1);
        Turno turno2 = new Turno(medico1, paciente1, fecha1);
        
        assertEquals(turno1, turno2);
    }

    @Test
    void testNotEquals() {
        Medico medico1 = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                    true, Especialidad.CARDIOLOGIA);
        Medico medico2 = new Medico("11111111", "López", "Ana", "alopez", "med456", 
                                    true, Especialidad.PEDIATRIA);
        Paciente paciente1 = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        LocalDateTime fecha2 = LocalDateTime.of(2024, 10, 27, 15, 0);
        
        Turno turno1 = new Turno(medico1, paciente1, fecha1);
        Turno turno2 = new Turno(medico2, paciente1, fecha1);
        Turno turno3 = new Turno(medico1, paciente1, fecha2);
        
        assertNotEquals(turno1, turno2); // Diferente médico
        assertNotEquals(turno1, turno3); // Diferente fecha
    }

    @Test
    void testHashCode() {
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        
        Turno turno1 = new Turno(medico, paciente, fecha);
        Turno turno2 = new Turno(medico, paciente, fecha);
        
        assertEquals(turno1.hashCode(), turno2.hashCode());
    }

    @Test
    void testToString() {
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        
        Turno turno = new Turno(medico, paciente, fecha);
        String resultado = turno.toString();
        
        assertNotNull(resultado);
        assertTrue(resultado.contains("Turno"));
    }
}

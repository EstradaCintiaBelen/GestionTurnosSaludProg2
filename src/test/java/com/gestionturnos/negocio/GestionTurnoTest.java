package com.gestionturnos.negocio;

import com.gestionturnos.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GestionTurnoTest {

    private GestionTurno gestionTurno;
    private Medico medico1;
    private Medico medico2;
    private Paciente paciente1;
    private Paciente paciente2;

    @BeforeEach
    void setUp() {
        gestionTurno = new GestionTurno();
        
        medico1 = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                            true, Especialidad.CARDIOLOGIA);
        medico2 = new Medico("11111111", "López", "Ana", "alopez", "med456", 
                            true, Especialidad.PEDIATRIA);
        
        paciente1 = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        paciente2 = new Paciente("99999999", "Rodríguez", "Carlos", "crodriguez", "pass456");
    }

    @Test
    void testConstructorPorDefecto() {
        GestionTurno gt = new GestionTurno();
        assertNotNull(gt);
        assertEquals(0, gt.cantidadTurnos());
    }

    @Test
    void testAgregarTurno() {
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        Turno turno = new Turno(medico1, paciente1, fecha);
        
        assertTrue(gestionTurno.agregarTurno(turno));
        assertEquals(1, gestionTurno.cantidadTurnos());
    }

    @Test
    void testAgregarTurnoNulo() {
        assertFalse(gestionTurno.agregarTurno(null));
        assertEquals(0, gestionTurno.cantidadTurnos());
    }

    @Test
    void testAgregarTurnoDuplicado() {
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        Turno turno = new Turno(medico1, paciente1, fecha);
        
        assertTrue(gestionTurno.agregarTurno(turno));
        assertFalse(gestionTurno.agregarTurno(turno)); // No debe agregar duplicado
        assertEquals(1, gestionTurno.cantidadTurnos());
    }

    @Test
    void testEliminarTurno() {
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        Turno turno = new Turno(medico1, paciente1, fecha);
        
        gestionTurno.agregarTurno(turno);
        assertEquals(1, gestionTurno.cantidadTurnos());
        
        assertTrue(gestionTurno.eliminarTurno(turno));
        assertEquals(0, gestionTurno.cantidadTurnos());
    }

    @Test
    void testExisteTurno() {
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        Turno turno = new Turno(medico1, paciente1, fecha);
        
        assertFalse(gestionTurno.existeTurno(turno));
        
        gestionTurno.agregarTurno(turno);
        assertTrue(gestionTurno.existeTurno(turno));
    }

    @Test
    void testGetTurnos() {
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        LocalDateTime fecha2 = LocalDateTime.of(2024, 10, 27, 14, 0);
        
        Turno turno1 = new Turno(medico1, paciente1, fecha1);
        Turno turno2 = new Turno(medico2, paciente2, fecha2);
        
        gestionTurno.agregarTurno(turno1);
        gestionTurno.agregarTurno(turno2);
        
        List<Turno> turnos = gestionTurno.getTurnos();
        assertEquals(2, turnos.size());
        assertTrue(turnos.contains(turno1));
        assertTrue(turnos.contains(turno2));
    }

    @Test
    void testGetTurnosPorMedico() {
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        LocalDateTime fecha2 = LocalDateTime.of(2024, 10, 27, 14, 0);
        LocalDateTime fecha3 = LocalDateTime.of(2024, 10, 28, 16, 0);
        
        Turno turno1 = new Turno(medico1, paciente1, fecha1);
        Turno turno2 = new Turno(medico2, paciente2, fecha2);
        Turno turno3 = new Turno(medico1, paciente2, fecha3);
        
        gestionTurno.agregarTurno(turno1);
        gestionTurno.agregarTurno(turno2);
        gestionTurno.agregarTurno(turno3);
        
        List<Turno> turnosMedico1 = gestionTurno.getTurnosPorMedico(medico1);
        assertEquals(2, turnosMedico1.size());
        assertTrue(turnosMedico1.contains(turno1));
        assertTrue(turnosMedico1.contains(turno3));
        
        List<Turno> turnosMedico2 = gestionTurno.getTurnosPorMedico(medico2);
        assertEquals(1, turnosMedico2.size());
        assertTrue(turnosMedico2.contains(turno2));
    }

    @Test
    void testGetTurnosPorPaciente() {
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        LocalDateTime fecha2 = LocalDateTime.of(2024, 10, 27, 14, 0);
        LocalDateTime fecha3 = LocalDateTime.of(2024, 10, 28, 16, 0);
        
        Turno turno1 = new Turno(medico1, paciente1, fecha1);
        Turno turno2 = new Turno(medico2, paciente2, fecha2);
        Turno turno3 = new Turno(medico2, paciente1, fecha3);
        
        gestionTurno.agregarTurno(turno1);
        gestionTurno.agregarTurno(turno2);
        gestionTurno.agregarTurno(turno3);
        
        List<Turno> turnosPaciente1 = gestionTurno.getTurnosPorPaciente(paciente1);
        assertEquals(2, turnosPaciente1.size());
        assertTrue(turnosPaciente1.contains(turno1));
        assertTrue(turnosPaciente1.contains(turno3));
    }

    @Test
    void testGetTurnosPorRangoFecha() {
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        LocalDateTime fecha2 = LocalDateTime.of(2024, 10, 27, 14, 0);
        LocalDateTime fecha3 = LocalDateTime.of(2024, 11, 1, 16, 0);
        
        Turno turno1 = new Turno(medico1, paciente1, fecha1);
        Turno turno2 = new Turno(medico2, paciente2, fecha2);
        Turno turno3 = new Turno(medico1, paciente2, fecha3);
        
        gestionTurno.agregarTurno(turno1);
        gestionTurno.agregarTurno(turno2);
        gestionTurno.agregarTurno(turno3);
        
        LocalDateTime inicio = LocalDateTime.of(2024, 10, 26, 0, 0);
        LocalDateTime fin = LocalDateTime.of(2024, 10, 31, 23, 59);
        
        List<Turno> turnosEnRango = gestionTurno.getTurnosPorRangoFecha(inicio, fin);
        assertEquals(2, turnosEnRango.size());
        assertTrue(turnosEnRango.contains(turno1));
        assertTrue(turnosEnRango.contains(turno2));
        assertFalse(turnosEnRango.contains(turno3));
    }

    @Test
    void testCantidadTurnos() {
        assertEquals(0, gestionTurno.cantidadTurnos());
        
        LocalDateTime fecha1 = LocalDateTime.of(2024, 10, 26, 10, 30);
        LocalDateTime fecha2 = LocalDateTime.of(2024, 10, 27, 14, 0);
        
        gestionTurno.agregarTurno(new Turno(medico1, paciente1, fecha1));
        assertEquals(1, gestionTurno.cantidadTurnos());
        
        gestionTurno.agregarTurno(new Turno(medico2, paciente2, fecha2));
        assertEquals(2, gestionTurno.cantidadTurnos());
    }
}

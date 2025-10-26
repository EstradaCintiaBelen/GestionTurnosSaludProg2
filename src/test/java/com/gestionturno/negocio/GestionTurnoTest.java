package com.gestionturno.negocio;

import com.gestionturno.modelo.*;
import com.gestionturno.persistencia.PersistenciaTurno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class GestionTurnoTest {

    private GestionTurno gestionTurno;
    private Medico medico;
    private Paciente paciente;
    private PersistenciaTurno persistencia;

    @BeforeEach
    void setUp() {
        persistencia = new PersistenciaTurno();
        persistencia.eliminarArchivo();
        gestionTurno = new GestionTurno();
        medico = new Medico("12345678", "García", "María", "mgarcia", "med123", 
                           true, Especialidad.CARDIOLOGIA);
        paciente = new Paciente("23456789", "López", "Ana", "alopez", "pac123");
    }

    @AfterEach
    void tearDown() {
        persistencia.eliminarArchivo();
    }

    @Test
    void testAgregarTurnoValido() {
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(1);
        Turno turno = new Turno(medico, paciente, fechaHora);
        
        assertTrue(gestionTurno.agregarTurno(turno));
    }

    @Test
    void testNoAgregarTurnoNulo() {
        assertFalse(gestionTurno.agregarTurno(null));
    }

    @Test
    void testNoAgregarTurnoSinMedico() {
        Turno turno = new Turno(null, paciente, LocalDateTime.now());
        assertFalse(gestionTurno.agregarTurno(turno));
    }

    @Test
    void testNoAgregarTurnoConMedicoNoDisponible() {
        medico.setDisponibilidad(false);
        Turno turno = new Turno(medico, paciente, LocalDateTime.now().plusDays(1));
        
        assertFalse(gestionTurno.agregarTurno(turno));
    }

    @Test
    void testEliminarTurno() {
        LocalDateTime fechaHora = LocalDateTime.now().plusDays(1);
        Turno turno = new Turno(medico, paciente, fechaHora);
        
        gestionTurno.agregarTurno(turno);
        assertTrue(gestionTurno.eliminarTurno(turno));
    }

    @Test
    void testGetTurnos() {
        assertNotNull(gestionTurno.getTurnos());
    }
}

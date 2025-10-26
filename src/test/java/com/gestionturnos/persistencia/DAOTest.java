package com.gestionturnos.persistencia;

import com.gestionturnos.modelo.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {

    private static final String TEST_FILE = "test_data.dat";
    private DAO<Turno> turnoDAO;

    @BeforeEach
    void setUp() {
        turnoDAO = new DAO<>(TEST_FILE);
        // Limpiar archivo de prueba si existe
        new File(TEST_FILE).delete();
    }

    @AfterEach
    void tearDown() {
        // Limpiar archivo de prueba después de cada test
        new File(TEST_FILE).delete();
    }

    @Test
    void testGuardarYCargar() {
        List<Turno> turnos = new ArrayList<>();
        
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        LocalDateTime fecha = LocalDateTime.of(2024, 10, 26, 10, 30);
        
        turnos.add(new Turno(medico, paciente, fecha));
        
        assertTrue(turnoDAO.guardar(turnos));
        
        List<Turno> turnosCargados = turnoDAO.cargar();
        assertEquals(1, turnosCargados.size());
        assertEquals(turnos.get(0), turnosCargados.get(0));
    }

    @Test
    void testCargarArchivoNoExiste() {
        List<Turno> turnos = turnoDAO.cargar();
        assertNotNull(turnos);
        assertEquals(0, turnos.size());
    }

    @Test
    void testGuardarMultiplesElementos() {
        List<Turno> turnos = new ArrayList<>();
        
        Medico medico1 = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                    true, Especialidad.CARDIOLOGIA);
        Medico medico2 = new Medico("11111111", "López", "Ana", "alopez", "med456", 
                                    true, Especialidad.PEDIATRIA);
        Paciente paciente = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        
        turnos.add(new Turno(medico1, paciente, LocalDateTime.of(2024, 10, 26, 10, 30)));
        turnos.add(new Turno(medico2, paciente, LocalDateTime.of(2024, 10, 27, 14, 0)));
        
        assertTrue(turnoDAO.guardar(turnos));
        
        List<Turno> turnosCargados = turnoDAO.cargar();
        assertEquals(2, turnosCargados.size());
    }

    @Test
    void testExiste() {
        assertFalse(turnoDAO.existe());
        
        turnoDAO.guardar(new ArrayList<>());
        
        assertTrue(turnoDAO.existe());
    }

    @Test
    void testEliminar() {
        turnoDAO.guardar(new ArrayList<>());
        assertTrue(turnoDAO.existe());
        
        assertTrue(turnoDAO.eliminar());
        assertFalse(turnoDAO.existe());
    }

    @Test
    void testEliminarArchivoNoExiste() {
        assertFalse(turnoDAO.existe());
        assertFalse(turnoDAO.eliminar());
    }
}

package com.gestionturnos.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void testConstructorPorDefecto() {
        Paciente paciente = new Paciente();
        assertNotNull(paciente);
    }

    @Test
    void testConstructorConParametros() {
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        
        assertEquals("12345678", paciente.getDni());
        assertEquals("González", paciente.getApellido());
        assertEquals("María", paciente.getNombre());
        assertEquals("mgonzalez", paciente.getUserName());
        assertEquals("pass123", paciente.getPassword());
    }

    @Test
    void testSettersYGetters() {
        Paciente paciente = new Paciente();
        
        paciente.setDni("87654321");
        paciente.setApellido("Rodríguez");
        paciente.setNombre("Juan");
        paciente.setUserName("jrodriguez");
        paciente.setPassword("test456");
        
        assertEquals("87654321", paciente.getDni());
        assertEquals("Rodríguez", paciente.getApellido());
        assertEquals("Juan", paciente.getNombre());
        assertEquals("jrodriguez", paciente.getUserName());
        assertEquals("test456", paciente.getPassword());
    }

    @Test
    void testEquals() {
        Paciente paciente1 = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        Paciente paciente2 = new Paciente("12345678", "Pérez", "Ana", "aperez", "pass456");
        Paciente paciente3 = new Paciente("87654321", "González", "María", "mgonzalez", "pass123");
        
        assertEquals(paciente1, paciente2); // Mismo DNI
        assertNotEquals(paciente1, paciente3); // Diferente DNI
    }

    @Test
    void testToString() {
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        String resultado = paciente.toString();
        
        assertTrue(resultado.contains("12345678"));
        assertTrue(resultado.contains("González"));
        assertTrue(resultado.contains("María"));
        assertTrue(resultado.contains("mgonzalez"));
    }
}

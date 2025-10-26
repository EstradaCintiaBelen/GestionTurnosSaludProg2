package com.gestionturno.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testPacienteCreation() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pass123");
        
        assertEquals("12345678", paciente.getDni());
        assertEquals("López", paciente.getApellido());
        assertEquals("Ana", paciente.getNombre());
        assertEquals("alopez", paciente.getUserName());
        assertEquals("pass123", paciente.getPassword());
    }

    @Test
    void testMedicoCreation() {
        Medico medico = new Medico("23456789", "García", "María", "mgarcia", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        
        assertEquals("23456789", medico.getDni());
        assertEquals("García", medico.getApellido());
        assertEquals("María", medico.getNombre());
        assertTrue(medico.isDisponibilidad());
        assertEquals(Especialidad.CARDIOLOGIA, medico.getEspecialidad());
    }

    @Test
    void testAdministrativoCreation() {
        Administrativo admin = new Administrativo("34567890", "Pérez", "Juan", "jperez", "admin123");
        
        assertEquals("34567890", admin.getDni());
        assertEquals("Pérez", admin.getApellido());
        assertEquals("Juan", admin.getNombre());
        assertEquals("jperez", admin.getUserName());
        assertEquals("admin123", admin.getPassword());
    }

    @Test
    void testMedicoDisponibilidadDefault() {
        Medico medico = new Medico();
        assertTrue(medico.isDisponibilidad(), "Disponibilidad debería ser true por defecto");
    }
}

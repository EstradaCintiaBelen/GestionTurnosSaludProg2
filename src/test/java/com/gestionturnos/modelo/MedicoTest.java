package com.gestionturnos.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void testConstructorPorDefecto() {
        Medico medico = new Medico();
        assertNotNull(medico);
        assertTrue(medico.isDisponibilidad()); // Por defecto debe ser true
    }

    @Test
    void testConstructorConParametros() {
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        
        assertEquals("12345678", medico.getDni());
        assertEquals("Pérez", medico.getApellido());
        assertEquals("Juan", medico.getNombre());
        assertEquals("jperez", medico.getUserName());
        assertEquals("med123", medico.getPassword());
        assertTrue(medico.isDisponibilidad());
        assertEquals(Especialidad.CARDIOLOGIA, medico.getEspecialidad());
    }

    @Test
    void testSettersYGetters() {
        Medico medico = new Medico();
        
        medico.setDni("87654321");
        medico.setApellido("López");
        medico.setNombre("Ana");
        medico.setUserName("alopez");
        medico.setPassword("test789");
        medico.setDisponibilidad(false);
        medico.setEspecialidad(Especialidad.PEDIATRIA);
        
        assertEquals("87654321", medico.getDni());
        assertEquals("López", medico.getApellido());
        assertEquals("Ana", medico.getNombre());
        assertEquals("alopez", medico.getUserName());
        assertEquals("test789", medico.getPassword());
        assertFalse(medico.isDisponibilidad());
        assertEquals(Especialidad.PEDIATRIA, medico.getEspecialidad());
    }

    @Test
    void testEspecialidades() {
        Medico medico1 = new Medico();
        medico1.setEspecialidad(Especialidad.TRAUMATOLOGIA);
        assertEquals(Especialidad.TRAUMATOLOGIA, medico1.getEspecialidad());
        
        Medico medico2 = new Medico();
        medico2.setEspecialidad(Especialidad.OFTALMOLOGIA);
        assertEquals(Especialidad.OFTALMOLOGIA, medico2.getEspecialidad());
    }

    @Test
    void testDisponibilidad() {
        Medico medico = new Medico();
        assertTrue(medico.isDisponibilidad()); // Por defecto true
        
        medico.setDisponibilidad(false);
        assertFalse(medico.isDisponibilidad());
        
        medico.setDisponibilidad(true);
        assertTrue(medico.isDisponibilidad());
    }

    @Test
    void testToString() {
        Medico medico = new Medico("12345678", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        String resultado = medico.toString();
        
        assertTrue(resultado.contains("12345678"));
        assertTrue(resultado.contains("Pérez"));
        assertTrue(resultado.contains("Juan"));
        assertTrue(resultado.contains("CARDIOLOGIA"));
    }
}

package com.gestionturnos.modelo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AdministrativoTest {

    private Administrativo admin;

    @BeforeEach
    void setUp() {
        admin = new Administrativo("11111111", "López", "Ana", "alopez", "admin123");
    }

    @Test
    void testConstructorPorDefecto() {
        Administrativo adm = new Administrativo();
        assertNotNull(adm);
        assertNotNull(adm.getUsuariosGestionados());
        assertNotNull(adm.getTurnosGestionados());
        assertEquals(0, adm.getUsuariosGestionados().size());
        assertEquals(0, adm.getTurnosGestionados().size());
    }

    @Test
    void testConstructorConParametros() {
        assertEquals("11111111", admin.getDni());
        assertEquals("López", admin.getApellido());
        assertEquals("Ana", admin.getNombre());
        assertEquals("alopez", admin.getUserName());
        assertEquals("admin123", admin.getPassword());
    }

    @Test
    void testAgregarUsuario() {
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        
        assertTrue(admin.agregarUsuario(paciente));
        assertEquals(1, admin.getUsuariosGestionados().size());
        assertTrue(admin.getUsuariosGestionados().contains(paciente));
    }

    @Test
    void testAgregarUsuarioDuplicado() {
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        
        assertTrue(admin.agregarUsuario(paciente));
        assertFalse(admin.agregarUsuario(paciente)); // No debe agregar duplicado
        assertEquals(1, admin.getUsuariosGestionados().size());
    }

    @Test
    void testAgregarUsuarioNulo() {
        assertFalse(admin.agregarUsuario(null));
        assertEquals(0, admin.getUsuariosGestionados().size());
    }

    @Test
    void testEliminarUsuario() {
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        
        admin.agregarUsuario(paciente);
        assertEquals(1, admin.getUsuariosGestionados().size());
        
        assertTrue(admin.eliminarUsuario(paciente));
        assertEquals(0, admin.getUsuariosGestionados().size());
    }

    @Test
    void testAgregarTurno() {
        Medico medico = new Medico("87654321", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        Turno turno = new Turno(medico, paciente, java.time.LocalDateTime.now());
        
        assertTrue(admin.agregarTurno(turno));
        assertEquals(1, admin.getTurnosGestionados().size());
        assertTrue(admin.getTurnosGestionados().contains(turno));
    }

    @Test
    void testAgregarTurnoDuplicado() {
        Medico medico = new Medico("87654321", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        Turno turno = new Turno(medico, paciente, java.time.LocalDateTime.now());
        
        assertTrue(admin.agregarTurno(turno));
        assertFalse(admin.agregarTurno(turno)); // No debe agregar duplicado
        assertEquals(1, admin.getTurnosGestionados().size());
    }

    @Test
    void testEliminarTurno() {
        Medico medico = new Medico("87654321", "Pérez", "Juan", "jperez", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        Turno turno = new Turno(medico, paciente, java.time.LocalDateTime.now());
        
        admin.agregarTurno(turno);
        assertEquals(1, admin.getTurnosGestionados().size());
        
        assertTrue(admin.eliminarTurno(turno));
        assertEquals(0, admin.getTurnosGestionados().size());
    }

    @Test
    void testGestionMultiplesUsuarios() {
        Paciente paciente1 = new Paciente("11111111", "A", "A", "a", "a");
        Paciente paciente2 = new Paciente("22222222", "B", "B", "b", "b");
        Medico medico = new Medico("33333333", "C", "C", "c", "c", true, Especialidad.PEDIATRIA);
        
        admin.agregarUsuario(paciente1);
        admin.agregarUsuario(paciente2);
        admin.agregarUsuario(medico);
        
        assertEquals(3, admin.getUsuariosGestionados().size());
    }
}

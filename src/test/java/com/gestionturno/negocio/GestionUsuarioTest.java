package com.gestionturno.negocio;

import com.gestionturno.modelo.*;
import com.gestionturno.persistencia.PersistenciaUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class GestionUsuarioTest {

    private GestionUsuario gestionUsuario;
    private PersistenciaUsuario persistencia;

    @BeforeEach
    void setUp() {
        persistencia = new PersistenciaUsuario();
        persistencia.eliminarArchivo();
        gestionUsuario = new GestionUsuario();
    }

    @AfterEach
    void tearDown() {
        persistencia.eliminarArchivo();
    }

    @Test
    void testAgregarPaciente() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        assertTrue(gestionUsuario.agregarUsuario(paciente));
    }

    @Test
    void testAgregarMedico() {
        Medico medico = new Medico("23456789", "García", "María", "mgarcia", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        assertTrue(gestionUsuario.agregarUsuario(medico));
    }

    @Test
    void testAgregarAdministrativo() {
        Administrativo admin = new Administrativo("34567890", "Pérez", "Juan", "jperez", "admin123");
        assertTrue(gestionUsuario.agregarUsuario(admin));
    }

    @Test
    void testNoAgregarUsuarioDuplicado() {
        Paciente paciente1 = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        Paciente paciente2 = new Paciente("12345678", "Martínez", "Pedro", "pmartinez", "pac456");
        
        assertTrue(gestionUsuario.agregarUsuario(paciente1));
        assertFalse(gestionUsuario.agregarUsuario(paciente2), "No debería agregar usuario con DNI duplicado");
    }

    @Test
    void testBuscarUsuarioPorDni() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        gestionUsuario.agregarUsuario(paciente);
        
        Optional<Usuario> resultado = gestionUsuario.buscarUsuarioPorDni("12345678");
        assertTrue(resultado.isPresent());
        assertEquals("López", resultado.get().getApellido());
    }

    @Test
    void testBuscarUsuarioPorUserName() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        gestionUsuario.agregarUsuario(paciente);
        
        Optional<Usuario> resultado = gestionUsuario.buscarUsuarioPorUserName("alopez");
        assertTrue(resultado.isPresent());
        assertEquals("López", resultado.get().getApellido());
    }

    @Test
    void testAutenticarUsuario() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        gestionUsuario.agregarUsuario(paciente);
        
        Optional<Usuario> resultado = gestionUsuario.autenticarUsuario("alopez", "pac123");
        assertTrue(resultado.isPresent());
    }

    @Test
    void testAutenticarUsuarioPasswordIncorrecta() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        gestionUsuario.agregarUsuario(paciente);
        
        Optional<Usuario> resultado = gestionUsuario.autenticarUsuario("alopez", "wrongpass");
        assertFalse(resultado.isPresent());
    }

    @Test
    void testEliminarUsuario() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        gestionUsuario.agregarUsuario(paciente);
        
        assertTrue(gestionUsuario.eliminarUsuario("12345678"));
    }

    @Test
    void testGetPacientes() {
        Paciente paciente = new Paciente("12345678", "López", "Ana", "alopez", "pac123");
        gestionUsuario.agregarUsuario(paciente);
        
        assertEquals(1, gestionUsuario.getPacientes().size());
    }

    @Test
    void testGetMedicos() {
        Medico medico = new Medico("23456789", "García", "María", "mgarcia", "med123", 
                                   true, Especialidad.CARDIOLOGIA);
        gestionUsuario.agregarUsuario(medico);
        
        assertEquals(1, gestionUsuario.getMedicos().size());
    }

    @Test
    void testGetAdministrativos() {
        Administrativo admin = new Administrativo("34567890", "Pérez", "Juan", "jperez", "admin123");
        gestionUsuario.agregarUsuario(admin);
        
        assertEquals(1, gestionUsuario.getAdministrativos().size());
    }
}

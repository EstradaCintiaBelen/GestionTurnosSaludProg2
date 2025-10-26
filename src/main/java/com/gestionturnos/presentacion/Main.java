package com.gestionturnos.presentacion;

import com.gestionturnos.modelo.*;
import com.gestionturnos.negocio.GestionTurno;
import com.gestionturnos.persistencia.UsuarioDAO;
import com.gestionturnos.persistencia.TurnoDAO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase principal de la aplicación - Capa de presentación
 * Esta es una demostración básica del sistema
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión de Turnos de Salud ===");
        System.out.println("Aplicación desarrollada con arquitectura de 3 capas");
        System.out.println();
        
        // Demostración de creación de objetos
        demonstrarCreacionObjetos();
        
        // Demostración de gestión de turnos
        demonstrarGestionTurnos();
        
        // Demostración de persistencia
        demonstrarPersistencia();
        
        System.out.println("\nSistema iniciado correctamente.");
    }
    
    private static void demonstrarCreacionObjetos() {
        System.out.println("--- Demostración de creación de objetos ---");
        
        // Crear pacientes
        Paciente paciente1 = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        System.out.println("Paciente creado: " + paciente1);
        
        // Crear médico
        Medico medico1 = new Medico("87654321", "Pérez", "Juan", "jperez", "medico123", 
                                    true, Especialidad.CARDIOLOGIA);
        System.out.println("Médico creado: " + medico1);
        
        // Crear administrativo
        Administrativo admin = new Administrativo("11111111", "López", "Ana", "alopez", "admin123");
        System.out.println("Administrativo creado: " + admin);
        System.out.println();
    }
    
    private static void demonstrarGestionTurnos() {
        System.out.println("--- Demostración de gestión de turnos ---");
        
        // Crear objetos
        Paciente paciente = new Paciente("12345678", "González", "María", "mgonzalez", "pass123");
        Medico medico = new Medico("87654321", "Pérez", "Juan", "jperez", "medico123", 
                                   true, Especialidad.CARDIOLOGIA);
        
        // Crear turno
        Turno turno = new Turno(medico, paciente, LocalDateTime.now().plusDays(1));
        
        // Gestionar turno
        GestionTurno gestionTurno = new GestionTurno();
        boolean agregado = gestionTurno.agregarTurno(turno);
        System.out.println("Turno agregado: " + agregado);
        System.out.println("Cantidad de turnos: " + gestionTurno.cantidadTurnos());
        System.out.println("Turno: " + turno);
        System.out.println();
    }
    
    private static void demonstrarPersistencia() {
        System.out.println("--- Demostración de persistencia ---");
        
        // DAOs
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TurnoDAO turnoDAO = new TurnoDAO();
        
        System.out.println("UsuarioDAO creado - Archivo: usuarios.dat");
        System.out.println("TurnoDAO creado - Archivo: turnos.dat");
        System.out.println("Sistema de persistencia listo para usar");
        System.out.println();
    }
}

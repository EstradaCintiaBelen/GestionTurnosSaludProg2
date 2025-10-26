package com.gestionturno;

import com.gestionturno.modelo.*;
import com.gestionturno.negocio.GestionUsuario;
import com.gestionturno.negocio.GestionTurno;

import java.time.LocalDateTime;

/**
 * Clase principal para demostrar el funcionamiento del sistema
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Gestión de Turnos para Centros de Salud ===\n");

        // Inicializar gestores
        GestionUsuario gestionUsuario = new GestionUsuario();
        GestionTurno gestionTurno = new GestionTurno();

        // Crear usuarios de ejemplo
        Administrativo admin = new Administrativo("12345678", "Pérez", "Juan", "admin", "admin123");
        Medico medico1 = new Medico("23456789", "García", "María", "mgarcia", "med123", true, Especialidad.CARDIOLOGIA);
        Medico medico2 = new Medico("34567890", "Rodríguez", "Carlos", "crodriguez", "med456", true, Especialidad.PEDIATRIA);
        Paciente paciente1 = new Paciente("45678901", "López", "Ana", "alopez", "pac123");
        Paciente paciente2 = new Paciente("56789012", "Martínez", "Pedro", "pmartinez", "pac456");

        // Agregar usuarios al sistema
        System.out.println("Agregando usuarios al sistema...");
        gestionUsuario.agregarUsuario(admin);
        gestionUsuario.agregarUsuario(medico1);
        gestionUsuario.agregarUsuario(medico2);
        gestionUsuario.agregarUsuario(paciente1);
        gestionUsuario.agregarUsuario(paciente2);

        // Mostrar usuarios registrados
        System.out.println("\nUsuarios registrados:");
        System.out.println("Administrativos: " + gestionUsuario.getAdministrativos().size());
        System.out.println("Médicos: " + gestionUsuario.getMedicos().size());
        System.out.println("Pacientes: " + gestionUsuario.getPacientes().size());

        // Crear turnos de ejemplo
        System.out.println("\nCreando turnos...");
        Turno turno1 = new Turno(medico1, paciente1, LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));
        Turno turno2 = new Turno(medico2, paciente2, LocalDateTime.now().plusDays(2).withHour(14).withMinute(30));
        Turno turno3 = new Turno(medico1, paciente2, LocalDateTime.now().plusDays(3).withHour(11).withMinute(0));

        // Agregar turnos
        gestionTurno.agregarTurno(turno1);
        gestionTurno.agregarTurno(turno2);
        gestionTurno.agregarTurno(turno3);

        // Mostrar turnos
        System.out.println("\nTurnos registrados: " + gestionTurno.getTurnos().size());
        for (Turno turno : gestionTurno.getTurnos()) {
            System.out.println("- " + turno);
        }

        // Mostrar turnos por médico
        System.out.println("\nTurnos del Dr./Dra. " + medico1.getApellido() + ":");
        for (Turno turno : gestionTurno.getTurnosPorMedico(medico1)) {
            System.out.println("- " + turno);
        }

        // Autenticar usuario
        System.out.println("\nPrueba de autenticación:");
        gestionUsuario.autenticarUsuario("admin", "admin123")
                .ifPresentOrElse(
                        u -> System.out.println("Usuario autenticado: " + u.getNombre() + " " + u.getApellido()),
                        () -> System.out.println("Usuario no autenticado")
                );

        System.out.println("\n=== Datos guardados exitosamente ===");
    }
}

# Sistema de Gestión de Turnos para Centros de Salud

Sistema de Gestión de Turnos para Centros de Salud - Aplicación de escritorio en Java para gestionar turnos médicos en centros de salud comunitarios.

## Características

- **Java 21**: Proyecto desarrollado con Java 21
- **Arquitectura de 3 capas**: 
  - **Modelo** (Domain): Clases de dominio
  - **Negocio** (Business): Lógica de negocio
  - **Persistencia** (Data): Capa de acceso a datos
- **Persistencia de datos**: Almacenamiento en archivos serializados
- **Gestión de usuarios**: Pacientes, Médicos y Administrativos
- **Gestión de turnos**: Creación, consulta y eliminación de turnos médicos

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── gestionturno/
│               ├── Main.java                    # Clase principal
│               ├── modelo/                      # Capa de Modelo
│               │   ├── Usuario.java            # Clase abstracta padre
│               │   ├── Paciente.java           # Usuario tipo Paciente
│               │   ├── Medico.java             # Usuario tipo Médico
│               │   ├── Administrativo.java     # Usuario tipo Administrativo
│               │   ├── Turno.java              # Clase Turno
│               │   └── Especialidad.java       # Enum de especialidades médicas
│               ├── negocio/                     # Capa de Negocio
│               │   ├── GestionUsuario.java     # Gestión de usuarios
│               │   └── GestionTurno.java       # Gestión de turnos
│               └── persistencia/                # Capa de Persistencia
│                   ├── PersistenciaUsuario.java
│                   └── PersistenciaTurno.java
└── test/
    └── java/
        └── com/
            └── gestionturno/
                ├── modelo/
                │   ├── UsuarioTest.java
                │   └── TurnoTest.java
                └── negocio/
                    ├── GestionUsuarioTest.java
                    └── GestionTurnoTest.java
```

## Clases Principales

### Modelo (Domain Layer)

- **Usuario** (abstracta): Clase padre con atributos dni, apellido, nombre, userName, password
- **Paciente**: Extiende Usuario
- **Medico**: Extiende Usuario, incluye disponibilidad y especialidad
- **Administrativo**: Extiende Usuario, gestiona usuarios y turnos
- **Turno**: Contiene médico, paciente y fechaYHora (LocalDateTime)
- **Especialidad**: Enumerado con especialidades médicas

### Negocio (Business Layer)

- **GestionUsuario**: Maneja operaciones CRUD de usuarios, autenticación, búsqueda
- **GestionTurno**: Maneja operaciones de turnos, verificación de disponibilidad

### Persistencia (Data Layer)

- **PersistenciaUsuario**: Serialización/deserialización de usuarios
- **PersistenciaTurno**: Serialización/deserialización de turnos

## Requisitos

- Java 21 o superior
- Maven 3.6+

## Compilación y Ejecución

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar las pruebas

```bash
mvn test
```

### Ejecutar la aplicación

```bash
mvn exec:java -Dexec.mainClass="com.gestionturno.Main"
```

## Funcionalidades

### Gestión de Usuarios

- Agregar usuarios (Pacientes, Médicos, Administrativos)
- Buscar usuarios por DNI o nombre de usuario
- Autenticar usuarios
- Eliminar usuarios
- Listar usuarios por tipo

### Gestión de Turnos

- Crear turnos médicos
- Verificar disponibilidad de médicos
- Consultar turnos por médico o paciente
- Consultar turnos por rango de fechas
- Eliminar turnos

### Persistencia

- Los datos se guardan automáticamente en archivos `.dat`
- Los datos se cargan automáticamente al iniciar la aplicación

## Ejemplo de Uso

```java
// Inicializar gestores
GestionUsuario gestionUsuario = new GestionUsuario();
GestionTurno gestionTurno = new GestionTurno();

// Crear un médico
Medico medico = new Medico("12345678", "García", "María", "mgarcia", "pass123", 
                           true, Especialidad.CARDIOLOGIA);
gestionUsuario.agregarUsuario(medico);

// Crear un paciente
Paciente paciente = new Paciente("23456789", "López", "Ana", "alopez", "pass456");
gestionUsuario.agregarUsuario(paciente);

// Crear un turno
Turno turno = new Turno(medico, paciente, LocalDateTime.now().plusDays(1));
gestionTurno.agregarTurno(turno);

// Autenticar usuario
Optional<Usuario> usuario = gestionUsuario.autenticarUsuario("alopez", "pass456");
```

## Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

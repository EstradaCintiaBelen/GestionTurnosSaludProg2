# GestionTurnosSaludProg2

Sistema de Gestión de Turnos para Centros de Salud - Aplicación desarrollada en Java 17 para gestionar turnos médicos en centros de salud comunitarios.

## 📋 Descripción

Sistema de gestión de turnos médicos implementado con arquitectura de 3 capas que permite administrar usuarios (pacientes, médicos y administrativos) y gestionar turnos médicos con persistencia de datos.

## 🏗️ Arquitectura

El proyecto está desarrollado siguiendo una arquitectura de 3 capas:

### 1. Capa de Modelo (Dominio)
- **Usuario** (abstract): Clase padre con atributos:
  - dni: String
  - apellido: String
  - nombre: String
  - userName: String
  - password: String
  
- **Paciente**: Clase hija de Usuario
- **Medico**: Clase hija de Usuario con atributos adicionales:
  - disponibilidad: boolean
  - especialidad: Especialidad (enum)
  
- **Administrativo**: Clase hija de Usuario que gestiona:
  - Lista de usuarios
  - Lista de turnos
  
- **Turno**: Clase que representa un turno médico:
  - medico: Medico
  - paciente: Paciente
  - fechaYHora: LocalDateTime
  
- **Especialidad**: Enumerado con especialidades médicas disponibles

### 2. Capa de Negocio
- **GestionTurno**: Clase para gestionar la lógica de negocio de turnos:
  - turnos: List<Turno>
  - Métodos para agregar, eliminar, buscar y filtrar turnos

### 3. Capa de Persistencia
- **DAO<T>**: Clase genérica para persistencia mediante serialización
- **UsuarioDAO**: DAO específico para usuarios
- **TurnoDAO**: DAO específico para turnos

### 4. Capa de Presentación
- **Main**: Clase principal con ejemplos de uso del sistema

## 🛠️ Tecnologías Utilizadas

- **Java 17** (compatible con Java 21)
- **Maven 3.9+** como herramienta de construcción
- **JUnit 5** para pruebas unitarias
- **Serialización Java** para persistencia de datos

## 📦 Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/gestionturnos/
│           ├── modelo/              # Clases de dominio
│           │   ├── Usuario.java     # Clase abstracta padre
│           │   ├── Paciente.java
│           │   ├── Medico.java
│           │   ├── Administrativo.java
│           │   ├── Turno.java
│           │   └── Especialidad.java
│           ├── negocio/             # Lógica de negocio
│           │   └── GestionTurno.java
│           ├── persistencia/        # Capa de datos
│           │   ├── DAO.java
│           │   ├── UsuarioDAO.java
│           │   └── TurnoDAO.java
│           └── presentacion/        # Interfaz de usuario
│               └── Main.java
└── test/
    └── java/
        └── com/gestionturnos/
            ├── modelo/
            │   ├── PacienteTest.java
            │   ├── MedicoTest.java
            │   ├── AdministrativoTest.java
            │   └── TurnoTest.java
            ├── negocio/
            │   └── GestionTurnoTest.java
            └── persistencia/
                └── DAOTest.java
```

## 🚀 Compilación y Ejecución

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior

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
mvn exec:java -Dexec.mainClass="com.gestionturnos.presentacion.Main"
```

### Generar el JAR
```bash
mvn package
```

## 🧪 Pruebas

El proyecto incluye 45 pruebas unitarias que verifican:
- Creación y manipulación de objetos del modelo
- Funcionalidad de gestión de turnos
- Persistencia de datos
- Relaciones entre clases

Ejecutar: `mvn test`

## 📊 Especialidades Médicas Disponibles

- CLINICA_MEDICA
- PEDIATRIA
- CARDIOLOGIA
- TRAUMATOLOGIA
- GINECOLOGIA
- OFTALMOLOGIA
- DERMATOLOGIA
- NEUROLOGIA
- PSIQUIATRIA
- ODONTOLOGIA

## 💾 Persistencia de Datos

El sistema utiliza serialización Java para persistir datos en archivos:
- `usuarios.dat`: Almacena usuarios del sistema
- `turnos.dat`: Almacena turnos médicos

## 🤝 Contribuir

Este es un proyecto educativo desarrollado como parte del curso de Programación 2.

## 📝 Licencia

Proyecto académico - Universidad

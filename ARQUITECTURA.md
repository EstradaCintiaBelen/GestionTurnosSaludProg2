# Arquitectura del Sistema

## Arquitectura de 3 Capas

```
┌─────────────────────────────────────────────────────────────┐
│                     CAPA DE PRESENTACIÓN                     │
│                         (Main.java)                          │
└────────────────────────────┬────────────────────────────────┘
                             │
                             ▼
┌─────────────────────────────────────────────────────────────┐
│                      CAPA DE NEGOCIO                         │
│  ┌──────────────────┐         ┌─────────────────────┐       │
│  │ GestionUsuario   │         │  GestionTurno       │       │
│  │                  │         │                     │       │
│  │ - usuarios: List │         │ - turnos: List      │       │
│  │ + agregarUsuario │         │ + agregarTurno      │       │
│  │ + eliminarUsuario│         │ + eliminarTurno     │       │
│  │ + autenticar     │         │ + getTurnos         │       │
│  │ + buscarPorDni   │         │ + getTurnosPorMedico│       │
│  └────────┬─────────┘         └──────────┬──────────┘       │
└───────────┼────────────────────────────────┼─────────────────┘
            │                                │
            ▼                                ▼
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE PERSISTENCIA                       │
│  ┌──────────────────┐         ┌─────────────────────┐       │
│  │PersistenciaUsuario│         │ PersistenciaTurno  │       │
│  │                  │         │                     │       │
│  │ + guardarUsuarios│         │ + guardarTurnos     │       │
│  │ + cargarUsuarios │         │ + cargarTurnos      │       │
│  │ + eliminarArchivo│         │ + eliminarArchivo   │       │
│  └────────┬─────────┘         └──────────┬──────────┘       │
└───────────┼────────────────────────────────┼─────────────────┘
            │                                │
            ▼                                ▼
      usuarios.dat                      turnos.dat
```

## Modelo de Dominio

```
                    ┌──────────────────────┐
                    │    Usuario           │
                    │   (abstract)         │
                    ├──────────────────────┤
                    │ - dni: String        │
                    │ - apellido: String   │
                    │ - nombre: String     │
                    │ - userName: String   │
                    │ - password: String   │
                    └────────┬─────────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
              ▼              ▼              ▼
      ┌─────────────┐ ┌──────────────────┐ ┌──────────────┐
      │  Paciente   │ │  Medico          │ │Administrativo│
      │             │ │                  │ │              │
      │             │ │+ disponibilidad  │ │              │
      │             │ │+ especialidad    │ │              │
      └─────────────┘ └────┬─────────────┘ └──────────────┘
                           │
                           │ usa
                           │
                    ┌──────▼────────┐
                    │ Especialidad  │
                    │    (enum)     │
                    ├───────────────┤
                    │ CARDIOLOGIA   │
                    │ PEDIATRIA     │
                    │ TRAUMATOLOGIA │
                    │ ...           │
                    └───────────────┘


      ┌─────────────────────────────────┐
      │          Turno                  │
      ├─────────────────────────────────┤
      │ - medico: Medico                │
      │ - paciente: Paciente            │
      │ - fechaYHora: LocalDateTime     │
      └─────────────────────────────────┘
```

## Flujo de Datos

### Crear un Turno

```
Usuario → GestionTurno.agregarTurno()
            ↓
       Validar datos
            ↓
       Verificar disponibilidad médico
            ↓
       Agregar a lista
            ↓
       PersistenciaTurno.guardarTurnos()
            ↓
       turnos.dat
```

### Autenticar Usuario

```
Usuario → GestionUsuario.autenticarUsuario()
            ↓
       PersistenciaUsuario.cargarUsuarios()
            ↓
       usuarios.dat
            ↓
       Buscar en lista
            ↓
       Validar credenciales
            ↓
       Retornar Optional<Usuario>
```

## Responsabilidades por Capa

### Capa de Modelo
- Definir las entidades del dominio
- Implementar lógica básica de las entidades
- Serialización (implementa Serializable)

### Capa de Negocio
- Implementar reglas de negocio
- Validaciones
- Coordinar operaciones entre modelo y persistencia
- Gestionar listas en memoria

### Capa de Persistencia
- Serializar/deserializar objetos
- Manejo de archivos
- Operaciones de I/O

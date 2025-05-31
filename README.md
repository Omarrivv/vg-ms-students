# Microservicio de Gestión de Estudiantes (vg-ms-students)

Microservicio desarrollado con Spring Boot WebFlux y MongoDB para la gestión de estudiantes y sus matrículas en aulas, siguiendo los principios de la arquitectura hexagonal (puertos y adaptadores).

## Arquitectura

El proyecto sigue una arquitectura hexagonal con las siguientes capas:

```
src/main/java/pe/edu/vallegrande/msvstudents/
├── application/
│   ├── dto/
│   │   ├── request/         # DTOs para las solicitudes
│   │   └── response/        # DTOs para las respuestas
│   ├── mapper/              # Mappers para convertir entre DTOs y entidades
│   └── service/             # Servicios de aplicación
├── domain/
│   ├── enums/               # Enumeraciones del dominio
│   ├── model/               # Entidades del dominio
│   └── repository/          # Interfaces de repositorio
└── infrastructure/
    ├── config/              # Configuraciones
    ├── exception/           # Manejo de excepciones
    ├── repository/          # Implementaciones de repositorios
    └── rest/                # Controladores REST
```

## Tecnologías

- Java 11
- Spring Boot 2.7.0
- Spring WebFlux
- MongoDB
- Lombok
- Maven

## Funcionalidades

### Gestión de Estudiantes
- Listar todos los estudiantes
- Buscar estudiante por ID
- Crear nuevo estudiante
- Actualizar estudiante
- Eliminar estudiante (baja lógica)
- Restaurar estudiante
- Filtrar por institución
- Filtrar por estado
- Filtrar por género

### Gestión de Matrículas
- Listar todas las matrículas
- Buscar matrícula por ID
- Crear nueva matrícula
- Actualizar matrícula
- Eliminar matrícula (baja lógica)
- Restaurar matrícula
- Filtrar por estudiante
- Filtrar por aula
- Filtrar por año
- Filtrar por periodo

## Configuración

### Requisitos
- Java 11 o superior
- MongoDB
- Maven

### Variables de Entorno
```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>/<database>
server.port=8081
```

### Ejecución
```bash
mvn spring-boot:run
```

## API Endpoints

### Estudiantes
```
GET    /api/v1/students
GET    /api/v1/students/{id}
POST   /api/v1/students
PUT    /api/v1/students/{id}
DELETE /api/v1/students/{id}
PUT    /api/v1/students/{id}/restore
GET    /api/v1/students/institution/{institutionId}
GET    /api/v1/students/status/{status}
GET    /api/v1/students/gender/{gender}
```

### Matrículas
```
GET    /api/v1/classroom-students
GET    /api/v1/classroom-students/{id}
POST   /api/v1/classroom-students
PUT    /api/v1/classroom-students/{id}
DELETE /api/v1/classroom-students/{id}
PUT    /api/v1/classroom-students/{id}/restore
GET    /api/v1/classroom-students/student/{studentId}
GET    /api/v1/classroom-students/classroom/{classroomId}
GET    /api/v1/classroom-students/status/{status}
GET    /api/v1/classroom-students/year/{year}
GET    /api/v1/classroom-students/period/{period}
```

## Manejo de Errores

El sistema incluye un manejador global de excepciones que proporciona respuestas consistentes para los siguientes casos:
- Recurso no encontrado (404)
- Error de validación (400)
- Error interno del servidor (500)

## Seguridad

- CORS habilitado para todos los orígenes en desarrollo
- Métodos HTTP permitidos: GET, POST, PUT, DELETE, OPTIONS
- Headers permitidos: todos

## Repositorios

- GitHub: https://github.com/Omarrivv/vg-ms-students
- GitLab: https://gitlab.com/vallegrande/as231s5_prs2/vg-ms-students

## Contribución

1. Fork el repositorio
2. Cree una rama para su feature (`git checkout -b feature/AmazingFeature`)
3. Commit sus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abra un Pull Request

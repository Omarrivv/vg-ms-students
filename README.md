# Microservicio msv-students

Backend desarrollado con **Spring Boot 2.7.18**, **Spring WebFlux**, **MongoDB Atlas**, **Reactor Core** y **Lombok**.

## Requisitos
- Java 11 o superior
- Maven
- Docker y Docker Swarm
- Acceso a MongoDB Atlas

## Configuración

La configuración de la base de datos está en `src/main/resources/application.yml`.

### Variables de Entorno
Crear un archivo `.env` en la raíz del proyecto con las siguientes variables:
```
MONGO_INITDB_ROOT_USERNAME=admin
MONGO_INITDB_ROOT_PASSWORD=admin123
MONGO_INITDB_DATABASE=students_db
```

## Ejecución Local

```bash
mvn spring-boot:run
```

La aplicación se ejecuta en `http://localhost:8081` por defecto.

## Despliegue con Docker Swarm

1. Construir la aplicación:
```bash
mvn clean package -DskipTests
```

2. Inicializar Docker Swarm (si no está inicializado):
```bash
docker swarm init
```

3. Desplegar el stack:
```bash
docker stack deploy -c docker-compose.yml microservice
```

4. Verificar los servicios:
```bash
docker service ls
docker stack services microservice
```

5. Verificar los logs:
```bash
docker service logs microservice_app
```

6. Para detener el stack:
```bash
docker stack rm microservice
```

7. Para salir del swarm:
```bash
docker swarm leave --force
```

## Health Check
El servicio expone un endpoint de health check en:
```
GET http://localhost:8081/actuator/health
```

---

# ENDPOINTS Y DOCUMENTACIÓN

## 1. Estudiantes (`/api/v1/students`)

### Obtener todos los estudiantes
- **GET** `/api/v1/students`
- **Body:** _No requiere_
- **Respuesta:**
```json
[
  {
    "id": "...",
    "institutionId": "1",
    "firstName": "Juan",
    "lastName": "Gómez",
    "documentType": "DNI",
    "documentNumber": "78901234",
    "gender": "M",
    "birthDate": "2015-03-15",
    "address": "Jr. Los Pinos 123",
    "phone": "912345678",
    "email": "juangomez@mail.com",
    "nameQr": "Juan_Gomez_78901234",
    "status": "A"
  }
]
```

### Obtener estudiante por ID
- **GET** `/api/v1/students/{id}`
- **Body:** _No requiere_
- **Respuesta:**
```json
{
  "id": "...",
  "institutionId": "1",
  "firstName": "Juan",
  "lastName": "Gómez",
  ...
}
```

### Crear estudiante
- **POST** `/api/v1/students`
- **Body:**
```json
{
  "institutionId": "1",
  "firstName": "Nuevo",
  "lastName": "Estudiante",
  "documentType": "DNI",
  "documentNumber": "12345678",
  "gender": "M",
  "birthDate": "2010-01-01",
  "address": "Calle Falsa 123",
  "phone": "999999999",
  "email": "nuevo@mail.com",
  "nameQr": "Nuevo_Estudiante_12345678",
  "status": "A"
}
```
- **Respuesta:** Estudiante creado (con `id` generado).

### Actualizar estudiante
- **PUT** `/api/v1/students/{id}`
- **Body:**
```json
{
  "institutionId": "1",
  "firstName": "NombreActualizado",
  "lastName": "ApellidoActualizado",
  "documentType": "DNI",
  "documentNumber": "12345678",
  "gender": "F",
  "birthDate": "2010-01-01",
  "address": "Nueva dirección",
  "phone": "888888888",
  "email": "actualizado@mail.com",
  "nameQr": "NombreActualizado_ApellidoActualizado_12345678",
  "status": "A"
}
```
- **Respuesta:** Estudiante actualizado.

### Eliminar estudiante
- **DELETE** `/api/v1/students/{id}`
- **Body:** _No requiere_
- **Respuesta:** `200 OK` si se eliminó correctamente.

### Restaurar estudiante
- **PUT** `/api/v1/students/{id}/restore`
- **Body:** _No requiere_
- **Respuesta:** Estudiante restaurado (status = "A").

### Listar estudiantes por institución
- **GET** `/api/v1/students/institution/{institutionId}`
- **Body:** _No requiere_
- **Respuesta:** Lista de estudiantes de esa institución.

### Listar estudiantes por estado
- **GET** `/api/v1/students/status/{status}`
- **Body:** _No requiere_
- **Respuesta:** Lista de estudiantes con ese estado (`A`=activo, `I`=inactivo).

### Listar estudiantes por género
- **GET** `/api/v1/students/gender/{gender}`
- **Body:** _No requiere_
- **Respuesta:** Lista de estudiantes con ese género (`M` o `F`).

---

## 2. Matrículas (`/api/v1/classroom-students`)

### Obtener todas las matrículas
- **GET** `/api/v1/classroom-students`
- **Body:** _No requiere_
- **Respuesta:**
```json
[
  {
    "id": "...",
    "classroomId": "1",
    "studentId": "...",
    "enrollmentDate": "2023-03-01",
    "status": "A",
    "enrollmentYear": "2023",
    "enrollmentPeriod": "2023-1"
  }
]
```

### Obtener matrícula por ID
- **GET** `/api/v1/classroom-students/{id}`
- **Body:** _No requiere_
- **Respuesta:** Objeto matrícula.

### Crear matrícula
- **POST** `/api/v1/classroom-students`
- **Body:**
```json
{
  "classroomId": "3",
  "studentId": "...",
  "enrollmentDate": "2024-01-01",
  "status": "A",
  "enrollmentYear": "2024",
  "enrollmentPeriod": "2024-1"
}
```
- **Respuesta:** Matrícula creada.

### Actualizar matrícula
- **PUT** `/api/v1/classroom-students/{id}`
- **Body:**
```json
{
  "classroomId": "3",
  "studentId": "...",
  "enrollmentDate": "2024-01-01",
  "status": "I",
  "enrollmentYear": "2024",
  "enrollmentPeriod": "2024-1"
}
```
- **Respuesta:** Matrícula actualizada.

### Eliminar matrícula
- **DELETE** `/api/v1/classroom-students/{id}`
- **Body:** _No requiere_
- **Respuesta:** `200 OK` si se eliminó correctamente.

### Restaurar matrícula
- **PUT** `/api/v1/classroom-students/{id}/restore`
- **Body:** _No requiere_
- **Respuesta:** Matrícula restaurada (status = "A").

### Listar matrículas por estudiante
- **GET** `/api/v1/classroom-students/student/{studentId}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas de ese estudiante.

### Listar matrículas por aula
- **GET** `/api/v1/classroom-students/classroom/{classroomId}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas de ese aula.

### Listar matrículas por estado
- **GET** `/api/v1/classroom-students/status/{status}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas con ese estado.

### Listar matrículas por año de matrícula
- **GET** `/api/v1/classroom-students/year/{enrollmentYear}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas de ese año.

### Listar matrículas por periodo de matrícula
- **GET** `/api/v1/classroom-students/period/{enrollmentPeriod}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas de ese periodo.

### Listar matrículas por estudiante y estado
- **GET** `/api/v1/classroom-students/student/{studentId}/status/{status}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas de ese estudiante con ese estado.

### Listar matrículas por aula y estado
- **GET** `/api/v1/classroom-students/classroom/{classroomId}/status/{status}`
- **Body:** _No requiere_
- **Respuesta:** Lista de matrículas de ese aula con ese estado.

---

## Notas
- El formato de fechas es `yyyy-MM-dd`.
- Los IDs se obtienen de los endpoints GET.
- Los campos `enrollmentYear` y `enrollmentPeriod` permiten filtrar por año y periodo académico.
- El backend maneja errores devolviendo listas vacías si no hay resultados o si ocurre un error en la consulta.
- Puedes probar los endpoints con Postman, Insomnia o curl.

---

## Autor
- Vallegrande - Omar 

---

## Buenas Prácticas y Consideraciones

- **Borrado lógico y Restauración:** El borrado de estudiantes y matrículas no elimina físicamente los documentos. En su lugar, se actualiza el campo `status` a "I" (inactivo). Es posible restaurar estos registros utilizando el endpoint de restauración, que cambia el `status` nuevamente a "A" (activo). Esto permite mantener el historial y trazabilidad de los datos.
- **Filtros:** Todos los endpoints de listado permiten filtrar por estado (`A`=activo, `I`=inactivo), género, institución, año y periodo de matrícula.
- **Estructura de entidades:**
  - **Student:** Incluye campos como institutionId, nombres, apellidos, documento, género, fecha de nacimiento, dirección, teléfono, email, nombre para QR y status.
  - **ClassroomStudent:** Relaciona estudiantes con aulas, incluye classroomId, studentId, fecha de matrícula, status, año y periodo de matrícula.
- **Auditoría:** Se recomienda agregar campos de auditoría como createdAt, updatedAt, deletedAt para mayor trazabilidad. 
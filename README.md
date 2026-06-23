# Sistema de Reserva de Coworking

Pequeña API REST en Java + Spring Boot para gestionar espacios y reservas en un coworking.

Características principales
- Registro / login de usuarios (JWT)
- Gestión de espacios (creación/actualización/eliminación) — rol ADMIN
- Consulta de espacios disponibles y horarios
- Creación/consulta/actualización/eliminación de reservas — usuarios autenticados
- Endpoints públicos para obtener franjas horarias (slots)

Stack
- Java 17
- Spring Boot
- Spring Security (JWT)
- Maven
- JPA/H2 (persistencia usando repositorios y entidades)

Cómo ejecutar (Windows)

Usando Maven (local):

```powershell
./mvnw.cmd spring-boot:run
```

Con Docker (si existe `docker-compose.yml`):

```powershell
docker-compose up --build
```

Endpoints principales

Autenticación
- POST /auth/register — Registrar usuario (público)
  - Request: RegisterRequest { "username", "email", "password" }
  - Response: RegisterResponse { id, username, email, rol }

- POST /auth — Login (público)
  - Request: LoginRequest { "email", "password" }
  - Response: LoginResponse { "token" }
  - Nota: el token es un JWT que debe enviarse en el header Authorization: Bearer <token>

Slots (publico)
- GET /slots — Lista las franjas horarias disponibles (public)
  - Response: List<SlotResponse> (name, startTime, endTime)

Espacios (spaces)
- POST /spaces — Crear espacio (ROLE_ADMIN)
  - Request: SpaceRequest { name, spaceType, maxCapacity, description }
  - Response: SpaceResponse { id, name, spaceType, maxCapacity, description }

- PUT /spaces/{id} — Actualizar espacio (ROLE_ADMIN)
  - Request: SpaceUpdateDto { name?, spaceType?, description?, maxCapacity? }
  - Response: SpaceResponse

- DELETE /spaces/{id} — Eliminar espacio (ROLE_ADMIN)
  - Response: 204 No Content

- POST /spaces/available — Consultar espacios libres para una fecha y slot (ROLE_USER o ROLE_ADMIN)
  - Request: AvailableSpaceRequest { date (yyyy-MM-dd), slot }
  - Response: List<AvailableSpaceResponse> { id, name, description }

Reservas (bookings)
- POST /bookings — Crear reserva (autenticado)
  - Request: BookingRequest { spaceId, date (yyyy-MM-dd), slot, attendees }
  - Response: BookingResponse { id, nameUser, nameSpace, date, slot, attendees } (201)

- GET /bookings?userId={id} — Listar reservas. Si eres ADMIN puedes pasar userId para consultar las de un usuario (autenticado)
  - Response: List<BookingResponse>

- PUT /bookings/{bookingId} — Actualizar reserva (autenticado)
  - Request: BookingRequest
  - Response: BookingResponse

- DELETE /bookings/{bookingId} — Eliminar reserva (autenticado)
  - Response: 204 No Content

DTOs y ejemplos JSON

RegisterRequest
```json
{
  "username": "juan",
  "email": "juan@ejemplo.com",
  "password": "pass1234"
}
```

LoginRequest
```json
{
  "email": "juan@ejemplo.com",
  "password": "pass1234"
}
```

LoginResponse
```json
{
  "token": "<jwt-token>"
}
```

SpaceRequest
```json
{
  "name": "Sala A",
  "spaceType": "DESK",
  "maxCapacity": 4,
  "description": "Sala con 4 puestos"
}
```

AvailableSpaceRequest
```json
{
  "date": "2026-07-01",
  "slot": "MORNING_1"
}
```

BookingRequest
```json
{
  "spaceId": 1,
  "date": "2026-07-01",
  "slot": "MORNING_1",
  "attendees": 3
}
```

BookingResponse (ejemplo)
```json
{
  "id": 10,
  "nameUser": "juan",
  "nameSpace": "Sala A",
  "date": "2026-07-01",
  "slot": "MORNING_1",
  "attendees": 3
}
```

Valores relevantes
- ReservationSlot: MORNING_1, MORNING_2, AFTERNOON_1, AFTERNOON_2 (con horarios en la aplicación)
- SpaceType: HALL, DESK
- Roles: ADMIN, USER

Autenticación
- Enviar header: Authorization: Bearer <token>
- Rutas públicas: /auth/**, /slots/**, /swagger-ui/**, /v3/api-docs/**

CORS
- CORS está habilitado para todos los orígenes (*).
- Se permiten todos los métodos HTTP (GET, POST, PUT, DELETE, OPTIONS, PATCH).
- Se permiten todos los headers.
- Cache de preflight por 1 hora (3600 segundos).

Documentación Swagger
- La aplicación expone configuración OpenAPI; Swagger UI suele estar disponible en: /swagger-ui/ ó /swagger-ui/index.html

Notas finales
- Las validaciones (fechas, capacidad máxima, ocupación) se realizan en los validators del servicio.
- Para pruebas rápidas: registrar un usuario, hacer login, copiar el JWT y usarlo en Authorization para las rutas protegidas.

Si quieres, puedo añadir ejemplos de curl o Postman Collection y ampliar la sección de despliegue (Docker, variables de entorno).

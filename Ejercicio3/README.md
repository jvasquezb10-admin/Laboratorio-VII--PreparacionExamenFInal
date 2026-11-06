# API de empleados

API REST desarrollada con Java 21 y Spring Boot. Los empleados se almacenan en memoria y se pierden al reiniciar la aplicación.

## Ejecutar

Desde la carpeta `Ejercicio3`, configura la clave e inicia Spring Boot:

```powershell
$env:API_KEY = "cambia-esta-clave"
mvn spring-boot:run
```

Todas las solicitudes requieren el encabezado `X-API-Key`. La aplicación no inicia si `API_KEY` no está configurada.

## Endpoints

- `POST /api/empleados`: crea un empleado y responde `201 Created`.
- `GET /api/empleados`: consulta todos los empleados.
- `GET /api/empleados?especialidad=Desarrollo`: filtra por especialidad, sin distinguir mayúsculas.
- `GET /api/empleados/{id}`: consulta por ID o responde `404 Not Found`.

Ejemplo de cuerpo:

```json
{
  "nombre": "Luis",
  "apellido": "Pérez",
  "correo": "luis@example.com",
  "especialidad": "Desarrollo",
  "estado": "ACTIVO"
}
```

El nombre, apellido, correo, especialidad y estado son obligatorios; el correo debe tener formato válido. Las solicitudes inválidas responden `400 Bad Request`, y una API Key ausente o incorrecta responde `401 Unauthorized`.

## Pruebas

```powershell
mvn test
```
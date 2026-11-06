# API de profesores

API REST desarrollada con Java 21 y Spring Boot. Los datos se conservan en memoria y se pierden al reiniciar la aplicación.

## Ejecutar

Define una API Key antes de iniciar la aplicación:

```powershell
$env:API_KEY = "cambia-esta-clave"
mvn spring-boot:run
```

Todas las solicitudes deben enviar la clave en el encabezado `X-API-Key`. Sin una clave configurada la aplicación no inicia.

## Endpoints

- `POST /api/profesores`: crea un profesor y responde `201 Created`.
- `GET /api/profesores`: lista todos los profesores.
- `GET /api/profesores?especialidad=Matemáticas`: filtra por especialidad, sin distinguir mayúsculas.
- `GET /api/profesores/{id}`: obtiene un profesor o responde `404 Not Found`.

Ejemplo de cuerpo para crear un profesor:

```json
{
  "nombre": "Ana",
  "apellido": "García",
  "correo": "ana@example.com",
  "especialidad": "Matemáticas",
  "estado": "ACTIVO"
}
```

El nombre, apellido, correo, especialidad y estado son obligatorios; el correo debe tener formato válido. Las solicitudes inválidas responden `400 Bad Request` y las solicitudes sin API Key válida responden `401 Unauthorized`.

## Pruebas

```powershell
mvn test
```
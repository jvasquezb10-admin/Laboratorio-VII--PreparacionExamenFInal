# API de proveedores

API REST con Java 21 y Spring Boot. Los proveedores se guardan en memoria y se pierden al reiniciar la aplicación.

## Ejecutar

Desde la carpeta `Ejercicio2`, configura la clave e inicia Spring Boot:

```powershell
$env:API_KEY = "cambia-esta-clave"
mvn spring-boot:run
```

Todas las solicitudes requieren el encabezado `X-API-Key`. La aplicación no inicia si `API_KEY` no está configurada.

## Endpoints

- `POST /api/proveedores`: crea un proveedor y responde `201 Created`.
- `GET /api/proveedores`: consulta todos los proveedores.
- `GET /api/proveedores?categoria=Tecnología`: filtra por categoría, sin distinguir mayúsculas.
- `GET /api/proveedores/{id}`: consulta por ID o responde `404 Not Found`.

Ejemplo de cuerpo:

```json
{
  "nombre": "Ana",
  "apellido": "García",
  "correo": "ana@example.com",
  "categoria": "Tecnología",
  "estado": "ACTIVO"
}
```

El nombre, apellido, correo, categoría y estado son obligatorios; el correo debe tener formato válido. Las solicitudes inválidas responden `400 Bad Request`, y una API Key ausente o incorrecta responde `401 Unauthorized`.

## Pruebas

```powershell
mvn test
```
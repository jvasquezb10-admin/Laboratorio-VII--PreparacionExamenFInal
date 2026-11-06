package com.example.proveedores.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiExceptionHandlerTest {

    @Test
    void proveedorNoEncontrado_responde404() {
        ApiExceptionHandler handler = new ApiExceptionHandler();

        var response = handler.manejarProveedorNoEncontrado(new ProveedorNoEncontradoException(42L));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No existe un proveedor con id 42", response.getBody().mensaje());
    }
}
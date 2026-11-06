package com.example.empleados.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiExceptionHandlerTest {

    @Test
    void empleadoNoEncontrado_responde404() {
        ApiExceptionHandler handler = new ApiExceptionHandler();

        var response = handler.manejarEmpleadoNoEncontrado(new EmpleadoNoEncontradoException(42L));

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No existe un empleado con id 42", response.getBody().mensaje());
    }
}
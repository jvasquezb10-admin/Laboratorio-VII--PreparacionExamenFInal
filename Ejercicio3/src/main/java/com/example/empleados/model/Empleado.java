package com.example.empleados.model;

public record Empleado(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        EstadoEmpleado estado
) {
}
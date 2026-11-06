package com.example.profesores.model;

public record Profesor(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String especialidad,
        EstadoProfesor estado
) {
}
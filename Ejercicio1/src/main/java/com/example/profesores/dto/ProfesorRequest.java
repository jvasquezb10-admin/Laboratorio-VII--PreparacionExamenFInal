package com.example.profesores.dto;

import com.example.profesores.model.EstadoProfesor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfesorRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String correo,
        @NotBlank(message = "La especialidad es obligatoria")
        String especialidad,
        @NotNull(message = "El estado es obligatorio")
        EstadoProfesor estado
) {
}
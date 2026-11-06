package com.example.proveedores.dto;

import com.example.proveedores.model.EstadoProveedor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProveedorRequest(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        String correo,
        @NotBlank(message = "La categoría es obligatoria")
        String categoria,
        @NotNull(message = "El estado es obligatorio")
        EstadoProveedor estado
) {
}
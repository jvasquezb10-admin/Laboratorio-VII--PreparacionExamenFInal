package com.example.proveedores.model;

public record Proveedor(
        Long id,
        String nombre,
        String apellido,
        String correo,
        String categoria,
        EstadoProveedor estado
) {
}
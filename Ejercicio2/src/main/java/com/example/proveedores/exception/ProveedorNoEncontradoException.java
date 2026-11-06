package com.example.proveedores.exception;

public class ProveedorNoEncontradoException extends RuntimeException {

    public ProveedorNoEncontradoException(Long id) {
        super("No existe un proveedor con id " + id);
    }
}
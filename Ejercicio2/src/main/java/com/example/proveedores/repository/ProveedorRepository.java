package com.example.proveedores.repository;

import com.example.proveedores.model.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {

    Proveedor save(Proveedor proveedor);

    List<Proveedor> findAll();

    List<Proveedor> findByCategoria(String categoria);

    Optional<Proveedor> findById(Long id);
}
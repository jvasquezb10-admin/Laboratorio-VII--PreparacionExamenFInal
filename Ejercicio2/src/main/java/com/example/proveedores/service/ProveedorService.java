package com.example.proveedores.service;

import com.example.proveedores.dto.ProveedorRequest;
import com.example.proveedores.exception.ProveedorNoEncontradoException;
import com.example.proveedores.model.Proveedor;
import com.example.proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Proveedor crear(ProveedorRequest request) {
        Proveedor proveedor = new Proveedor(
                null,
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.categoria(),
                request.estado()
        );
        return proveedorRepository.save(proveedor);
    }

    public List<Proveedor> listar(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            return proveedorRepository.findAll();
        }
        return proveedorRepository.findByCategoria(categoria.trim());
    }

    public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new ProveedorNoEncontradoException(id));
    }
}
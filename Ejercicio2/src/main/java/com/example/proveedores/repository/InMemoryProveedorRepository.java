package com.example.proveedores.repository;

import com.example.proveedores.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProveedorRepository implements ProveedorRepository {

    private final List<Proveedor> proveedores = new CopyOnWriteArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    @Override
    public Proveedor save(Proveedor proveedor) {
        Proveedor nuevoProveedor = new Proveedor(
                siguienteId.getAndIncrement(),
                proveedor.nombre(),
                proveedor.apellido(),
                proveedor.correo(),
                proveedor.categoria(),
                proveedor.estado()
        );
        proveedores.add(nuevoProveedor);
        return nuevoProveedor;
    }

    @Override
    public List<Proveedor> findAll() {
        return List.copyOf(proveedores);
    }

    @Override
    public List<Proveedor> findByCategoria(String categoria) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.categoria().equalsIgnoreCase(categoria))
                .toList();
    }

    @Override
    public Optional<Proveedor> findById(Long id) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.id().equals(id))
                .findFirst();
    }
}
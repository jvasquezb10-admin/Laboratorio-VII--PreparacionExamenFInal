package com.example.empleados.repository;

import com.example.empleados.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryEmpleadoRepository implements EmpleadoRepository {

    private final List<Empleado> empleados = new CopyOnWriteArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    @Override
    public Empleado save(Empleado empleado) {
        Empleado nuevoEmpleado = new Empleado(
                siguienteId.getAndIncrement(),
                empleado.nombre(),
                empleado.apellido(),
                empleado.correo(),
                empleado.especialidad(),
                empleado.estado()
        );
        empleados.add(nuevoEmpleado);
        return nuevoEmpleado;
    }

    @Override
    public List<Empleado> findAll() {
        return List.copyOf(empleados);
    }

    @Override
    public List<Empleado> findByEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(empleado -> empleado.especialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return empleados.stream()
                .filter(empleado -> empleado.id().equals(id))
                .findFirst();
    }
}
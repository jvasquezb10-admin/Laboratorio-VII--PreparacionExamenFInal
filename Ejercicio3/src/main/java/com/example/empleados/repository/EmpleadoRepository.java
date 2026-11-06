package com.example.empleados.repository;

import com.example.empleados.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {

    Empleado save(Empleado empleado);

    List<Empleado> findAll();

    List<Empleado> findByEspecialidad(String especialidad);

    Optional<Empleado> findById(Long id);
}
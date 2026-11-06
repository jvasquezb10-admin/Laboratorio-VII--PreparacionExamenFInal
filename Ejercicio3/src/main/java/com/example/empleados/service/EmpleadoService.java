package com.example.empleados.service;

import com.example.empleados.dto.EmpleadoRequest;
import com.example.empleados.exception.EmpleadoNoEncontradoException;
import com.example.empleados.model.Empleado;
import com.example.empleados.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public Empleado crear(EmpleadoRequest request) {
        Empleado empleado = new Empleado(
                null,
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.especialidad(),
                request.estado()
        );
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return empleadoRepository.findAll();
        }
        return empleadoRepository.findByEspecialidad(especialidad.trim());
    }

    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }
}
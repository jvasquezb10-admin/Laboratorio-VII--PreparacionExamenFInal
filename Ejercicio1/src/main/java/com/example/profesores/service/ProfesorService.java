package com.example.profesores.service;

import com.example.profesores.dto.ProfesorRequest;
import com.example.profesores.exception.ProfesorNoEncontradoException;
import com.example.profesores.model.Profesor;
import com.example.profesores.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor crear(ProfesorRequest request) {
        Profesor profesor = new Profesor(
                null,
                request.nombre(),
                request.apellido(),
                request.correo(),
                request.especialidad(),
                request.estado()
        );
        return profesorRepository.save(profesor);
    }

    public List<Profesor> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return profesorRepository.findAll();
        }
        return profesorRepository.findByEspecialidad(especialidad.trim());
    }

    public Profesor obtenerPorId(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new ProfesorNoEncontradoException(id));
    }
}
package com.example.profesores.repository;

import com.example.profesores.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepository {

    Profesor save(Profesor profesor);

    List<Profesor> findAll();

    List<Profesor> findByEspecialidad(String especialidad);

    Optional<Profesor> findById(Long id);
}
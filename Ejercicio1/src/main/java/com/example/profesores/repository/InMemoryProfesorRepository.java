package com.example.profesores.repository;

import com.example.profesores.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryProfesorRepository implements ProfesorRepository {

    private final List<Profesor> profesores = new CopyOnWriteArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    @Override
    public Profesor save(Profesor profesor) {
        Profesor nuevoProfesor = new Profesor(
                siguienteId.getAndIncrement(),
                profesor.nombre(),
                profesor.apellido(),
                profesor.correo(),
                profesor.especialidad(),
                profesor.estado()
        );
        profesores.add(nuevoProfesor);
        return nuevoProfesor;
    }

    @Override
    public List<Profesor> findAll() {
        return List.copyOf(profesores);
    }

    @Override
    public List<Profesor> findByEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(profesor -> profesor.especialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    @Override
    public Optional<Profesor> findById(Long id) {
        return profesores.stream()
                .filter(profesor -> profesor.id().equals(id))
                .findFirst();
    }
}
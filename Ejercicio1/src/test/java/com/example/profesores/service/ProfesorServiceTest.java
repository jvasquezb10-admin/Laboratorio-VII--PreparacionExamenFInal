package com.example.profesores.service;

import com.example.profesores.dto.ProfesorRequest;
import com.example.profesores.exception.ProfesorNoEncontradoException;
import com.example.profesores.model.EstadoProfesor;
import com.example.profesores.model.Profesor;
import com.example.profesores.repository.ProfesorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @InjectMocks
    private ProfesorService profesorService;

    @Test
    void crearProfesor_guardaYRetornaElProfesorConId() {
        ProfesorRequest request = new ProfesorRequest(
                "Ana", "García", "ana@example.com", "Matemáticas", EstadoProfesor.ACTIVO
        );
        Profesor profesorGuardado = new Profesor(
                1L, "Ana", "García", "ana@example.com", "Matemáticas", EstadoProfesor.ACTIVO
        );
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesorGuardado);

        Profesor resultado = profesorService.crear(request);

        assertEquals(profesorGuardado, resultado);
        verify(profesorRepository).save(any(Profesor.class));
    }

    @Test
    void obtenerPorId_cuandoExiste_retornaElProfesor() {
        Profesor profesor = new Profesor(
                1L, "Ana", "García", "ana@example.com", "Matemáticas", EstadoProfesor.ACTIVO
        );
        when(profesorRepository.findById(1L)).thenReturn(Optional.of(profesor));

        Profesor resultado = profesorService.obtenerPorId(1L);

        assertEquals(profesor, resultado);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_lanzaExcepcion() {
        when(profesorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProfesorNoEncontradoException.class, () -> profesorService.obtenerPorId(99L));
    }
}
package com.example.empleados.service;

import com.example.empleados.dto.EmpleadoRequest;
import com.example.empleados.exception.EmpleadoNoEncontradoException;
import com.example.empleados.model.Empleado;
import com.example.empleados.model.EstadoEmpleado;
import com.example.empleados.repository.EmpleadoRepository;
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
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    @Test
    void crearEmpleado_guardaYRetornaEmpleadoConId() {
        EmpleadoRequest request = new EmpleadoRequest(
                "Luis", "Pérez", "luis@example.com", "Desarrollo", EstadoEmpleado.ACTIVO
        );
        Empleado empleadoGuardado = new Empleado(
                1L, "Luis", "Pérez", "luis@example.com", "Desarrollo", EstadoEmpleado.ACTIVO
        );
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoGuardado);

        Empleado resultado = empleadoService.crear(request);

        assertEquals(empleadoGuardado, resultado);
        verify(empleadoRepository).save(any(Empleado.class));
    }

    @Test
    void obtenerPorId_cuandoExiste_retornaEmpleado() {
        Empleado empleado = new Empleado(
                1L, "Luis", "Pérez", "luis@example.com", "Desarrollo", EstadoEmpleado.ACTIVO
        );
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Empleado resultado = empleadoService.obtenerPorId(1L);

        assertEquals(empleado, resultado);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_lanzaExcepcion() {
        when(empleadoRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(EmpleadoNoEncontradoException.class, () -> empleadoService.obtenerPorId(99L));
    }
}
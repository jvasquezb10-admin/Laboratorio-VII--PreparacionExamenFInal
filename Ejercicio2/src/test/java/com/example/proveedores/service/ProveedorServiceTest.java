package com.example.proveedores.service;

import com.example.proveedores.dto.ProveedorRequest;
import com.example.proveedores.exception.ProveedorNoEncontradoException;
import com.example.proveedores.model.EstadoProveedor;
import com.example.proveedores.model.Proveedor;
import com.example.proveedores.repository.ProveedorRepository;
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
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void crearProveedor_guardaYRetornaProveedorConId() {
        ProveedorRequest request = new ProveedorRequest(
                "Ana", "García", "ana@example.com", "Tecnología", EstadoProveedor.ACTIVO
        );
        Proveedor proveedorGuardado = new Proveedor(
                1L, "Ana", "García", "ana@example.com", "Tecnología", EstadoProveedor.ACTIVO
        );
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedorGuardado);

        Proveedor resultado = proveedorService.crear(request);

        assertEquals(proveedorGuardado, resultado);
        verify(proveedorRepository).save(any(Proveedor.class));
    }

    @Test
    void obtenerPorId_cuandoExiste_retornaProveedor() {
        Proveedor proveedor = new Proveedor(
                1L, "Ana", "García", "ana@example.com", "Tecnología", EstadoProveedor.ACTIVO
        );
        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));

        Proveedor resultado = proveedorService.obtenerPorId(1L);

        assertEquals(proveedor, resultado);
    }

    @Test
    void obtenerPorId_cuandoNoExiste_lanzaExcepcion() {
        when(proveedorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProveedorNoEncontradoException.class, () -> proveedorService.obtenerPorId(99L));
    }
}
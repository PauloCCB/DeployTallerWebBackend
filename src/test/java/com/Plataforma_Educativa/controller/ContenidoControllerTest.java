package com.Plataforma_Educativa.controller;

import com.Plataforma_Educativa.service.ContenidoService;
import com.Plataforma_Educativa.model.dto.ContenidoDTO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ContenidoControllerTest {
    @Mock
    private ContenidoService contenidoService;

    @InjectMocks
    private ContenidoController contenidoController;

    @Test
    void subirVideoShouldReturnCreatedContent() {
        // Arrange
        ContenidoDTO contenidoDTO = new ContenidoDTO();
        when(contenidoService.subirVideo(any(ContenidoDTO.class))).thenReturn(contenidoDTO);

        // Act
        ResponseEntity<ContenidoDTO> response = contenidoController.subirVideo(contenidoDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(contenidoService).subirVideo(contenidoDTO);
    }

    @Test
    void obtenerContenidoPorIdShouldReturnContent() {
        // Arrange
        Long id = 1L;
        ContenidoDTO contenidoDTO = new ContenidoDTO();
        when(contenidoService.obtenerContenidoPorId(id)).thenReturn(contenidoDTO);

        // Act
        ResponseEntity<ContenidoDTO> response = contenidoController.obtenerContenidoPorId(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(contenidoService).obtenerContenidoPorId(id);
    }

    @Test
    void obtenerTodosLosContenidosShouldReturnList() {
        // Arrange
        List<ContenidoDTO> contenidos = Arrays.asList(new ContenidoDTO(), new ContenidoDTO());
        when(contenidoService.obtenerTodosLosContenidos()).thenReturn(contenidos);

        // Act
        ResponseEntity<List<ContenidoDTO>> response = contenidoController.obtenerTodosLosContenidos();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(contenidoService).obtenerTodosLosContenidos();
    }

    @Test
    void editarContenidoShouldReturnUpdatedContent() {
        // Arrange
        Long id = 1L;
        ContenidoDTO contenidoDTO = new ContenidoDTO();
        when(contenidoService.editarContenido(eq(id), any(ContenidoDTO.class))).thenReturn(contenidoDTO);

        // Act
        ResponseEntity<ContenidoDTO> response = contenidoController.editarContenido(id, contenidoDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(contenidoService).editarContenido(id, contenidoDTO);
    }

    @Test
    void eliminarContenidoShouldReturnSuccessMessage() {
        // Arrange
        Long id = 1L;
        doNothing().when(contenidoService).eliminarContenido(id);

        // Act
        ResponseEntity<String> response = contenidoController.eliminarContenido(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Contenido eliminado correctamente", response.getBody());
        verify(contenidoService).eliminarContenido(id);
    }
}
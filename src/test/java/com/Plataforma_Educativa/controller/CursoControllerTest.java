package com.Plataforma_Educativa.controller;

import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import com.Plataforma_Educativa.service.CursoService;
import com.Plataforma_Educativa.model.dto.CursoDTO;

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
class CursoControllerTest {
    @Mock
    private CursoService cursoService;

    @InjectMocks
    private CursoController cursoController;

    @Test
    void crearCursoShouldReturnCreatedCourse() {
        // Arrange
        CursoDTO cursoDTO = new CursoDTO();
        Curso curso = new Curso();
        when(cursoService.crearCurso(any(CursoDTO.class))).thenReturn(curso);

        // Act
        ResponseEntity<Curso> response = cursoController.crearCurso(cursoDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(cursoService).crearCurso(cursoDTO);
    }

    @Test
    void editarCursoShouldReturnUpdatedCourse() {
        // Arrange
        Long id = 1L;
        Curso curso = new Curso();
        when(cursoService.editarCurso(eq(id), any(Curso.class))).thenReturn(curso);

        // Act
        ResponseEntity<Curso> response = cursoController.editarCurso(id, curso);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(cursoService).editarCurso(id, curso);
    }

    @Test
    void eliminarCursoShouldReturnSuccessMessage() {
        // Arrange
        Long id = 1L;
        doNothing().when(cursoService).eliminarCurso(id);

        // Act
        ResponseEntity<String> response = cursoController.eliminarCurso(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Curso eliminado correctamente.", response.getBody());
        verify(cursoService).eliminarCurso(id);
    }

    @Test
    void obtenerCursoPorIDShouldReturnCourse() {
        // Arrange
        Long id = 1L;
        Curso curso = new Curso();
        when(cursoService.obtenerCursoPorID(id)).thenReturn(curso);

        // Act
        ResponseEntity<Curso> response = cursoController.obtenerCursoPorID(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(cursoService).obtenerCursoPorID(id);
    }

    @Test
    void agregarContenidoShouldReturnAddedContent() {
        // Arrange
        Long cursoId = 1L;
        Contenido contenido = new Contenido();
        when(cursoService.agregarContenido(eq(cursoId), any(Contenido.class))).thenReturn(contenido);

        // Act
        ResponseEntity<Contenido> response = cursoController.agregarContenido(cursoId, contenido);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(cursoService).agregarContenido(cursoId, contenido);
    }
}
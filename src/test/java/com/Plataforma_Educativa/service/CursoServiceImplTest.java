package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.CursoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import com.Plataforma_Educativa.model.entity.Docente;
import com.Plataforma_Educativa.repository.ContenidoRepository;
import com.Plataforma_Educativa.repository.CursoRepository;
import com.Plataforma_Educativa.repository.DocenteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CursoServiceImplTest {

    @Mock
    private CursoRepository cursoRepository;
    @Mock
    private DocenteRepository docenteRepository;
    @Mock
    private ContenidoRepository contenidoRepository;

    private CursoServiceImpl cursoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cursoService = new CursoServiceImpl(cursoRepository, docenteRepository, contenidoRepository);
    }

    @Test
    void crearCurso_DeberiaCrearCursoExitosamente() {
        // Arrange
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setNombre("Programación Java");
        cursoDTO.setDescripcion("Curso básico de Java");
        cursoDTO.setDocentesIds(Arrays.asList(1L, 2L));

        Docente docente1 = new Docente();
        Docente docente2 = new Docente();
        when(docenteRepository.findAllById(cursoDTO.getDocentesIds()))
                .thenReturn(Arrays.asList(docente1, docente2));

        Curso cursoEsperado = new Curso();
        cursoEsperado.setNombre(cursoDTO.getNombre());
        cursoEsperado.setDescripcion(cursoDTO.getDescripcion());
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoEsperado);

        // Act
        Curso resultado = cursoService.crearCurso(cursoDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(cursoDTO.getNombre(), resultado.getNombre());
        assertEquals(cursoDTO.getDescripcion(), resultado.getDescripcion());
        verify(cursoRepository).save(any(Curso.class));
    }

    @Test
    void editarCurso_DeberiaActualizarCursoExistente() {
        // Arrange
        Long cursoId = 1L;
        Curso cursoExistente = new Curso();
        cursoExistente.setNombre("Curso Original");

        Curso cursoActualizado = new Curso();
        cursoActualizado.setNombre("Curso Actualizado");
        cursoActualizado.setDescripcion("Nueva descripción");

        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(cursoExistente));
        when(cursoRepository.save(any(Curso.class))).thenReturn(cursoActualizado);

        // Act
        Curso resultado = cursoService.editarCurso(cursoId, cursoActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals(cursoActualizado.getNombre(), resultado.getNombre());
        assertEquals(cursoActualizado.getDescripcion(), resultado.getDescripcion());
    }

    @Test
    void editarCurso_DeberiaLanzarExcepcionCuandoCursoNoExiste() {
        // Arrange
        Long cursoId = 1L;
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                cursoService.editarCurso(cursoId, new Curso())
        );
    }

    @Test
    void eliminarCurso_DeberiaEliminarCursoExitosamente() {
        // Arrange
        Long cursoId = 1L;

        // Act
        cursoService.eliminarCurso(cursoId);

        // Assert
        verify(cursoRepository).deleteById(cursoId);
    }

    @Test
    void obtenerCursoPorID_DeberiaRetornarCursoExistente() {
        // Arrange
        Long cursoId = 1L;
        Curso cursoEsperado = new Curso();
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(cursoEsperado));

        // Act
        Curso resultado = cursoService.obtenerCursoPorID(cursoId);

        // Assert
        assertNotNull(resultado);
        assertEquals(cursoEsperado, resultado);
    }

    @Test
    void agregarContenido_DeberiaAgregarContenidoACursoExistente() {
        // Arrange
        Long cursoId = 1L;
        Curso curso = new Curso();
        Contenido contenido = new Contenido();

        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(curso));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(contenido);

        // Act
        Contenido resultado = cursoService.agregarContenido(cursoId, contenido);

        // Assert
        assertNotNull(resultado);
        assertEquals(curso, resultado.getCurso());
        verify(contenidoRepository).save(contenido);
    }

    @Test
    void agregarContenido_DeberiaLanzarExcepcionCuandoCursoNoExiste() {
        // Arrange
        Long cursoId = 1L;
        when(cursoRepository.findById(cursoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                cursoService.agregarContenido(cursoId, new Contenido())
        );
    }
}
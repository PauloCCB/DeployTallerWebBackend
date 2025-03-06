package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.ContenidoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import com.Plataforma_Educativa.repository.ContenidoRepository;
import com.Plataforma_Educativa.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContenidoServiceImplTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private CursoRepository cursoRepository;

    private ContenidoServiceImpl contenidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contenidoService = new ContenidoServiceImpl(contenidoRepository, cursoRepository);
    }

    @Test
    void subirVideo_DeberiaCrearContenidoExitosamente() {
        // Arrange
        Long cursoId = 1L;
        Curso curso = new Curso();
        curso.setId(cursoId);

        ContenidoDTO contenidoDTO = new ContenidoDTO();
        contenidoDTO.setCursoId(cursoId);
        contenidoDTO.setTitulo("Video Tutorial");
        contenidoDTO.setUrl("https://ejemplo.com/video");

        Contenido contenidoGuardado = new Contenido();
        contenidoGuardado.setId(1L);
        contenidoGuardado.setTitulo(contenidoDTO.getTitulo());
        contenidoGuardado.setUrl(contenidoDTO.getUrl());
        contenidoGuardado.setCurso(curso);

        when(cursoRepository.findById(cursoId)).thenReturn(Optional.of(curso));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(contenidoGuardado);

        // Act
        ContenidoDTO resultado = contenidoService.subirVideo(contenidoDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(contenidoDTO.getTitulo(), resultado.getTitulo());
        assertEquals(contenidoDTO.getUrl(), resultado.getUrl());
        assertEquals(cursoId, resultado.getCursoId());
    }

    @Test
    void obtenerContenidoPorId_DeberiaRetornarContenido() {
        // Arrange
        Long contenidoId = 1L;
        Curso curso = new Curso();
        curso.setId(1L);

        Contenido contenido = new Contenido();
        contenido.setId(contenidoId);
        contenido.setTitulo("Video Tutorial");
        contenido.setUrl("https://ejemplo.com/video");
        contenido.setCurso(curso);

        when(contenidoRepository.findById(contenidoId)).thenReturn(Optional.of(contenido));

        // Act
        ContenidoDTO resultado = contenidoService.obtenerContenidoPorId(contenidoId);

        // Assert
        assertNotNull(resultado);
        assertEquals(contenido.getId(), resultado.getId());
        assertEquals(contenido.getTitulo(), resultado.getTitulo());
        assertEquals(contenido.getUrl(), resultado.getUrl());
    }

    @Test
    void obtenerTodosLosContenidos_DeberiaRetornarListaDeContenidos() {
        // Arrange
        Curso curso = new Curso();
        curso.setId(1L);

        Contenido contenido1 = new Contenido(1L, "Video 1", "url1", curso);
        Contenido contenido2 = new Contenido(2L, "Video 2", "url2", curso);
        List<Contenido> contenidos = Arrays.asList(contenido1, contenido2);

        when(contenidoRepository.findAll()).thenReturn(contenidos);

        // Act
        List<ContenidoDTO> resultado = contenidoService.obtenerTodosLosContenidos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(contenido1.getTitulo(), resultado.get(0).getTitulo());
        assertEquals(contenido2.getTitulo(), resultado.get(1).getTitulo());
    }

    @Test
    void editarContenido_DeberiaActualizarContenido() {
        // Arrange
        Long contenidoId = 1L;
        Curso curso = new Curso();
        curso.setId(1L);

        Contenido contenidoExistente = new Contenido(contenidoId, "Video Original", "url-original", curso);
        ContenidoDTO contenidoDTO = new ContenidoDTO(contenidoId, "Video Actualizado", "url-nueva", curso.getId());

        when(contenidoRepository.findById(contenidoId)).thenReturn(Optional.of(contenidoExistente));
        when(contenidoRepository.save(any(Contenido.class))).thenReturn(
                new Contenido(contenidoId, contenidoDTO.getTitulo(), contenidoDTO.getUrl(), curso)
        );

        // Act
        ContenidoDTO resultado = contenidoService.editarContenido(contenidoId, contenidoDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(contenidoDTO.getTitulo(), resultado.getTitulo());
        assertEquals(contenidoDTO.getUrl(), resultado.getUrl());
    }

    @Test
    void eliminarContenido_DeberiaEliminarContenido() {
        // Arrange
        Long contenidoId = 1L;

        // Act
        contenidoService.eliminarContenido(contenidoId);

        // Assert
        verify(contenidoRepository).deleteById(contenidoId);
    }

    @Test
    void subirVideo_DeberiaLanzarExcepcionCuandoCursoNoExiste() {
        // Arrange
        ContenidoDTO contenidoDTO = new ContenidoDTO();
        contenidoDTO.setCursoId(1L);
        when(cursoRepository.findById(any())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> contenidoService.subirVideo(contenidoDTO));
    }

    @Test
    void obtenerContenidoPorId_DeberiaLanzarExcepcionCuandoNoExiste() {
        // Arrange
        Long contenidoId = 1L;
        when(contenidoRepository.findById(contenidoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> contenidoService.obtenerContenidoPorId(contenidoId));
    }
}
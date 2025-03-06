package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.ContenidoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import com.Plataforma_Educativa.repository.ContenidoRepository;
import com.Plataforma_Educativa.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContenidoServiceImpl implements ContenidoService{
    private final ContenidoRepository contenidoRepository;
    private final CursoRepository cursoRepository;

    public ContenidoServiceImpl(ContenidoRepository contenidoRepository, CursoRepository cursoRepository) {
        this.contenidoRepository = contenidoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public ContenidoDTO subirVideo(ContenidoDTO contenidoDTO) {
        Curso curso = cursoRepository.findById(contenidoDTO.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Contenido contenido = new Contenido(null, contenidoDTO.getTitulo(), contenidoDTO.getUrl(), curso);
        contenido = contenidoRepository.save(contenido);
        return new ContenidoDTO(contenido.getId(), contenido.getTitulo(), contenido.getUrl(), curso.getId());
    }

    @Override
    public ContenidoDTO obtenerContenidoPorId(Long id) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
        return new ContenidoDTO(contenido.getId(), contenido.getTitulo(), contenido.getUrl(), contenido.getCurso().getId());
    }

    @Override
    public List<ContenidoDTO> obtenerTodosLosContenidos() {
        return contenidoRepository.findAll().stream()
                .map(contenido -> new ContenidoDTO(contenido.getId(), contenido.getTitulo(), contenido.getUrl(), contenido.getCurso().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public ContenidoDTO editarContenido(Long id, ContenidoDTO contenidoDTO) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
        contenido.setTitulo(contenidoDTO.getTitulo());
        contenido.setUrl(contenidoDTO.getUrl());
        contenido = contenidoRepository.save(contenido);
        return new ContenidoDTO(contenido.getId(), contenido.getTitulo(), contenido.getUrl(), contenido.getCurso().getId());
    }

    @Override
    public void eliminarContenido(Long id) {
        contenidoRepository.deleteById(id);
    }

    @Override
    public byte[] descargarContenido(Long id) {
        Contenido contenido = contenidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
        // Aquí debería implementarse la lógica para leer el archivo y devolverlo en bytes
        return new byte[0];
    }
}

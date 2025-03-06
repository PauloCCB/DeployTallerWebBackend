package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.CursoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import com.Plataforma_Educativa.model.entity.Docente;
import com.Plataforma_Educativa.repository.ContenidoRepository;
import com.Plataforma_Educativa.repository.CursoRepository;
import com.Plataforma_Educativa.repository.DocenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final DocenteRepository docenteRepository;
    private final ContenidoRepository contenidoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository, DocenteRepository docenteRepository, ContenidoRepository contenidoRepository) {
        this.cursoRepository = cursoRepository;
        this.docenteRepository = docenteRepository;
        this.contenidoRepository = contenidoRepository;
    }

    @Override
    public Curso crearCurso(CursoDTO cursoDTO) {
        Curso curso = new Curso();
        curso.setNombre(cursoDTO.getNombre());
        curso.setDescripcion(cursoDTO.getDescripcion());

        // Obtener docentes desde la base de datos
        List<Docente> docentes = docenteRepository.findAllById(cursoDTO.getDocentesIds());
        curso.setDocentes(docentes);

        return cursoRepository.save(curso);
    }

    @Override
    public Curso editarCurso(Long cursoID, Curso curso) {
        return cursoRepository.findById(cursoID).map(cursoExistente -> {
            cursoExistente.setNombre(curso.getNombre());
            cursoExistente.setDescripcion(curso.getDescripcion());
            return cursoRepository.save(cursoExistente);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }

    @Override
    public void eliminarCurso(Long cursoID) {
        cursoRepository.deleteById(cursoID);
    }

    @Override
    public Curso obtenerCursoPorID(Long cursoID) {
        return cursoRepository.findById(cursoID).orElse(null);
    }

    @Override
    public Contenido agregarContenido(Long cursoID, Contenido contenido) {
        return cursoRepository.findById(cursoID).map(curso -> {
            contenido.setCurso(curso);
            return contenidoRepository.save(contenido);
        }).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}

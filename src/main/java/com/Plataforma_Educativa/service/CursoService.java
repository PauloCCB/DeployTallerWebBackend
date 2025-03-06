package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.CursoDTO;
import com.Plataforma_Educativa.model.entity.Contenido;
import com.Plataforma_Educativa.model.entity.Curso;
import java.util.List;

public interface CursoService {
    Curso crearCurso(CursoDTO cursoDTO);
    Curso editarCurso(Long cursoID, Curso curso);
    void eliminarCurso(Long cursoID);
    Curso obtenerCursoPorID(Long cursoID);
    Contenido agregarContenido(Long cursoID, Contenido contenido);
}
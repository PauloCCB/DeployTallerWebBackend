package com.Plataforma_Educativa.mapper;

import com.Plataforma_Educativa.model.dto.CursoDTO;
import com.Plataforma_Educativa.model.entity.Curso;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public CursoDTO toDto(Curso curso) {
        return modelMapper.map(curso, CursoDTO.class);
    }

    public Curso toEntity(CursoDTO cursoDTO) {
        return modelMapper.map(cursoDTO, Curso.class);
    }
}

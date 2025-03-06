package com.Plataforma_Educativa.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private List<Long> docentesIds; //Cambio a lista porque un curso puede tener múltiples docentes
}
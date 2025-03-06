package com.Plataforma_Educativa.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContenidoDTO {
    private Long id;
    private String titulo;
    private String url;
    private Long cursoId;
}

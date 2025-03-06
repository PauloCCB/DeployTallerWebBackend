package com.Plataforma_Educativa.model.dto;

import com.Plataforma_Educativa.model.entity.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleResponse {
    private Long id;
    private ERole name; //analizar si se cambia a string


}

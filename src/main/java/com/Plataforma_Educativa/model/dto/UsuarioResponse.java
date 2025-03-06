package com.Plataforma_Educativa.model.dto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String dni;

}

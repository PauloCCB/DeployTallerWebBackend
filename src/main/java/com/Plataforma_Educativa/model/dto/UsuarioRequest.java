package com.Plataforma_Educativa.model.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;

}

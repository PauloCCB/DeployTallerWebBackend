package com.Plataforma_Educativa.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioInfoResponse {
    //User
    private Long id;
    private String email;
    //UserProfile
    private String firstName;
    private String lastName;
    private String dni;
    //Role
    private List<String> roles;
}

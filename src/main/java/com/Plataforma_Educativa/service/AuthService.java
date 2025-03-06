package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.LoginRequest;
import com.Plataforma_Educativa.model.dto.SignUpRequest;
import com.Plataforma_Educativa.model.dto.UsuarioInfoResponse;
import com.Plataforma_Educativa.security.JwtAuthenticationResponse;

public interface AuthService {

    UsuarioInfoResponse registerUser (SignUpRequest signUpRequest);

    JwtAuthenticationResponse authenticateUser (LoginRequest loginRequest);

}

package com.Plataforma_Educativa.service;



import com.Plataforma_Educativa.exception.BadRequestException;
import com.Plataforma_Educativa.mapper.AuthMapper;
import com.Plataforma_Educativa.model.dto.LoginRequest;
import com.Plataforma_Educativa.model.dto.RoleResponse;
import com.Plataforma_Educativa.model.dto.SignUpRequest;
import com.Plataforma_Educativa.model.dto.UsuarioInfoResponse;
import com.Plataforma_Educativa.model.entity.ERole;
import com.Plataforma_Educativa.model.entity.Role;
import com.Plataforma_Educativa.model.entity.Usuario;
import com.Plataforma_Educativa.repository.UsuarioRepository;
import com.Plataforma_Educativa.security.JwtAuthenticationResponse;
import com.Plataforma_Educativa.security.JwtTokenProvider;
import com.Plataforma_Educativa.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository userRepository;
    private final RoleService roleService;
    private final AuthMapper authMapper;
    private final JwtTokenProvider tokenProvider;


    @Override
    public UsuarioInfoResponse registerUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        Usuario user = authMapper.signUpRequestToUser(signUpRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Usar el passwordEncoder inyectado

        // Asignar el rol por defecto
        RoleResponse defaultRoleResponse = roleService.getRoleByName(ERole.ROLE_USER);
        Role defaultRole = new Role(); // Crear un nuevo objeto Role
        defaultRole.setId(defaultRoleResponse.getId());
        defaultRole.setName(ERole.valueOf(String.valueOf(defaultRoleResponse.getName())));

        user.setRoles(Collections.singletonList(defaultRole));

        Usuario savedUser = userRepository.save(user);
        return authMapper.userToUserInfoResponse(savedUser);
    }

    @Override
    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        // Autenticar usuario
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Establecer autenticación en el contexto de seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generar token JWT
        String jwt = tokenProvider.generateToken(authentication);

        // Obtener detalles del usuario autenticado
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        // Crear y devolver JwtAuthenticationResponse con información adicional
        return new JwtAuthenticationResponse(
                jwt,
                "Bearer",
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }



}

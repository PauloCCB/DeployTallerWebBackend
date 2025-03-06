package com.Plataforma_Educativa.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import com.Plataforma_Educativa.model.dto.LoginRequest;
import com.Plataforma_Educativa.model.dto.SignUpRequest;
import com.Plataforma_Educativa.model.dto.UsuarioInfoResponse;
import com.Plataforma_Educativa.model.entity.Usuario;
import com.Plataforma_Educativa.model.entity.UsuarioPerfil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthMapper {

    private final ModelMapper modelMapper;

    public AuthMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        configureSignUpRequestToUserTypeMap();
    }

    private void configureSignUpRequestToUserTypeMap() {
        TypeMap<SignUpRequest, Usuario> signUpRequestUserTypeMap = modelMapper.
                createTypeMap(SignUpRequest.class, Usuario.class);
        signUpRequestUserTypeMap.addMappings(mapper -> {
            mapper.skip(Usuario::setId); // ID es generado automáticamente
            mapper.map(SignUpRequest::getEmail, Usuario::setEmail);
            mapper.map(SignUpRequest::getPassword, Usuario::setPassword);
            mapper.skip(Usuario::setRoles); // Roles se asignan después
            mapper.skip(Usuario::setUsuarioPerfil); // UserProfile se asigna después
        });
    }

    public Usuario signUpRequestToUser(SignUpRequest request) {
        //Mapeo automatico
        Usuario user = modelMapper.map(request, Usuario.class);

        //Mapeo manual
        UsuarioPerfil userProfile = new UsuarioPerfil();
        userProfile.setFirstName(request.getFirstName());
        userProfile.setLastName(request.getLastName());
        userProfile.setDni(request.getDni());
        userProfile.setUsuario(user);
        user.setUsuarioPerfil(userProfile);

        return user;
    }

    public UsuarioInfoResponse userToUserInfoResponse(Usuario user) {
        // Mapear directamente las propiedades básicas con ModelMapper
        UsuarioInfoResponse response = modelMapper.map(user, UsuarioInfoResponse.class);


        // Usar Optional para manejar UserProfile de manera segura
        Optional.ofNullable(user.getUsuarioPerfil()).ifPresent(userProfile -> {
            response.setFirstName(userProfile.getFirstName());
            response.setLastName(userProfile.getLastName());
            response.setDni(userProfile.getDni());
        });

        // Mapear roles
        List<String> roles = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());
        response.setRoles(roles);
        return response;
    }


    public Usuario loginRequestToUser(LoginRequest request) {
        return modelMapper.map(request, Usuario.class);
    }
}
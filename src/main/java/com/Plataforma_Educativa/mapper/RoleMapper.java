package com.Plataforma_Educativa.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.Plataforma_Educativa.model.dto.RoleRequest;
import com.Plataforma_Educativa.model.dto.RoleResponse;
import com.Plataforma_Educativa.model.entity.Role;

@Component
public class RoleMapper {
    
   
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role roleRequestToRole(RoleRequest request) {
        return modelMapper.map(request, Role.class);
    }

    public RoleResponse roleToRoleResponse(Role role) {
        return modelMapper.map(role, RoleResponse.class);
    }
}

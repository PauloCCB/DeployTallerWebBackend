package com.Plataforma_Educativa.service;

import com.Plataforma_Educativa.model.dto.RoleResponse;
import com.Plataforma_Educativa.model.entity.ERole;

public interface RoleService {
    RoleResponse getRoleByName(ERole name);
}

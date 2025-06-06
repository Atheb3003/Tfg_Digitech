package com.gestion.application.mapper;

import com.gestion.application.dto.RoleRequest;
import com.gestion.application.dto.RoleResponse;
import com.gestion.application.model.Role;

public class RoleMapper {

  public static RoleResponse toDto(Role role) {
    RoleResponse response = new RoleResponse();
    response.setId(role.getId());
    response.setName(role.getName());
    return response;
  }

  public static Role toEntity(RoleRequest request) {
    Role role = new Role();
    role.setName(request.getName());
    return role;
  }
}

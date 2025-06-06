package com.gestion.application.service.role.impl;

import com.gestion.application.dto.RoleRequest;
import com.gestion.application.dto.RoleResponse;
import com.gestion.application.mapper.RoleMapper;
import com.gestion.application.model.Role;
import com.gestion.application.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoleImpl {

  private final RoleRepository roleRepository;

  public RoleResponse createRole(RoleRequest request) {
    Role role = RoleMapper.toEntity(request);
    return RoleMapper.toDto(roleRepository.save(role));
  }
}

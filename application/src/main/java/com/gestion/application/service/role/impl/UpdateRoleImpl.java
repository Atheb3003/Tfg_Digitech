package com.gestion.application.service.role.impl;

import com.gestion.application.dto.RoleRequest;
import com.gestion.application.dto.RoleResponse;
import com.gestion.application.exception.RoleNotFoundException;
import com.gestion.application.mapper.RoleMapper;
import com.gestion.application.model.Role;
import com.gestion.application.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateRoleImpl {

  private final RoleRepository roleRepository;

  public RoleResponse updateRole(Long id, RoleRequest request) {
    Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    role.setName(request.getName());
    return RoleMapper.toDto(roleRepository.save(role));
  }
}

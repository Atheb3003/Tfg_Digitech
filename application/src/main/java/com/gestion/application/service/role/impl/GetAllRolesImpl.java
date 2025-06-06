package com.gestion.application.service.role.impl;

import com.gestion.application.dto.RoleResponse;
import com.gestion.application.mapper.RoleMapper;
import com.gestion.application.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllRolesImpl {

  private final RoleRepository roleRepository;

  public List<RoleResponse> getAllRoles() {
    return roleRepository.findAll().stream().map(RoleMapper::toDto).collect(Collectors.toList());
  }
}

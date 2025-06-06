package com.gestion.application.service.role.impl;

import com.gestion.application.exception.RoleNotFoundException;
import com.gestion.application.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteRoleImpl {

  private final RoleRepository roleRepository;

  public void deleteRole(Long id) {
    if (!roleRepository.existsById(id)) {
      throw new RoleNotFoundException(id);
    }
    roleRepository.deleteById(id);
  }
}

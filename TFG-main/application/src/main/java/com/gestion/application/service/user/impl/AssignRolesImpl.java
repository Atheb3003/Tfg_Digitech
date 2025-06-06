package com.gestion.application.service.user.impl;

import com.gestion.application.exception.UserNotFoundException;
import com.gestion.application.model.Role;
import com.gestion.application.model.User;
import com.gestion.application.repository.RoleRepository;
import com.gestion.application.repository.UserRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignRolesImpl {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public void assignRoles(Long userId, List<String> roleNames) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    Set<Role> roles =
        roleRepository.findAll().stream()
            .filter(role -> roleNames.contains(role.getName()))
            .collect(Collectors.toSet());

    user.setRoles(roles);
    userRepository.save(user);
  }
}

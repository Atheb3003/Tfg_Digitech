package com.gestion.application.service.user.impl;

import com.gestion.application.dto.UserRequest;
import com.gestion.application.dto.UserResponse;
import com.gestion.application.exception.UserNotFoundException;
import com.gestion.application.mapper.UserMapper;
import com.gestion.application.model.Role;
import com.gestion.application.model.User;
import com.gestion.application.repository.RoleRepository;
import com.gestion.application.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserImpl {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserResponse updateUser(Long id, UserRequest request) {
    User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

    user.setUsername(request.getUsername());
    if (request.getPassword() != null && !request.getPassword().isBlank()) {
      user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

    Set<Role> roles =
        roleRepository.findAll().stream()
            .filter(role -> request.getRoles().contains(role.getName()))
            .collect(Collectors.toSet());
    user.setRoles(roles);

    return UserMapper.toDto(userRepository.save(user));
  }
}

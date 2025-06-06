package com.gestion.application.service.user.impl;

import com.gestion.application.dto.UserRequest;
import com.gestion.application.dto.UserResponse;
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
public class CreateUserImpl {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public UserResponse createUser(UserRequest request) {
    // Convertimos el DTO en entidad User
    User user = UserMapper.toEntity(request);

    // Encriptar la contraseña
    String rawPassword = user.getPassword();
    String encryptedPassword = passwordEncoder.encode(rawPassword);
    user.setPassword(encryptedPassword);

    // Asignar roles desde los nombres recibidos
    Set<Role> roles =
        roleRepository.findAll().stream()
            .filter(role -> request.getRoles().contains(role.getName()))
            .collect(Collectors.toSet());

    user.setRoles(roles);

    // Guardar y devolver
    User savedUser = userRepository.save(user);
    return UserMapper.toDto(savedUser);
  }
}

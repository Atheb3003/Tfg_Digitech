package com.gestion.application.mapper;

import com.gestion.application.dto.UserRequest;
import com.gestion.application.dto.UserResponse;
import com.gestion.application.model.Role;
import com.gestion.application.model.User;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

  public static UserResponse toDto(User user) {
    UserResponse response = new UserResponse();
    response.setId(user.getId());
    response.setUsername(user.getUsername());

    Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
    response.setRoles(roles);

    return response;
  }

  public static User toEntity(UserRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());
    return user;
  }
}

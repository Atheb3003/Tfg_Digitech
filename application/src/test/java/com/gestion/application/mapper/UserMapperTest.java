package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.UserRequest;
import com.gestion.application.dto.UserResponse;
import com.gestion.application.model.Role;
import com.gestion.application.model.User;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class UserMapperTest {

  @Test
  void testToDto() {
    User user = new User();
    user.setId(1L);
    user.setUsername("usuarioEjemplo");

    Set<Role> roles = new HashSet<>();
    Role role1 = new Role();
    role1.setName("ADMIN");
    Role role2 = new Role();
    role2.setName("USER");
    roles.add(role1);
    roles.add(role2);

    user.setRoles(roles);

    UserResponse response = UserMapper.toDto(user);

    assertEquals(1L, response.getId());
    assertEquals("usuarioEjemplo", response.getUsername());
    assertTrue(response.getRoles().contains("ADMIN"));
    assertTrue(response.getRoles().contains("USER"));
  }

  @Test
  void testToEntity() {
    UserRequest request = new UserRequest();
    request.setUsername("nuevoUsuario");
    request.setPassword("secreto123");

    User entity = UserMapper.toEntity(request);

    assertEquals("nuevoUsuario", entity.getUsername());
    assertEquals("secreto123", entity.getPassword());
  }
}

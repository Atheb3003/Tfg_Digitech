package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class UserResponseTest {

  @Test
  void testGettersAndSetters() {
    UserResponse response = new UserResponse();

    response.setId(100L);
    response.setUsername("usuario123");

    Set<String> roles = new HashSet<>();
    roles.add("ADMIN");
    roles.add("USER");
    response.setRoles(roles);

    assertEquals(100L, response.getId());
    assertEquals("usuario123", response.getUsername());
    assertEquals(roles, response.getRoles());
  }
}

package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class UserRequestTest {

  @Test
  void testGettersAndSetters() {
    UserRequest request = new UserRequest();

    request.setUsername("usuario123");
    request.setPassword("pass1234");
    List<String> roles = Arrays.asList("ADMIN", "USER");
    request.setRoles(roles);

    assertEquals("usuario123", request.getUsername());
    assertEquals("pass1234", request.getPassword());
    assertEquals(roles, request.getRoles());
  }
}

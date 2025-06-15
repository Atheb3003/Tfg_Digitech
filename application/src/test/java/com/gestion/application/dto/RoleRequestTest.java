package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RoleRequestTest {

  @Test
  void testGettersAndSetters() {
    RoleRequest request = new RoleRequest();

    request.setName("Administrador");

    assertEquals("Administrador", request.getName());
  }
}

package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RoleResponseTest {

  @Test
  void testGettersAndSetters() {
    RoleResponse response = new RoleResponse();

    response.setId(100L);
    response.setName("Usuario");

    assertEquals(100L, response.getId());
    assertEquals("Usuario", response.getName());
  }
}

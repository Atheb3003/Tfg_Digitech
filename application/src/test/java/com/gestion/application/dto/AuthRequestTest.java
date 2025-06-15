package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AuthRequestTest {

  @Test
  void testGettersAndSetters() {
    AuthRequest authRequest = new AuthRequest();

    authRequest.setUsername("usuario1");
    authRequest.setPassword("pass123");

    assertEquals("usuario1", authRequest.getUsername());
    assertEquals("pass123", authRequest.getPassword());
  }
}

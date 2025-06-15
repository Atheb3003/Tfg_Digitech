package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AuthResponseTest {

  @Test
  void testConstructorAndGetter() {
    AuthResponse response = new AuthResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");

    assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9", response.getToken());
  }

  @Test
  void testSetter() {
    AuthResponse response = new AuthResponse("token_inicial");
    response.setToken("token_actualizado");

    assertEquals("token_actualizado", response.getToken());
  }
}

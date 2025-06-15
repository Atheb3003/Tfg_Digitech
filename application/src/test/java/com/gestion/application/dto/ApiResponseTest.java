package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ApiResponseTest {

  @Test
  void testConstructorAndGetters() {
    ApiResponse<String> response = new ApiResponse<>("success", "Datos cargados");

    assertEquals("success", response.getStatus());
    assertEquals("Datos cargados", response.getData());
  }

  @Test
  void testSetters() {
    ApiResponse<Integer> response = new ApiResponse<>("pending", 123);

    response.setStatus("completed");
    response.setData(456);

    assertEquals("completed", response.getStatus());
    assertEquals(456, response.getData());
  }
}

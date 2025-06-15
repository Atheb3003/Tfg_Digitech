package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CreateGroupPhotosRequestTest {

  @Test
  void testGettersAndSetters() {
    CreateGroupPhotosRequest request = new CreateGroupPhotosRequest();

    request.setContactId(101);
    request.setConsultationId(202);
    request.setRevisionId(303);
    request.setTitle("Sesión de fotos");
    request.setDescription("Fotos pre-operatorias");
    request.setIsVisible(true);

    assertEquals(101, request.getContactId());
    assertEquals(202, request.getConsultationId());
    assertEquals(303, request.getRevisionId());
    assertEquals("Sesión de fotos", request.getTitle());
    assertEquals("Fotos pre-operatorias", request.getDescription());
    assertTrue(request.getIsVisible());
  }
}

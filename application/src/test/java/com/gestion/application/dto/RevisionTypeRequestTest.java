package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class RevisionTypeRequestTest {

  @Test
  void testGettersAndSetters() {
    RevisionTypeRequest request = new RevisionTypeRequest();

    request.setTypeName("Control Postoperatorio");

    assertEquals("Control Postoperatorio", request.getTypeName());
  }
}

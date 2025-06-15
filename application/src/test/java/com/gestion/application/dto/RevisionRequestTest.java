package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class RevisionRequestTest {

  @Test
  void testGettersAndSetters() {
    RevisionRequest request = new RevisionRequest();

    request.setIdPatient(101);
    request.setIdType(5);
    request.setIdProtocol(10);
    request.setObservations("Revisión final");
    request.setRevisionDate(LocalDate.of(2025, 6, 14));

    assertEquals(101, request.getIdPatient());
    assertEquals(5, request.getIdType());
    assertEquals(10, request.getIdProtocol());
    assertEquals("Revisión final", request.getObservations());
    assertEquals(LocalDate.of(2025, 6, 14), request.getRevisionDate());
  }
}

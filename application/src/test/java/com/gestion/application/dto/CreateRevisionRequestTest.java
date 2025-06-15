package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class CreateRevisionRequestTest {

  @Test
  void testGettersAndSetters() {
    CreateRevisionRequest request = new CreateRevisionRequest();

    request.setIdContact(123);
    request.setIdType(5);
    request.setIdProtocol(10);
    request.setObservations("Revisión post-operatoria");
    request.setRevisionDate(LocalDate.of(2025, 6, 14));
    request.setIdPatient(null); // Desasociar paciente

    assertEquals(123, request.getIdContact());
    assertEquals(5, request.getIdType());
    assertEquals(10, request.getIdProtocol());
    assertEquals("Revisión post-operatoria", request.getObservations());
    assertEquals(LocalDate.of(2025, 6, 14), request.getRevisionDate());
    assertNull(request.getIdPatient());
  }
}

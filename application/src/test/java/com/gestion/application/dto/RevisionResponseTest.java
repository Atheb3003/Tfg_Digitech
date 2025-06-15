package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class RevisionResponseTest {

  @Test
  void testGettersAndSetters() {
    RevisionResponse response = new RevisionResponse();

    response.setIdRevision(1);
    response.setIdContact(2);
    response.setIdType(3);
    response.setIdProtocol(null);
    response.setObservations("Revisión sin protocolo");
    response.setRevisionDate(LocalDate.of(2025, 6, 14));
    response.setIdPatient(null);
    response.setIsVisible(true);

    assertEquals(1, response.getIdRevision());
    assertEquals(2, response.getIdContact());
    assertEquals(3, response.getIdType());
    assertNull(response.getIdProtocol());
    assertEquals("Revisión sin protocolo", response.getObservations());
    assertEquals(LocalDate.of(2025, 6, 14), response.getRevisionDate());
    assertNull(response.getIdPatient());
    assertTrue(response.getIsVisible());
  }
}

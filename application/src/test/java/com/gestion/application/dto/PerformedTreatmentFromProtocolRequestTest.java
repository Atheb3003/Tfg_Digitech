package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PerformedTreatmentFromProtocolRequestTest {

  @Test
  void testGettersAndSetters() {
    PerformedTreatmentFromProtocolRequest request = new PerformedTreatmentFromProtocolRequest();

    request.setContactId(1001);
    request.setProtocolTreatmentId(2002);
    request.setPerformedDate(LocalDate.of(2025, 6, 14));
    request.setFinalPrice(350.75);
    request.setNotes("Tratamiento realizado con éxito");
    request.setRevisionId(3003);

    assertEquals(1001, request.getContactId());
    assertEquals(2002, request.getProtocolTreatmentId());
    assertEquals(LocalDate.of(2025, 6, 14), request.getPerformedDate());
    assertEquals(350.75, request.getFinalPrice());
    assertEquals("Tratamiento realizado con éxito", request.getNotes());
    assertEquals(3003, request.getRevisionId());
  }
}

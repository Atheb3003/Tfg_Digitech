package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ConsultationRequestTest {

  @Test
  void testGettersAndSetters() {
    ConsultationRequest request = new ConsultationRequest();

    request.setContactId(100);
    request.setTypeId(2);
    request.setFollicularUnits(500);
    request.setInsertionZones("Zona frontal y coronilla");
    request.setObservations("Paciente con buena salud");
    request.setTreatmentDone(true);
    request.setSurgeryReserved(false);
    request.setConsultationDate(LocalDate.of(2025, 6, 14));

    assertEquals(100, request.getContactId());
    assertEquals(2, request.getTypeId());
    assertEquals(500, request.getFollicularUnits());
    assertEquals("Zona frontal y coronilla", request.getInsertionZones());
    assertEquals("Paciente con buena salud", request.getObservations());
    assertTrue(request.getTreatmentDone());
    assertFalse(request.getSurgeryReserved());
    assertEquals(LocalDate.of(2025, 6, 14), request.getConsultationDate());
  }
}

package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ConsultationResponseTest {

  @Test
  void testGettersAndSetters() {
    ConsultationResponse response = new ConsultationResponse();

    response.setIdConsultation(1);
    response.setContactId(10);
    response.setIdType(5);
    response.setFollicularUnits(600);
    response.setInsertionZones("Zona temporal");
    response.setObservations("Sin observaciones");
    response.setTreatmentDone(true);
    response.setSurgeryReserved(false);
    response.setConsultationDate(LocalDate.of(2025, 6, 14));
    response.setIsVisible(true);
    response.setNombreContacto("Juan Pérez");
    response.setType("Consulta inicial");

    assertEquals(1, response.getIdConsultation());
    assertEquals(10, response.getContactId());
    assertEquals(5, response.getIdType());
    assertEquals(600, response.getFollicularUnits());
    assertEquals("Zona temporal", response.getInsertionZones());
    assertEquals("Sin observaciones", response.getObservations());
    assertTrue(response.getTreatmentDone());
    assertFalse(response.getSurgeryReserved());
    assertEquals(LocalDate.of(2025, 6, 14), response.getConsultationDate());
    assertTrue(response.getIsVisible());
    assertEquals("Juan Pérez", response.getNombreContacto());
    assertEquals("Consulta inicial", response.getType());
  }
}

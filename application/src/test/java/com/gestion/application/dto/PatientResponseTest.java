package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PatientResponseTest {

  @Test
  void testGettersAndSetters() {
    PatientResponse response = new PatientResponse();

    response.setIdPatient(101);
    response.setIdContact(202);
    response.setDischargeDate(LocalDate.of(2025, 12, 31));
    response.setIsVisible(true);

    assertEquals(101, response.getIdPatient());
    assertEquals(202, response.getIdContact());
    assertEquals(LocalDate.of(2025, 12, 31), response.getDischargeDate());
    assertTrue(response.getIsVisible());
  }
}

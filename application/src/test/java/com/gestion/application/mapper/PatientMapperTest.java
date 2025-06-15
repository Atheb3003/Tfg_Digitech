package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.PatientResponse;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class PatientMapperTest {

  private final PatientMapper mapper = new PatientMapper();

  @Test
  void testToResponse() {
    Contact contact = new Contact();
    contact.setIdContact(123);

    Patient patient = new Patient();
    patient.setIdPatient(456);
    patient.setContact(contact);
    patient.setDischargeDate(LocalDate.of(2025, 6, 14));
    patient.setIsVisible(true);

    PatientResponse response = mapper.toResponse(patient);

    assertEquals(456, response.getIdPatient());
    assertEquals(123, response.getIdContact());
    assertEquals(LocalDate.of(2025, 6, 14), response.getDischargeDate());
    assertTrue(response.getIsVisible());
  }
}

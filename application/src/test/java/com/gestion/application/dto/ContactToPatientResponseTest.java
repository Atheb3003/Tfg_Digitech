package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactToPatientResponseTest {

  @Test
  void testConstructorAndGetters() {
    ContactToPatientResponse response =
        new ContactToPatientResponse("OK", "Paciente creado correctamente");

    assertEquals("OK", response.getStatus());
    assertEquals("Paciente creado correctamente", response.getMessage());
  }

  @Test
  void testSetters() {
    ContactToPatientResponse response = new ContactToPatientResponse("PENDING", "Proceso en curso");

    response.setStatus("COMPLETED");
    response.setMessage("Paciente registrado con éxito");

    assertEquals("COMPLETED", response.getStatus());
    assertEquals("Paciente registrado con éxito", response.getMessage());
  }
}

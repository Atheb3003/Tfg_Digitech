package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ContactRequestTest {

  @Test
  void testGettersAndSetters() {
    ContactRequest contact = new ContactRequest();

    contact.setName("Carlos");
    contact.setSurname("García");
    contact.setNif("12345678A");
    contact.setBirthDate(LocalDate.of(1990, 5, 20));
    contact.setOccupation("Ingeniero");
    contact.setCountry("España");
    contact.setProvince("Madrid");
    contact.setTown("Madrid");
    contact.setDirection("Calle Falsa 123");
    contact.setTelephoneNumber1("600123456");
    contact.setTelephoneNumber2("600654321");
    contact.setEmail("carlos.garcia@example.com");
    contact.setObservations("Sin observaciones");
    contact.setIsVisible(true);

    assertEquals("Carlos", contact.getName());
    assertEquals("García", contact.getSurname());
    assertEquals("12345678A", contact.getNif());
    assertEquals(LocalDate.of(1990, 5, 20), contact.getBirthDate());
    assertEquals("Ingeniero", contact.getOccupation());
    assertEquals("España", contact.getCountry());
    assertEquals("Madrid", contact.getProvince());
    assertEquals("Madrid", contact.getTown());
    assertEquals("Calle Falsa 123", contact.getDirection());
    assertEquals("600123456", contact.getTelephoneNumber1());
    assertEquals("600654321", contact.getTelephoneNumber2());
    assertEquals("carlos.garcia@example.com", contact.getEmail());
    assertEquals("Sin observaciones", contact.getObservations());
    assertTrue(contact.getIsVisible());
  }
}

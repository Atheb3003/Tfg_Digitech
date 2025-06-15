package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ContactResponseTest {

  @Test
  void testGettersAndSetters() {
    ContactResponse response = new ContactResponse();

    response.setIdContact(123);
    response.setIdContactString("C123");
    response.setName("Ana");
    response.setSurname("Martínez");
    response.setNif("87654321B");
    response.setBirthDate(LocalDate.of(1985, 3, 15));
    response.setOccupation("Doctora");
    response.setCountry("España");
    response.setProvince("Barcelona");
    response.setTown("Barcelona");
    response.setDirection("Av. Diagonal 456");
    response.setTelephoneNumber1("600987654");
    response.setTelephoneNumber2("600456789");
    response.setEmail("ana.martinez@example.com");
    response.setObservations("Paciente VIP");
    response.setIsVisible(true);

    assertEquals(123, response.getIdContact());
    assertEquals("C123", response.getIdContactString());
    assertEquals("Ana", response.getName());
    assertEquals("Martínez", response.getSurname());
    assertEquals("87654321B", response.getNif());
    assertEquals(LocalDate.of(1985, 3, 15), response.getBirthDate());
    assertEquals("Doctora", response.getOccupation());
    assertEquals("España", response.getCountry());
    assertEquals("Barcelona", response.getProvince());
    assertEquals("Barcelona", response.getTown());
    assertEquals("Av. Diagonal 456", response.getDirection());
    assertEquals("600987654", response.getTelephoneNumber1());
    assertEquals("600456789", response.getTelephoneNumber2());
    assertEquals("ana.martinez@example.com", response.getEmail());
    assertEquals("Paciente VIP", response.getObservations());
    assertTrue(response.getIsVisible());
  }
}

package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.ContactRequest;
import com.gestion.application.dto.ContactResponse;
import com.gestion.application.model.Contact;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ContactMapperTest {

  private final ContactMapper mapper = new ContactMapper();

  @Test
  void testToEntity() {
    ContactRequest request = new ContactRequest();
    request.setName("Ana");
    request.setSurname("Martínez");
    request.setNif("87654321B");
    request.setBirthDate(LocalDate.of(1985, 3, 15));
    request.setOccupation("Doctora");
    request.setCountry("España");
    request.setProvince("Barcelona");
    request.setTown("Barcelona");
    request.setDirection("Av. Diagonal 456");
    request.setTelephoneNumber1("600987654");
    request.setTelephoneNumber2("600456789");
    request.setEmail("ana.martinez@example.com");
    request.setObservations("Paciente VIP");

    Contact entity = mapper.toEntity(request);

    assertEquals("Ana", entity.getName());
    assertEquals("Martínez", entity.getSurname());
    assertEquals("87654321B", entity.getNif());
    assertEquals(LocalDate.of(1985, 3, 15), entity.getBirthDate());
    assertEquals("Doctora", entity.getOccupation());
    assertEquals("España", entity.getCountry());
    assertEquals("Barcelona", entity.getProvince());
    assertEquals("Barcelona", entity.getTown());
    assertEquals("Av. Diagonal 456", entity.getDirection());
    assertEquals("600987654", entity.getTelephoneNumber1());
    assertEquals("600456789", entity.getTelephoneNumber2());
    assertEquals("ana.martinez@example.com", entity.getEmail());
    assertEquals("Paciente VIP", entity.getObservations());
    assertTrue(entity.getIsVisible());
  }

  @Test
  void testToResponse() {
    Contact contact = new Contact();
    contact.setIdContact(123);
    contact.setIdContactString("C123");
    contact.setName("Ana");
    contact.setSurname("Martínez");
    contact.setNif("87654321B");
    contact.setBirthDate(LocalDate.of(1985, 3, 15));
    contact.setOccupation("Doctora");
    contact.setCountry("España");
    contact.setProvince("Barcelona");
    contact.setTown("Barcelona");
    contact.setDirection("Av. Diagonal 456");
    contact.setTelephoneNumber1("600987654");
    contact.setTelephoneNumber2("600456789");
    contact.setEmail("ana.martinez@example.com");
    contact.setObservations("Paciente VIP");
    contact.setIsVisible(true);

    ContactResponse response = mapper.toResponse(contact);

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

package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetContactAllDetailsImplTest {

  @Mock private ContactRepository contactRepository;

  @InjectMocks private GetContactAllDetailsImpl getContactAllDetails;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnContactDetailsWhenFound() {
    // Given
    Integer id = 1;
    Contact contact = new Contact();
    contact.setIdContact(id);
    contact.setName("Ana");
    contact.setSurname("Lopez");
    contact.setNif("12345678A");
    contact.setBirthDate(LocalDate.of(1990, 1, 1));
    contact.setOccupation("Engineer");
    contact.setCountry("Spain");
    contact.setProvince("Madrid");
    contact.setTown("Madrid");
    contact.setDirection("Calle Falsa 123");
    contact.setTelephoneNumber1("600000000");
    contact.setTelephoneNumber2("600000001");
    contact.setEmail("ana@example.com");
    contact.setObservations("Detalle completo");
    contact.setIdContactString("AL123");

    when(contactRepository.findById(id)).thenReturn(Optional.of(contact));

    // When
    ContactResponse result = getContactAllDetails.getContactById(id);

    // Then
    assertNotNull(result);
    assertEquals("Ana", result.getName());
    assertEquals("Lopez", result.getSurname());
    assertEquals("12345678A", result.getNif());
    assertEquals(LocalDate.of(1990, 1, 1), result.getBirthDate());
    assertEquals("Engineer", result.getOccupation());
    assertEquals("Spain", result.getCountry());
    assertEquals("Madrid", result.getProvince());
    assertEquals("Madrid", result.getTown());
    assertEquals("Calle Falsa 123", result.getDirection());
    assertEquals("600000000", result.getTelephoneNumber1());
    assertEquals("600000001", result.getTelephoneNumber2());
    assertEquals("ana@example.com", result.getEmail());
    assertEquals("Detalle completo", result.getObservations());
    assertEquals("AL123", result.getIdContactString());

    verify(contactRepository).findById(id);
  }

  @Test
  void shouldThrowWhenContactNotFound() {
    // Given
    Integer id = 999;
    when(contactRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(ContactNotFoundException.class, () -> getContactAllDetails.getContactById(id));
    verify(contactRepository).findById(id);
  }
}

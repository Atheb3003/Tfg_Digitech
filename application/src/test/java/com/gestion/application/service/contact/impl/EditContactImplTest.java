package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactRequest;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class EditContactImplTest {

  @Mock private ContactRepository contactRepository;

  @InjectMocks private EditContactImpl editContactImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldEditContactWhenExists() {
    // Given
    Integer id = 1;

    Contact existingContact = new Contact();
    existingContact.setIdContact(id);

    ContactRequest request = new ContactRequest();
    request.setName("Ana");
    request.setSurname("Lopez");
    request.setNif("12345678A");
    request.setBirthDate(LocalDate.of(1990, 1, 1));
    request.setOccupation("Engineer");
    request.setCountry("Spain");
    request.setProvince("Madrid");
    request.setTown("Madrid");
    request.setDirection("Calle Falsa 123");
    request.setTelephoneNumber1("600000000");
    request.setTelephoneNumber2("600000001");
    request.setEmail("ana@example.com");
    request.setObservations("Updated contact");
    request.setIsVisible(true);

    when(contactRepository.existsById(id)).thenReturn(true);
    when(contactRepository.findById(id)).thenReturn(Optional.of(existingContact));
    when(contactRepository.save(existingContact)).thenReturn(existingContact);

    // When
    Contact result = editContactImpl.editContact(id, request);

    // Then
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
    assertEquals("Updated contact", result.getObservations());
    assertTrue(result.getIsVisible());

    verify(contactRepository).existsById(id);
    verify(contactRepository).findById(id);
    verify(contactRepository).save(existingContact);
  }

  @Test
  void shouldThrowWhenContactDoesNotExist() {
    // Given
    Integer id = 99;
    ContactRequest request = new ContactRequest();

    when(contactRepository.existsById(id)).thenReturn(false);

    // Then
    assertThrows(ContactNotFoundException.class, () -> editContactImpl.editContact(id, request));
    verify(contactRepository).existsById(id);
    verify(contactRepository, never()).findById(any());
    verify(contactRepository, never()).save(any());
  }
}

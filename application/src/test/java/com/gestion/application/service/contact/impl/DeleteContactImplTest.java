package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteContactImplTest {

  @Mock private ContactRepository contactRepository;

  @InjectMocks private DeleteContactImpl deleteContactImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldDeleteContactWhenExists() {
    // Given
    Integer id = 1;
    when(contactRepository.existsById(id)).thenReturn(true);

    // When
    deleteContactImpl.deleteContact(id);

    // Then
    verify(contactRepository).existsById(id);
    verify(contactRepository).deleteById(id);
  }

  @Test
  void shouldThrowWhenContactDoesNotExist() {
    // Given
    Integer id = 99;
    when(contactRepository.existsById(id)).thenReturn(false);

    // Then
    assertThrows(ContactNotFoundException.class, () -> deleteContactImpl.deleteContact(id));
    verify(contactRepository).existsById(id);
    verify(contactRepository, never()).deleteById(any());
  }
}

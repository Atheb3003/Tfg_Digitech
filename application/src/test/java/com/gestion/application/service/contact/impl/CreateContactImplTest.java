package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.model.Contact;
import com.gestion.application.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateContactImplTest {

  @Mock private ContactRepository contactRepository;

  @InjectMocks private CreateContactImpl createContactImpl;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldGenerateIdAndSaveContactWhenIdIsNull() {
    // Given
    Contact contact = new Contact();
    contact.setName("Ana");
    contact.setSurname("Lopez");
    contact.setIdContactString(null);

    Contact savedContact = new Contact();
    savedContact.setName("Ana");
    savedContact.setSurname("Lopez");
    savedContact.setIdContactString("ALXYZ12"); // simulación de ID generado

    when(contactRepository.save(any(Contact.class)))
        .thenAnswer(
            invocation -> {
              Contact input = invocation.getArgument(0);
              assertNotNull(input.getIdContactString());
              assertTrue(input.getIdContactString().startsWith("AL"));
              return savedContact;
            });

    // When
    Contact result = createContactImpl.crearContacto(contact);

    // Then
    assertNotNull(result);
    assertEquals(savedContact.getIdContactString(), result.getIdContactString());
    verify(contactRepository).save(any(Contact.class));
  }

  @Test
  void shouldNotGenerateIdWhenAlreadySet() {
    // Given
    Contact contact = new Contact();
    contact.setName("Carlos");
    contact.setSurname("Perez");
    contact.setIdContactString("CP12345");

    when(contactRepository.save(contact)).thenReturn(contact);

    // When
    Contact result = createContactImpl.crearContacto(contact);

    // Then
    assertEquals("CP12345", result.getIdContactString());
    verify(contactRepository).save(contact);
  }
}

package com.gestion.application.service.contact.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ContactToPatientResponse;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.model.Contact;
import com.gestion.application.model.Patient;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.PatientRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class TransformContactToPatientImplTest {

  @Mock private ContactRepository contactRepository;

  @Mock private PatientRepository patientRepository;

  @InjectMocks private TransformContactToPatientImpl service;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldTransformContactToPatientSuccessfully() {
    // Given
    Integer contactId = 1;
    Contact contact = new Contact();
    contact.setIdContact(contactId);
    contact.setPatient(null);

    Patient savedPatient = new Patient();
    savedPatient.setIdPatient(10);
    savedPatient.setContact(contact);

    when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
    when(patientRepository.save(any(Patient.class)))
        .thenAnswer(
            invocation -> {
              Patient p = invocation.getArgument(0);
              p.setIdPatient(10); // simulate DB assigning ID
              return p;
            });

    // When
    ContactToPatientResponse response = service.transformContactToPatient(contactId);

    // Then
    assertEquals("created", response.getStatus());
    assertTrue(response.getMessage().contains("transformado correctamente"));
    verify(contactRepository).findById(contactId);
    verify(patientRepository).save(any(Patient.class));
  }

  @Test
  void shouldThrowWhenContactNotFound() {
    when(contactRepository.findById(99)).thenReturn(Optional.empty());
    assertThrows(ContactNotFoundException.class, () -> service.transformContactToPatient(99));
  }

  @Test
  void shouldThrowWhenContactIsAlreadyPatient() {
    Contact contact = new Contact();
    contact.setIdContact(1);
    contact.setPatient(new Patient());

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));

    assertThrows(IllegalStateException.class, () -> service.transformContactToPatient(1));
  }
}

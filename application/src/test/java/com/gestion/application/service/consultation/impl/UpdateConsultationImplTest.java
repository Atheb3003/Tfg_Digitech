package com.gestion.application.service.consultation.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.exception.*;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.model.Consultation;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.*;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class UpdateConsultationImplTest {

  @Mock private ConsultationRepository consultationRepository;
  @Mock private ContactRepository contactRepository;

  @Mock
  private PatientRepository patientRepository; // not used in logic, but needed for constructor

  @Mock private ConsultationTypeRepository consultationTypeRepository;
  @Mock private ConsultationMapper consultationMapper;

  @InjectMocks private UpdateConsultationImpl updateConsultation;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldUpdateConsultationSuccessfully() {
    // Given
    Integer id = 1;
    Integer contactId = 10;
    Integer typeId = 20;

    Consultation existing = new Consultation();
    existing.setIdConsultation(id);
    existing.setIsVisible(true);

    Contact contact = new Contact();
    contact.setIdContact(contactId);

    ConsultationType type = new ConsultationType();
    type.setIdType(typeId);

    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(contactId);
    request.setTypeId(typeId);
    request.setInsertionZones("Zone A");
    request.setObservations("Everything OK");
    request.setConsultationDate(LocalDate.of(2025, 1, 1));

    Consultation updated = new Consultation();
    updated.setIdConsultation(id);
    updated.setContact(contact);
    updated.setType(type);
    updated.setIsVisible(true);

    ConsultationResponse response = new ConsultationResponse();
    response.setIdConsultation(id);

    when(consultationRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));
    when(consultationTypeRepository.findById(typeId)).thenReturn(Optional.of(type));
    when(consultationRepository.save(existing)).thenReturn(updated);
    when(consultationMapper.toResponse(updated)).thenReturn(response);

    // When
    ConsultationResponse result = updateConsultation.update(id, request);

    // Then
    assertEquals(id, result.getIdConsultation());
    verify(consultationRepository).save(existing);
  }

  @Test
  void shouldThrowWhenConsultationNotFound() {
    // Given
    Integer id = 999;
    ConsultationRequest request = new ConsultationRequest();
    when(consultationRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(ConsultationNotFoundException.class, () -> updateConsultation.update(id, request));
    verify(consultationRepository).findById(id);
  }

  @Test
  void shouldThrowWhenContactNotFound() {
    // Given
    Integer id = 1;
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(88);
    request.setTypeId(1);

    Consultation existing = new Consultation();
    when(consultationRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(88)).thenReturn(Optional.empty());

    // Then
    assertThrows(ContactNotFoundException.class, () -> updateConsultation.update(id, request));
  }

  @Test
  void shouldThrowWhenConsultationTypeNotFound() {
    // Given
    Integer id = 1;
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(1);
    request.setTypeId(99);

    Consultation existing = new Consultation();
    Contact contact = new Contact();

    when(consultationRepository.findById(id)).thenReturn(Optional.of(existing));
    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(consultationTypeRepository.findById(99)).thenReturn(Optional.empty());

    // Then
    assertThrows(
        ConsultationTypeNotFoundException.class, () -> updateConsultation.update(id, request));
  }
}

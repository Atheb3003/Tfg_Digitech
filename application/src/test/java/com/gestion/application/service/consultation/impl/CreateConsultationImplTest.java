package com.gestion.application.service.consultation.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.exception.ConsultationCreationException;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.model.Consultation;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.model.Contact;
import com.gestion.application.repository.ConsultationRepository;
import com.gestion.application.repository.ConsultationTypeRepository;
import com.gestion.application.repository.ContactRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateConsultationImplTest {

  @Mock private ConsultationRepository consultationRepository;

  @Mock private ContactRepository contactRepository;

  @Mock private ConsultationTypeRepository consultationTypeRepository;

  @Mock private ConsultationMapper consultationMapper;

  @InjectMocks private CreateConsultationImpl createConsultation;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateConsultationSuccessfully() {
    // Given
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(1);
    request.setTypeId(2);

    Contact contact = new Contact();
    contact.setIdContact(1);

    ConsultationType type = new ConsultationType();
    type.setIdType(2);

    Consultation consultationEntity = new Consultation();
    Consultation savedConsultation = new Consultation();
    savedConsultation.setIdConsultation(100);
    savedConsultation.setContact(contact);
    savedConsultation.setType(type);

    ConsultationResponse expectedResponse = new ConsultationResponse();
    expectedResponse.setIdConsultation(100);

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(consultationTypeRepository.findById(2)).thenReturn(Optional.of(type));
    when(consultationMapper.toEntity(request)).thenReturn(consultationEntity);
    when(consultationRepository.save(consultationEntity)).thenReturn(savedConsultation);
    when(consultationMapper.toResponse(savedConsultation)).thenReturn(expectedResponse);

    // When
    ConsultationResponse actualResponse = createConsultation.create(request);

    // Then
    assertEquals(expectedResponse.getIdConsultation(), actualResponse.getIdConsultation());
    verify(consultationRepository).save(consultationEntity);
  }

  @Test
  void shouldThrowContactNotFoundException() {
    // Given
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(99);
    request.setTypeId(1);

    when(contactRepository.findById(99)).thenReturn(Optional.empty());

    // Then
    assertThrows(ContactNotFoundException.class, () -> createConsultation.create(request));
    verify(contactRepository).findById(99);
    verifyNoInteractions(consultationTypeRepository, consultationRepository, consultationMapper);
  }

  @Test
  void shouldThrowProtocolNotFoundException() {
    // Given
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(1);
    request.setTypeId(99);

    when(contactRepository.findById(1)).thenReturn(Optional.of(new Contact()));
    when(consultationTypeRepository.findById(99)).thenReturn(Optional.empty());

    // Then
    assertThrows(ProtocolNotFoundException.class, () -> createConsultation.create(request));
    verify(contactRepository).findById(1);
    verify(consultationTypeRepository).findById(99);
    verifyNoInteractions(consultationRepository, consultationMapper);
  }

  @Test
  void shouldThrowConsultationCreationExceptionOnRepositoryFailure() {
    // Given
    ConsultationRequest request = new ConsultationRequest();
    request.setContactId(1);
    request.setTypeId(2);

    Contact contact = new Contact();
    ConsultationType type = new ConsultationType();
    Consultation consultation = new Consultation();

    when(contactRepository.findById(1)).thenReturn(Optional.of(contact));
    when(consultationTypeRepository.findById(2)).thenReturn(Optional.of(type));
    when(consultationMapper.toEntity(request)).thenReturn(consultation);
    when(consultationRepository.save(consultation)).thenThrow(new RuntimeException("DB error"));

    // Then
    assertThrows(ConsultationCreationException.class, () -> createConsultation.create(request));
  }
}

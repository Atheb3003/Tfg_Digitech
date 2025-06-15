package com.gestion.application.service.consultation.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.repository.ConsultationRepository;
import com.gestion.application.repository.ContactRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetConsultationsByContactImplTest {

  @Mock private ConsultationRepository consultationRepository;

  @Mock private ContactRepository contactRepository;

  @Mock private ConsultationMapper consultationMapper;

  @InjectMocks private GetConsultationsByContactImpl getConsultationsByContact;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldThrowContactNotFoundExceptionWhenContactDoesNotExist() {
    // Given
    Integer contactId = 99;

    when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

    // Then
    assertThrows(
        ContactNotFoundException.class, () -> getConsultationsByContact.getByContact(contactId));
    verify(contactRepository).findById(contactId);
    verifyNoInteractions(consultationRepository, consultationMapper);
  }
}

package com.gestion.application.service.consultation.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ConsultationNotFoundException;
import com.gestion.application.repository.ConsultationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteConsultationImplTest {

  @Mock private ConsultationRepository consultationRepository;

  @InjectMocks private DeleteConsultationImpl deleteConsultation;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldDeleteConsultationWhenExists() {
    // Given
    Integer id = 1;
    when(consultationRepository.existsById(id)).thenReturn(true);

    // When
    deleteConsultation.delete(id);

    // Then
    verify(consultationRepository).existsById(id);
    verify(consultationRepository).deleteById(id);
  }

  @Test
  void shouldThrowExceptionWhenConsultationNotFound() {
    // Given
    Integer id = 999;
    when(consultationRepository.existsById(id)).thenReturn(false);

    // Then
    assertThrows(ConsultationNotFoundException.class, () -> deleteConsultation.delete(id));
    verify(consultationRepository).existsById(id);
    verify(consultationRepository, never()).deleteById(any());
  }
}

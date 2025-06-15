package com.gestion.application.service.consultation.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ConsultationAlreadyVisibleException;
import com.gestion.application.exception.ConsultationNotFoundException;
import com.gestion.application.model.Consultation;
import com.gestion.application.repository.ConsultationRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class MakeConsultationVisibleImplTest {

  @Mock private ConsultationRepository consultationRepository;

  @InjectMocks private MakeConsultationVisibleImpl makeConsultationVisible;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldMakeConsultationVisibleWhenCurrentlyInvisible() {
    // Given
    Integer id = 1;
    Consultation invisibleConsultation = new Consultation();
    invisibleConsultation.setIdConsultation(id);
    invisibleConsultation.setIsVisible(false);

    Consultation savedConsultation = new Consultation();
    savedConsultation.setIdConsultation(id);
    savedConsultation.setIsVisible(true);

    when(consultationRepository.findById(id)).thenReturn(Optional.of(invisibleConsultation));
    when(consultationRepository.save(invisibleConsultation)).thenReturn(savedConsultation);

    // When
    Consultation result = makeConsultationVisible.setVisible(id);

    // Then
    assertTrue(result.getIsVisible());
    verify(consultationRepository).findById(id);
    verify(consultationRepository).save(invisibleConsultation);
  }

  @Test
  void shouldThrowWhenConsultationIsAlreadyVisible() {
    // Given
    Integer id = 2;
    Consultation visibleConsultation = new Consultation();
    visibleConsultation.setIdConsultation(id);
    visibleConsultation.setIsVisible(true);

    when(consultationRepository.findById(id)).thenReturn(Optional.of(visibleConsultation));

    // Then
    assertThrows(
        ConsultationAlreadyVisibleException.class, () -> makeConsultationVisible.setVisible(id));
    verify(consultationRepository).findById(id);
    verify(consultationRepository, never()).save(any());
  }

  @Test
  void shouldThrowWhenConsultationNotFound() {
    // Given
    Integer id = 999;
    when(consultationRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(ConsultationNotFoundException.class, () -> makeConsultationVisible.setVisible(id));
    verify(consultationRepository).findById(id);
    verifyNoMoreInteractions(consultationRepository);
  }
}

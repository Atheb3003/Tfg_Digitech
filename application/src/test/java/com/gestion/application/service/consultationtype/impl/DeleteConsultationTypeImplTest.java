package com.gestion.application.service.consultationtype.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.repository.ConsultationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class DeleteConsultationTypeImplTest {

  @Mock private ConsultationTypeRepository consultationTypeRepository;

  @InjectMocks private DeleteConsultationTypeImpl deleteConsultationType;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldDeleteConsultationTypeWhenExists() {
    // Given
    Integer id = 1;
    when(consultationTypeRepository.existsById(id)).thenReturn(true);

    // When
    deleteConsultationType.delete(id);

    // Then
    verify(consultationTypeRepository).existsById(id);
    verify(consultationTypeRepository).deleteById(id);
  }

  @Test
  void shouldThrowWhenConsultationTypeDoesNotExist() {
    // Given
    Integer id = 999;
    when(consultationTypeRepository.existsById(id)).thenReturn(false);

    // Then
    assertThrows(ConsultationTypeNotFoundException.class, () -> deleteConsultationType.delete(id));
    verify(consultationTypeRepository).existsById(id);
    verify(consultationTypeRepository, never()).deleteById(any());
  }
}

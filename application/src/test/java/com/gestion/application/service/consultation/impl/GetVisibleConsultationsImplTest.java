package com.gestion.application.service.consultation.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.mapper.ConsultationMapper;
import com.gestion.application.repository.ConsultationRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetVisibleConsultationsImplTest {

  @Mock private ConsultationRepository consultationRepository;

  @Mock private ConsultationMapper consultationMapper;

  @InjectMocks private GetVisibleConsultationsImpl getVisibleConsultations;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnEmptyListWhenNoVisibleConsultationsExist() {
    // Given
    when(consultationRepository.findAllByIsVisibleTrue()).thenReturn(Collections.emptyList());

    // When
    List<ConsultationResponse> result = getVisibleConsultations.getVisible();

    // Then
    assertNotNull(result);
    assertTrue(result.isEmpty());

    verify(consultationRepository).findAllByIsVisibleTrue();
    verifyNoInteractions(consultationMapper);
  }
}

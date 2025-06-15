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

class GetAllConsultationsImplTest {

  @Mock private ConsultationRepository consultationRepository;

  @Mock private ConsultationMapper consultationMapper;

  @InjectMocks private GetAllConsultationsImpl getAllConsultations;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnEmptyListWhenNoConsultationsExist() {
    // Given
    when(consultationRepository.findAll()).thenReturn(Collections.emptyList());

    // When
    List<ConsultationResponse> result = getAllConsultations.getAll();

    // Then
    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(consultationRepository).findAll();
    verifyNoInteractions(consultationMapper);
  }
}

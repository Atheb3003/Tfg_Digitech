package com.gestion.application.service.consultationtype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.repository.ConsultationTypeRepository;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetAllConsultationTypesImplTest {

  @Mock private ConsultationTypeRepository consultationTypeRepository;

  @Mock private ConsultationTypeMapper consultationTypeMapper;

  @InjectMocks private GetAllConsultationTypesImpl getAllConsultationTypes;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnEmptyListWhenNoConsultationTypesExist() {
    // Given
    when(consultationTypeRepository.findAll()).thenReturn(Collections.emptyList());

    // When
    List<ConsultationTypeResponse> result = getAllConsultationTypes.getAll();

    // Then
    assertNotNull(result);
    assertTrue(result.isEmpty());

    verify(consultationTypeRepository).findAll();
    verifyNoInteractions(consultationTypeMapper);
  }
}

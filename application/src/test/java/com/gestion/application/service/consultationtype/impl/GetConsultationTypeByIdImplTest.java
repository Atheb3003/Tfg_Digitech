package com.gestion.application.service.consultationtype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.repository.ConsultationTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class GetConsultationTypeByIdImplTest {

  @Mock private ConsultationTypeRepository consultationTypeRepository;

  @Mock private ConsultationTypeMapper consultationTypeMapper;

  @InjectMocks private GetConsultationTypeByIdImpl getConsultationTypeById;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnConsultationTypeWhenExists() {
    // Given
    Integer id = 1;
    ConsultationType entity = new ConsultationType();
    ConsultationTypeResponse expectedResponse = new ConsultationTypeResponse();

    when(consultationTypeRepository.findById(id)).thenReturn(Optional.of(entity));
    when(consultationTypeMapper.toResponse(entity)).thenReturn(expectedResponse);

    // When
    ConsultationTypeResponse result = getConsultationTypeById.getById(id);

    // Then
    assertEquals(expectedResponse, result);
    verify(consultationTypeRepository).findById(id);
    verify(consultationTypeMapper).toResponse(entity);
  }

  @Test
  void shouldThrowWhenConsultationTypeNotFound() {
    // Given
    Integer id = 99;
    when(consultationTypeRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(
        ConsultationTypeNotFoundException.class, () -> getConsultationTypeById.getById(id));
    verify(consultationTypeRepository).findById(id);
    verifyNoInteractions(consultationTypeMapper);
  }
}

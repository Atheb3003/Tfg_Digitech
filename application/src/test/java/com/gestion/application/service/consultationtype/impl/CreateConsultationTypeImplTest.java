package com.gestion.application.service.consultationtype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.exception.ConsultationTypeInvalidDataException;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.repository.ConsultationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateConsultationTypeImplTest {

  @Mock private ConsultationTypeRepository consultationTypeRepository;

  @Mock private ConsultationTypeMapper consultationTypeMapper;

  @InjectMocks private CreateConsultationTypeImpl createConsultationType;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateConsultationTypeSuccessfully() {
    // Given
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName("Hair Transplant");

    ConsultationType entity = new ConsultationType();
    ConsultationType savedEntity = new ConsultationType();
    ConsultationTypeResponse response = new ConsultationTypeResponse();

    when(consultationTypeMapper.toEntity(request)).thenReturn(entity);
    when(consultationTypeRepository.save(entity)).thenReturn(savedEntity);
    when(consultationTypeMapper.toResponse(savedEntity)).thenReturn(response);

    // When
    ConsultationTypeResponse result = createConsultationType.create(request);

    // Then
    assertEquals(response, result);
    verify(consultationTypeMapper).toEntity(request);
    verify(consultationTypeRepository).save(entity);
    verify(consultationTypeMapper).toResponse(savedEntity);
  }

  @Test
  void shouldThrowWhenTypeNameIsNull() {
    // Given
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName(null);

    // Then
    assertThrows(
        ConsultationTypeInvalidDataException.class, () -> createConsultationType.create(request));
    verifyNoInteractions(consultationTypeMapper, consultationTypeRepository);
  }

  @Test
  void shouldThrowWhenTypeNameIsBlank() {
    // Given
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName("   ");

    // Then
    assertThrows(
        ConsultationTypeInvalidDataException.class, () -> createConsultationType.create(request));
    verifyNoInteractions(consultationTypeMapper, consultationTypeRepository);
  }
}

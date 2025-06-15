package com.gestion.application.service.consultationtype.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.exception.ConsultationTypeInvalidDataException;
import com.gestion.application.exception.ConsultationTypeNotFoundException;
import com.gestion.application.mapper.ConsultationTypeMapper;
import com.gestion.application.model.ConsultationType;
import com.gestion.application.repository.ConsultationTypeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class UpdateConsultationTypeImplTest {

  @Mock private ConsultationTypeRepository consultationTypeRepository;

  @Mock private ConsultationTypeMapper consultationTypeMapper;

  @InjectMocks private UpdateConsultationTypeImpl updateConsultationType;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldUpdateConsultationTypeSuccessfully() {
    // Given
    Integer id = 1;
    ConsultationType existing = new ConsultationType();
    existing.setIdType(id);
    existing.setTypeName("Old Name");

    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName("New Name");

    ConsultationType updated = new ConsultationType();
    updated.setIdType(id);
    updated.setTypeName("New Name");

    ConsultationTypeResponse response = new ConsultationTypeResponse();
    response.setIdType(id);
    response.setTypeName("New Name");

    when(consultationTypeRepository.findById(id)).thenReturn(Optional.of(existing));
    when(consultationTypeRepository.save(existing)).thenReturn(updated);
    when(consultationTypeMapper.toResponse(updated)).thenReturn(response);

    // When
    ConsultationTypeResponse result = updateConsultationType.update(id, request);

    // Then
    assertEquals("New Name", result.getTypeName());
    verify(consultationTypeRepository).findById(id);
    verify(consultationTypeRepository).save(existing);
    verify(consultationTypeMapper).toResponse(updated);
  }

  @Test
  void shouldThrowWhenTypeNotFound() {
    // Given
    Integer id = 99;
    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName("Valid");

    when(consultationTypeRepository.findById(id)).thenReturn(Optional.empty());

    // Then
    assertThrows(
        ConsultationTypeNotFoundException.class, () -> updateConsultationType.update(id, request));
    verify(consultationTypeRepository).findById(id);
    verifyNoMoreInteractions(consultationTypeRepository);
  }

  @Test
  void shouldThrowWhenTypeNameIsNull() {
    // Given
    Integer id = 1;
    ConsultationType existing = new ConsultationType();

    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName(null);

    when(consultationTypeRepository.findById(id)).thenReturn(Optional.of(existing));

    // Then
    assertThrows(
        ConsultationTypeInvalidDataException.class,
        () -> updateConsultationType.update(id, request));
    verify(consultationTypeRepository).findById(id);
    verify(consultationTypeRepository, never()).save(any());
  }

  @Test
  void shouldThrowWhenTypeNameIsBlank() {
    // Given
    Integer id = 1;
    ConsultationType existing = new ConsultationType();

    ConsultationTypeRequest request = new ConsultationTypeRequest();
    request.setTypeName("   ");

    when(consultationTypeRepository.findById(id)).thenReturn(Optional.of(existing));

    // Then
    assertThrows(
        ConsultationTypeInvalidDataException.class,
        () -> updateConsultationType.update(id, request));
    verify(consultationTypeRepository).findById(id);
    verify(consultationTypeRepository, never()).save(any());
  }
}

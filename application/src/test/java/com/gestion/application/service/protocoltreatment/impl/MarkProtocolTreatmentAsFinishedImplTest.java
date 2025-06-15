package com.gestion.application.service.protocoltreatment.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.exception.ProtocolTreatmentNotFoundException;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class MarkProtocolTreatmentAsFinishedImplTest {

  @Mock private ProtocolTreatmentRepository repository;

  @InjectMocks private MarkProtocolTreatmentAsFinishedImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldMarkTreatmentAsFinished() {
    Integer id = 1;
    ProtocolTreatment treatment = new ProtocolTreatment();
    treatment.setIsFinished(false);

    when(repository.findById(id)).thenReturn(Optional.of(treatment));
    when(repository.save(treatment)).thenReturn(treatment);

    SuccessfulMarkAsPaidResponse response = service.markAsFinished(id);

    assertEquals("success", response.getStatus());
    assertEquals("Tratamiento con ID 1 marcado como realizado", response.getMessage());
    assertTrue(treatment.getIsFinished());
    verify(repository).save(treatment);
  }

  @Test
  void shouldThrowWhenTreatmentNotFound() {
    Integer id = 999;
    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(ProtocolTreatmentNotFoundException.class, () -> service.markAsFinished(id));

    verify(repository, never()).save(any());
  }
}

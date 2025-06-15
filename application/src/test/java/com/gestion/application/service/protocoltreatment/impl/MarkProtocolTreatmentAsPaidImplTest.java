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

public class MarkProtocolTreatmentAsPaidImplTest {

  @Mock private ProtocolTreatmentRepository repository;

  @InjectMocks private MarkProtocolTreatmentAsPaidImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldMarkTreatmentAsPaid() {
    Integer id = 1;
    ProtocolTreatment treatment = new ProtocolTreatment();
    treatment.setIsPaid(false);

    when(repository.findById(id)).thenReturn(Optional.of(treatment));
    when(repository.save(treatment)).thenReturn(treatment);

    SuccessfulMarkAsPaidResponse response = service.markAsPaid(id);

    assertEquals("success", response.getStatus());
    assertEquals("Tratamiento con ID 1 marcado como pagado", response.getMessage());
    assertTrue(treatment.getIsPaid());
    verify(repository).save(treatment);
  }

  @Test
  void shouldThrowWhenTreatmentNotFound() {
    Integer id = 999;
    when(repository.findById(id)).thenReturn(Optional.empty());

    assertThrows(ProtocolTreatmentNotFoundException.class, () -> service.markAsPaid(id));

    verify(repository, never()).save(any());
  }
}

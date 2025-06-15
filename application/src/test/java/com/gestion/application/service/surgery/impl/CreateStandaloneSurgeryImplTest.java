package com.gestion.application.service.surgery.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.CreateSurgeryRequest;
import com.gestion.application.model.Surgery;
import com.gestion.application.repository.SurgeryRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CreateStandaloneSurgeryImplTest {

  @Mock private SurgeryRepository surgeryRepository;

  @InjectMocks private CreateStandaloneSurgeryImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void create_shouldSaveSurgeryWithIsVisibleTrue() {
    CreateSurgeryRequest request = new CreateSurgeryRequest();
    request.setDate(LocalDate.of(2025, 6, 14));
    request.setObservations("Test observations");

    Surgery surgeryToSave = new Surgery();
    surgeryToSave.setDate(request.getDate());
    surgeryToSave.setObservations(request.getObservations());
    surgeryToSave.setIsVisible(true);

    Surgery savedSurgery = new Surgery();
    savedSurgery.setIdSurgery(1);
    savedSurgery.setDate(request.getDate());
    savedSurgery.setObservations(request.getObservations());
    savedSurgery.setIsVisible(true);

    when(surgeryRepository.save(any(Surgery.class))).thenReturn(savedSurgery);

    Surgery result = service.create(request);

    assertNotNull(result);
    assertEquals(1, result.getIdSurgery());
    assertEquals(request.getDate(), result.getDate());
    assertEquals(request.getObservations(), result.getObservations());
    assertTrue(result.getIsVisible());

    verify(surgeryRepository).save(any(Surgery.class));
  }
}

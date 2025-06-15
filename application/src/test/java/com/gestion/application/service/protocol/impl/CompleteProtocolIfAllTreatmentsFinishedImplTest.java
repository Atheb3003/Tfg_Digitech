package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.SuccessfulUpdateResponse;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProtocolRepository;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CompleteProtocolIfAllTreatmentsFinishedImplTest {

  @Mock private ProtocolRepository repository;

  @InjectMocks private CompleteProtocolIfAllTreatmentsFinishedImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldMarkProtocolAsFinishedWhenAllTreatmentsAreFinished() {
    // Preparar datos
    Protocol protocol = new Protocol();
    protocol.setIdProtocol(1);
    protocol.setIsFinished(false);

    ProtocolTreatment treatment1 = new ProtocolTreatment();
    treatment1.setIsFinished(true);
    ProtocolTreatment treatment2 = new ProtocolTreatment();
    treatment2.setIsFinished(true);

    protocol.setTreatments(Arrays.asList(treatment1, treatment2));

    // Simular repositorio
    when(repository.findById(1)).thenReturn(Optional.of(protocol));
    when(repository.save(protocol)).thenReturn(protocol);

    // Ejecutar
    SuccessfulUpdateResponse response = service.markAsFinishedIfApplicable(1);

    // Verificar
    assertEquals("success", response.getStatus());
    assertTrue(protocol.getIsFinished());
    verify(repository).save(protocol);
  }

  @Test
  void shouldNotMarkProtocolWhenSomeTreatmentsAreUnfinished() {
    Protocol protocol = new Protocol();
    protocol.setIdProtocol(2);
    protocol.setIsFinished(false);

    ProtocolTreatment finished = new ProtocolTreatment();
    finished.setIsFinished(true);
    ProtocolTreatment unfinished = new ProtocolTreatment();
    unfinished.setIsFinished(false);

    protocol.setTreatments(Arrays.asList(finished, unfinished));

    when(repository.findById(2)).thenReturn(Optional.of(protocol));

    SuccessfulUpdateResponse response = service.markAsFinishedIfApplicable(2);

    assertEquals("ignored", response.getStatus());
    assertFalse(protocol.getIsFinished());
    verify(repository, never()).save(any());
  }

  @Test
  void shouldThrowWhenProtocolNotFound() {
    when(repository.findById(999)).thenReturn(Optional.empty());

    assertThrows(
        ProtocolNotFoundException.class,
        () -> {
          service.markAsFinishedIfApplicable(999);
        });

    verify(repository, never()).save(any());
  }
}

package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.model.Protocol;
import com.gestion.application.model.ProtocolTreatment;
import com.gestion.application.repository.ProtocolRepository;
import com.gestion.application.repository.ProtocolTreatmentRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class UpdateProtocolTreatmentImplTest {

  @Mock private ProtocolTreatmentRepository treatmentRepository;

  @Mock private ProtocolRepository protocolRepository;

  @InjectMocks private UpdateProtocolTreatmentImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldMarkTreatmentAsFinishedAndUpdateProtocolIfAllFinished() {
    // Arrange
    Protocol protocol = new Protocol();
    protocol.setIsFinished(false);

    ProtocolTreatment t1 = new ProtocolTreatment();
    t1.setIsFinished(true);
    t1.setProtocol(protocol);

    ProtocolTreatment t2 = new ProtocolTreatment();
    t2.setIsFinished(false); // Este será actualizado
    t2.setProtocol(protocol);

    protocol.setTreatments(List.of(t1, t2));
    t2.setProtocol(protocol); // necesario para el acceso inverso

    when(treatmentRepository.findById(2)).thenReturn(Optional.of(t2));

    // Act
    service.markTreatmentAsFinished(2);

    // Assert
    assertTrue(t2.getIsFinished());
    assertTrue(protocol.getIsFinished());
    verify(treatmentRepository).save(t2);
    verify(protocolRepository).save(protocol);
  }

  @Test
  void shouldMarkTreatmentAsFinishedButNotUpdateProtocolIfNotAllFinished() {
    // Arrange
    Protocol protocol = new Protocol();
    protocol.setIsFinished(false);

    ProtocolTreatment t1 = new ProtocolTreatment();
    t1.setIsFinished(false); // sigue sin terminar
    t1.setProtocol(protocol);

    ProtocolTreatment t2 = new ProtocolTreatment();
    t2.setIsFinished(false); // este se marca como terminado ahora
    t2.setProtocol(protocol);

    protocol.setTreatments(List.of(t1, t2));
    t2.setProtocol(protocol);

    when(treatmentRepository.findById(2)).thenReturn(Optional.of(t2));

    // Act
    service.markTreatmentAsFinished(2);

    // Assert
    assertTrue(t2.getIsFinished());
    assertFalse(protocol.getIsFinished());
    verify(treatmentRepository).save(t2);
    verify(protocolRepository, never()).save(protocol);
  }

  @Test
  void shouldThrowWhenTreatmentNotFound() {
    when(treatmentRepository.findById(999)).thenReturn(Optional.empty());

    RuntimeException ex =
        assertThrows(
            RuntimeException.class,
            () -> {
              service.markTreatmentAsFinished(999);
            });

    assertEquals("Tratamiento no encontrado", ex.getMessage());
    verify(treatmentRepository, never()).save(any());
    verify(protocolRepository, never()).save(any());
  }
}

package com.gestion.application.service.protocol.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.repository.ProtocolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class DeleteProtocolImplTest {

  @Mock private ProtocolRepository protocolRepository;

  @InjectMocks private DeleteProtocolImpl service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldDeleteProtocolWhenExists() {
    Integer id = 1;

    when(protocolRepository.existsById(id)).thenReturn(true);

    service.deleteProtocolById(id);

    verify(protocolRepository).deleteById(id);
  }

  @Test
  void shouldThrowWhenProtocolDoesNotExist() {
    Integer id = 999;

    when(protocolRepository.existsById(id)).thenReturn(false);

    assertThrows(ProtocolNotFoundException.class, () -> service.deleteProtocolById(id));

    verify(protocolRepository, never()).deleteById(any());
  }
}

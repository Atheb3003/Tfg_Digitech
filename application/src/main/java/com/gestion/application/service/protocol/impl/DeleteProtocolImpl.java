package com.gestion.application.service.protocol.impl;

import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.repository.ProtocolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteProtocolImpl {

  private final ProtocolRepository protocolRepository;

  public void deleteProtocolById(Integer id) {
    if (!protocolRepository.existsById(id)) {
      throw new ProtocolNotFoundException(id);
    }

    protocolRepository.deleteById(id);
  }
}

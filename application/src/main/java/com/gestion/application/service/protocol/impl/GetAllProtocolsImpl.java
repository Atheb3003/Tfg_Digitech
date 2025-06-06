package com.gestion.application.service.protocol.impl;

import com.gestion.application.dto.ProtocolListResponse;
import com.gestion.application.model.Protocol;
import com.gestion.application.repository.ProtocolRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllProtocolsImpl {

  private final ProtocolRepository protocolRepository;

  public List<ProtocolListResponse> getAllProtocols() {
    List<Protocol> protocols = protocolRepository.findAll();

    return protocols.stream()
        .map(
            protocol -> {
              var contact = protocol.getContact();
              String fullName = contact.getName() + " " + contact.getSurname();
              String phone =
                  contact.getTelephoneNumber1() != null
                      ? contact.getTelephoneNumber1()
                      : contact.getTelephoneNumber2();

              return new ProtocolListResponse(
                  protocol.getIdProtocol(),
                  protocol.getDescription(),
                  protocol.getPrice(),
                  protocol.getIsFinished(),
                  fullName,
                  phone,
                  contact.getNif());
            })
        .toList();
  }
}

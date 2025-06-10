package com.gestion.application.service.protocol.impl;

import com.gestion.application.dto.ProtocolResponse;
import com.gestion.application.dto.ProtocolTreatmentResponse;
import com.gestion.application.exception.ProtocolNotFoundException;
import com.gestion.application.model.Protocol;
import com.gestion.application.repository.ProtocolRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetProtocolByContactIdImpl {

  private final ProtocolRepository protocolRepository;

  public List<ProtocolResponse> getProtocolByContactId(Integer contactId) {
    List<Protocol> protocols = protocolRepository.findAllByContact_IdContact(contactId);

    if (protocols.isEmpty()) {
      throw new ProtocolNotFoundException(contactId);
    }

    return protocols.stream()
        .map(
            protocol -> {
              List<ProtocolTreatmentResponse> treatmentResponses =
                  protocol.getTreatments().stream()
                      .map(
                          t ->
                              new ProtocolTreatmentResponse(
                                  t.getId(),
                                  t.getProduct().getIdProduct(),
                                  t.getProduct().getName(),
                                  BigDecimal.valueOf(t.getProduct().getPrice()),
                                  t.getIsPaid(),
                                  t.getIsFinished()))
                      .toList();

              ProtocolResponse response = new ProtocolResponse();
              response.setIdProtocol(protocol.getIdProtocol());
              response.setDescription(protocol.getDescription());
              response.setPrice(protocol.getPrice());
              response.setIsFinished(protocol.getIsFinished());
              response.setTreatments(treatmentResponses);

              return response;
            })
        .toList();
  }
}

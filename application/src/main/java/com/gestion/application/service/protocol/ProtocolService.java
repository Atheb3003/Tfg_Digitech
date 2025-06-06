package com.gestion.application.service.protocol;

import com.gestion.application.dto.*;
import com.gestion.application.model.Protocol;
import com.gestion.application.service.protocol.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProtocolService {

  private final CreateProtocolImpl createProtocol;
  private final UpdateProtocolTreatmentImpl updateTreatment;
  private final GetProtocolByContactIdImpl getProtocolByContactId;
  private final UpdateProtocolImpl updateProtocol;
  private final DeleteProtocolImpl deleteProtocol;
  private final GetAllProtocolsImpl getAllProtocols;

  public Protocol createProtocol(CreateProtocolRequest request) {
    return createProtocol.createProtocol(request);
  }

  public void finishTreatment(Integer treatmentId) {
    updateTreatment.markTreatmentAsFinished(treatmentId);
  }

  public ProtocolResponse getProtocolByContactId(Integer contactId) {
    return getProtocolByContactId.getProtocolByContactId(contactId);
  }

  public Protocol updateProtocol(UpdateProtocolRequest request) {
    return updateProtocol.updateProtocol(request);
  }

  public void deleteProtocol(Integer id) {
    deleteProtocol.deleteProtocolById(id);
  }

  public List<ProtocolListResponse> listAllProtocols() {
    return getAllProtocols.getAllProtocols();
  }
}

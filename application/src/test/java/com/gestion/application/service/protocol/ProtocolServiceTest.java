package com.gestion.application.service.protocol;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.model.Protocol;
import com.gestion.application.service.protocol.impl.*;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

public class ProtocolServiceTest {

  @Mock private CreateProtocolImpl createProtocol;

  @Mock private UpdateProtocolTreatmentImpl updateTreatment;

  @Mock private GetProtocolByContactIdImpl getProtocolByContactId;

  @Mock private UpdateProtocolImpl updateProtocol;

  @Mock private DeleteProtocolImpl deleteProtocol;

  @Mock private GetAllProtocolsImpl getAllProtocols;

  @Mock private CompleteProtocolIfAllTreatmentsFinishedImpl completeProtocol;

  @Mock private SearchProtocolsImpl searchProtocols;

  @InjectMocks private ProtocolService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateProtocolDelegates() {
    CreateProtocolRequest request = new CreateProtocolRequest();
    Protocol protocol = new Protocol();

    when(createProtocol.createProtocol(request)).thenReturn(protocol);

    Protocol result = service.createProtocol(request);

    assertEquals(protocol, result);
    verify(createProtocol).createProtocol(request);
  }

  @Test
  void testFinishTreatmentDelegates() {
    Integer treatmentId = 10;

    doNothing().when(updateTreatment).markTreatmentAsFinished(treatmentId);

    service.finishTreatment(treatmentId);

    verify(updateTreatment).markTreatmentAsFinished(treatmentId);
  }

  @Test
  void testGetProtocolByContactIdDelegates() {
    Integer contactId = 5;
    List<ProtocolResponse> responseList = List.of();

    when(getProtocolByContactId.getProtocolByContactId(contactId)).thenReturn(responseList);

    List<ProtocolResponse> result = service.getProtocolByContactId(contactId);

    assertEquals(responseList, result);
    verify(getProtocolByContactId).getProtocolByContactId(contactId);
  }

  @Test
  void testUpdateProtocolDelegates() {
    UpdateProtocolRequest request = new UpdateProtocolRequest();
    Protocol protocol = new Protocol();

    when(updateProtocol.updateProtocol(request)).thenReturn(protocol);

    Protocol result = service.updateProtocol(request);

    assertEquals(protocol, result);
    verify(updateProtocol).updateProtocol(request);
  }

  @Test
  void testDeleteProtocolDelegates() {
    Integer id = 7;

    doNothing().when(deleteProtocol).deleteProtocolById(id);

    service.deleteProtocol(id);

    verify(deleteProtocol).deleteProtocolById(id);
  }

  @Test
  void testListAllProtocolsDelegates() {
    List<ProtocolListResponse> list = List.of();

    when(getAllProtocols.getAllProtocols()).thenReturn(list);

    List<ProtocolListResponse> result = service.listAllProtocols();

    assertEquals(list, result);
    verify(getAllProtocols).getAllProtocols();
  }

  @Test
  void testCompleteProtocolIfAllTreatmentsAreFinishedDelegates() {
    Integer protocolId = 3;
    SuccessfulUpdateResponse response = new SuccessfulUpdateResponse("status", "message");

    when(completeProtocol.markAsFinishedIfApplicable(protocolId)).thenReturn(response);

    SuccessfulUpdateResponse result =
        service.completeProtocolIfAllTreatmentsAreFinished(protocolId);

    assertEquals(response, result);
    verify(completeProtocol).markAsFinishedIfApplicable(protocolId);
  }

  @Test
  void testSearchProtocolsDelegates() {
    String search = "test";
    Pageable pageable = Pageable.unpaged();
    Page<ProtocolSearchResponseDto> page = Page.empty();

    when(searchProtocols.searchProtocols(search, pageable)).thenReturn(page);

    Page<ProtocolSearchResponseDto> result = service.searchProtocols(search, pageable);

    assertEquals(page, result);
    verify(searchProtocols).searchProtocols(search, pageable);
  }
}

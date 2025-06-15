package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.model.Protocol;
import com.gestion.application.service.protocol.ProtocolService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class ProtocolControllerTest {

  @Mock private ProtocolService protocolService;

  @InjectMocks private ProtocolController controller;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCreateProtocol() {
    CreateProtocolRequest request = new CreateProtocolRequest(); // rellena si necesario
    Protocol protocol = new Protocol();
    protocol.setIdProtocol(1);

    when(protocolService.createProtocol(request)).thenReturn(protocol);

    var result = controller.createProtocol(request);

    assertEquals(201, result.getStatusCodeValue());
    assertEquals("created", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData());
  }

  @Test
  void testFinishTreatment() {
    doNothing().when(protocolService).finishTreatment(5);

    var result = controller.finishTreatment(5);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("updated", result.getBody().getStatus());
    assertTrue(result.getBody().getData().contains("finalizado"));
  }

  @Test
  void testGetProtocolByContact() {
    ProtocolResponse p = new ProtocolResponse();
    when(protocolService.getProtocolByContactId(1)).thenReturn(List.of(p));

    var result = controller.getProtocolByContact(1);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals(1, result.getBody().getData().size());
  }

  @Test
  void testUpdateProtocol() {
    UpdateProtocolRequest request = new UpdateProtocolRequest(); // rellena si necesario
    Protocol protocol = new Protocol();
    protocol.setIdProtocol(7);

    when(protocolService.updateProtocol(request)).thenReturn(protocol);

    var result = controller.updateProtocol(request);

    assertEquals(200, result.getStatusCodeValue());
    assertTrue(result.getBody().getData().contains("actualizado"));
  }

  @Test
  void testDeleteProtocol() {
    doNothing().when(protocolService).deleteProtocol(3);

    var result = controller.deleteProtocol(3);

    assertEquals(200, result.getStatusCodeValue());
    assertTrue(result.getBody().getData().contains("eliminado"));
  }

  @Test
  void testGetAllProtocols() {
    ProtocolListResponse p =
        new ProtocolListResponse(1, "Desc", new BigDecimal("100"), false, "Juan", "123", "XYZ");
    when(protocolService.listAllProtocols()).thenReturn(List.of(p));

    var result = controller.getAllProtocols();

    assertEquals(200, result.getStatusCodeValue());
    assertEquals(1, result.getBody().getData().size());
  }

  @Test
  void testCompleteProtocolIfEligible() {
    SuccessfulUpdateResponse res = new SuccessfulUpdateResponse("success", "completado");

    when(protocolService.completeProtocolIfAllTreatmentsAreFinished(2)).thenReturn(res);

    var result = controller.completeProtocolIfEligible(2);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("completado", result.getBody().getMessage());
  }

  @Test
  void testSearchProtocols_withSearchTerm() {
    Pageable pageable = PageRequest.of(0, 10);
    ProtocolSearchResponseDto dto = new ProtocolSearchResponseDto();
    dto.setProtocolId(1);
    dto.setFinished(true);

    Page<ProtocolSearchResponseDto> page = new PageImpl<>(List.of(dto));
    when(protocolService.searchProtocols("ana", pageable)).thenReturn(page);

    var result = controller.searchProtocols("ana", pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals(1, result.getBody().getData().getContent().size());
  }

  @Test
  void testSearchProtocols_withoutSearchTerm() {
    Pageable pageable = PageRequest.of(0, 10);
    ProtocolSearchResponseDto dto = new ProtocolSearchResponseDto();
    Page<ProtocolSearchResponseDto> page = new PageImpl<>(List.of(dto));
    when(protocolService.searchProtocols("", pageable)).thenReturn(page);

    var result = controller.searchProtocols(null, pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals(1, result.getBody().getData().getContent().size());
  }
}

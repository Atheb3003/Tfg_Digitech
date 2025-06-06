package com.gestion.application.controller;

import com.gestion.application.dto.*;
import com.gestion.application.model.Protocol;
import com.gestion.application.service.protocol.ProtocolService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/protocols")
public class ProtocolController {

  private final ProtocolService protocolService;

  @PostMapping
  public ResponseEntity<ApiResponse<Integer>> createProtocol(
      @RequestBody CreateProtocolRequest request) {
    Protocol created = protocolService.createProtocol(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>("created", created.getIdProtocol()));
  }

  @PutMapping("/treatments/{id}/finish")
  public ResponseEntity<ApiResponse<String>> finishTreatment(@PathVariable Integer id) {
    protocolService.finishTreatment(id);
    return ResponseEntity.ok(new ApiResponse<>("updated", "Tratamiento marcado como finalizado."));
  }

  @GetMapping("/contact/{contactId}")
  public ResponseEntity<ApiResponse<ProtocolResponse>> getProtocolByContact(
      @PathVariable Integer contactId) {
    ProtocolResponse response = protocolService.getProtocolByContactId(contactId);
    return ResponseEntity.ok(new ApiResponse<>("success", response));
  }

  @PutMapping
  public ResponseEntity<ApiResponse<String>> updateProtocol(
      @RequestBody UpdateProtocolRequest request) {
    Protocol updated = protocolService.updateProtocol(request);
    return ResponseEntity.ok(
        new ApiResponse<>(
            "updated",
            "Protocolo con ID " + updated.getIdProtocol() + " actualizado correctamente."));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> deleteProtocol(@PathVariable Integer id) {
    protocolService.deleteProtocol(id);
    return ResponseEntity.ok(
        new ApiResponse<>("deleted", "Protocolo con ID " + id + " eliminado correctamente."));
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProtocolListResponse>>> getAllProtocols() {
    List<ProtocolListResponse> protocols = protocolService.listAllProtocols();
    return ResponseEntity.ok(new ApiResponse<>("success", protocols));
  }
}

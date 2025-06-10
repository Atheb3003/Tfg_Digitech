package com.gestion.application.controller;

import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.service.protocoltreatment.ProtocolTreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/protocol-treatments")
@RequiredArgsConstructor
public class ProtocolTreatmentController {

  private final ProtocolTreatmentService protocolTreatmentService;

  /** PUT /protocol-treatments/{id}/paid */
  @PutMapping("/{id}/paid")
  public ResponseEntity<SuccessfulMarkAsPaidResponse> markAsPaid(@PathVariable Integer id) {
    SuccessfulMarkAsPaidResponse response = protocolTreatmentService.markAsPaid(id);
    return ResponseEntity.ok(response);
  }
}

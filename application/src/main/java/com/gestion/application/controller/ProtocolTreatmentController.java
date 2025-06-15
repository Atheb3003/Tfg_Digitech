package com.gestion.application.controller;

import com.gestion.application.dto.AddTreatmentToProtocolRequest;
import com.gestion.application.dto.SuccessfulMarkAsPaidResponse;
import com.gestion.application.service.protocoltreatment.ProtocolTreatmentService;
import com.gestion.application.service.protocoltreatment.impl.AddTreatmentToProtocolService;
import com.gestion.application.service.protocoltreatment.impl.MarkProtocolTreatmentAsFinishedImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/protocol-treatments")
@RequiredArgsConstructor
public class ProtocolTreatmentController {

  private final ProtocolTreatmentService protocolTreatmentService;
  private final AddTreatmentToProtocolService addTreatmentToProtocolService;
  private final MarkProtocolTreatmentAsFinishedImpl markProtocolTreatmentAsFinished;

  /** PUT /protocol-treatments/{id}/paid */
  @PutMapping("/{id}/paid")
  public ResponseEntity<SuccessfulMarkAsPaidResponse> markAsPaid(@PathVariable Integer id) {
    SuccessfulMarkAsPaidResponse response = protocolTreatmentService.markAsPaid(id);
    return ResponseEntity.ok(response);
  }

  /** POST /protocol-treatments/add */
  @PostMapping("/add")
  public ResponseEntity<String> addTreatment(@RequestBody AddTreatmentToProtocolRequest request) {
    addTreatmentToProtocolService.addTreatment(request);
    return ResponseEntity.ok("Tratamiento añadido al protocolo y precio actualizado.");
  }

  @PutMapping("/{id}/finished")
  public ResponseEntity<SuccessfulMarkAsPaidResponse> markAsFinished(@PathVariable Integer id) {
    SuccessfulMarkAsPaidResponse response = markProtocolTreatmentAsFinished.markAsFinished(id);
    return ResponseEntity.ok(response);
  }
}

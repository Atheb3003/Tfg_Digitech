package com.gestion.application.controller;

import com.gestion.application.dto.*;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.service.surgery.SurgeryReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surgery-reservations")
@RequiredArgsConstructor
public class SurgeryReservationController {

  private final SurgeryReservationService surgeryReservationService;

  @PostMapping
  public ResponseEntity<ApiResponse<Integer>> createReservation(
      @RequestBody CreateSurgeryReservationRequest request) {
    SurgeryReservation saved = surgeryReservationService.createReservation(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>("created", saved.getIdSurgeryReservation()));
  }

  @GetMapping
  public ResponseEntity<Page<SurgeryReservationResponse>> getAllReservations(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size);
    Page<SurgeryReservationResponse> result =
        surgeryReservationService.getAllReservations(pageable);

    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SurgeryReservationResponse> getReservationById(@PathVariable Integer id) {
    SurgeryReservationResponse response = surgeryReservationService.getReservationById(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/patient/{id}")
  public ResponseEntity<List<SurgeryReservationResponse>> getByPatientId(@PathVariable Integer id) {
    List<SurgeryReservationResponse> response =
        surgeryReservationService.getReservationsByPatientId(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/confirm-surgery")
  public ResponseEntity<Surgery> createSurgery(@RequestBody CreateSurgeryRequest request) {
    Surgery surgery = surgeryReservationService.createSurgery(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(surgery);
  }

  @PostMapping("/surgeries/standalone")
  public ResponseEntity<Surgery> createStandaloneSurgery(
      @RequestBody CreateSurgeryRequest request) {
    Surgery surgery = surgeryReservationService.createStandaloneSurgery(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(surgery);
  }

  @PutMapping("/surgery-reservations/{id}")
  public ResponseEntity<ApiResponse<SurgeryReservationResponse>> updateReservation(
      @PathVariable Integer id, @RequestBody UpdateSurgeryReservationRequest request) {

    SurgeryReservationResponse updated = surgeryReservationService.updateReservation(id, request);
    ApiResponse<SurgeryReservationResponse> response = new ApiResponse<>("updated", updated);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/hidden")
  public ResponseEntity<ApiResponse<Page<SurgeryReservationResponse>>> getHiddenReservations(
      Pageable pageable) {
    Page<SurgeryReservationResponse> data = surgeryReservationService.getAllHidden(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", data));
  }

  @PatchMapping("/{id}/hide")
  public ResponseEntity<ApiResponse<String>> hideReservation(@PathVariable Integer id) {
    surgeryReservationService.hideReservation(id);
    return ResponseEntity.ok(
        new ApiResponse<>("hidden", "Reserva " + id + " oculta correctamente."));
  }
}

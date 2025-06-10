package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ConsultationRequest;
import com.gestion.application.dto.ConsultationResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.consultation.ConsultationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultations")
@RequiredArgsConstructor
public class ConsultationController {

  private final ConsultationService service;

  /** POST /consultations */
  @PostMapping
  public ResponseEntity<ApiResponse<ConsultationResponse>> create(
      @RequestBody ConsultationRequest req) {
    var dto = service.create(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", dto));
  }

  /** GET /consultations */
  @GetMapping
  public ResponseEntity<ApiResponse<List<ConsultationResponse>>> getAll() {
    return ResponseEntity.ok(new ApiResponse<>("success", service.getAll()));
  }

  /** GET /consultations/visible */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<List<ConsultationResponse>>> getVisible() {
    return ResponseEntity.ok(new ApiResponse<>("success", service.getVisible()));
  }

  //  /** GET /consultations/patient/{id} */
  //  @GetMapping("/patient/{id}")
  //  public ResponseEntity<ApiResponse<List<ConsultationResponse>>> getByPatient(
  //      @PathVariable String id) {
  //
  //    Integer patientId;
  //    try {
  //      patientId = Integer.valueOf(id);
  //    } catch (NumberFormatException ex) {
  //      throw new IdArgumentTypeException(id);
  //    }
  //
  //    List<ConsultationResponse> list = service.getByPatient(patientId);
  //    return ResponseEntity.ok(new ApiResponse<>("success", list));
  //  }

  /** GET /consultations/contact/{id} */
  @GetMapping("/contact/{id}")
  public ResponseEntity<ApiResponse<List<ConsultationResponse>>> getByContact(
      @PathVariable String id) {

    Integer contactId;
    try {
      contactId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    List<ConsultationResponse> list = service.getByContact(contactId);
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** DELETE /consultations/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> delete(@PathVariable String id) {

    Integer cId;
    try {
      cId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    service.delete(cId);
    return ResponseEntity.ok(
        new SuccessfulDeleteResponse(
            "deleted", "Consulta con ID " + cId + " eliminada correctamente."));
  }

  /** PUT /consultations/visible/{id} */
  @PatchMapping("/visible/{id}")
  public ResponseEntity<ApiResponse<ConsultationResponse>> makeVisible(@PathVariable String id) {
    Integer cId;
    try {
      cId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    return ResponseEntity.ok(new ApiResponse<>("success", service.makeVisible(cId)));
  }

  /** PUT /consultations/invisible/{id} */
  @PatchMapping("/invisible/{id}")
  public ResponseEntity<ApiResponse<ConsultationResponse>> makeInvisible(@PathVariable String id) {
    Integer cId;
    try {
      cId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    return ResponseEntity.ok(new ApiResponse<>("success", service.makeInvisible(cId)));
  }

  /** GET /consultations/type/{id} */
  @GetMapping("/type/{id}")
  public ResponseEntity<ApiResponse<List<ConsultationResponse>>> getByType(
      @PathVariable String id) {

    Integer typeId;
    try {
      typeId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    List<ConsultationResponse> list = service.getByType(typeId);
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** PUT /consultations/{id} */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ConsultationResponse>> update(
      @PathVariable String id, @RequestBody ConsultationRequest req) {

    Integer cId;
    try {
      cId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    ConsultationResponse dto = service.updateConsultation(cId, req);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }
}

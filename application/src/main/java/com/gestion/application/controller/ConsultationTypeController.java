package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ConsultationTypeRequest;
import com.gestion.application.dto.ConsultationTypeResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.consultationtype.ConsultationTypeService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation-types")
@RequiredArgsConstructor
public class ConsultationTypeController {

  private final ConsultationTypeService service;

  /** POST /consultation-types */
  @PostMapping
  public ResponseEntity<ApiResponse<ConsultationTypeResponse>> create(
      @RequestBody ConsultationTypeRequest req) {
    var dto = service.createConsultationType(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", dto));
  }

  /** GET /consultation-types */
  @GetMapping
  public ResponseEntity<Map<String, Object>> getAll() {
    List<ConsultationTypeResponse> list = service.getAllConsultationTypes();
    return ResponseEntity.ok(Map.of("status", "success", "data", list));
  }

  /** GET /consultation-types/{id} */
  @GetMapping("/{id}")
  public ResponseEntity<Map<String, Object>> getById(@PathVariable String id) {
    Integer tid;
    try {
      tid = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    ConsultationTypeResponse dto = service.getConsultationTypeById(tid);
    return ResponseEntity.ok(Map.of("status", "success", "data", dto));
  }

  /** PUT /consultation-types/{id} */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ConsultationTypeResponse>> update(
      @PathVariable String id, @RequestBody ConsultationTypeRequest req) {

    Integer tid;
    try {
      tid = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    var dto = service.updateConsultationType(tid, req);
    return ResponseEntity.ok(new ApiResponse<>("updated", dto));
  }

  /** DELETE /consultation-types/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> delete(@PathVariable String id) {

    Integer tid;
    try {
      tid = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    service.deleteConsultationType(tid);
    return ResponseEntity.ok(
        new SuccessfulDeleteResponse(
            "deleted", "Tipo de consulta con ID " + tid + " eliminado correctamente."));
  }
}

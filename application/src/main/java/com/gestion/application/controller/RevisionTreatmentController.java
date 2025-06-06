package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.RevisionTreatmentRequest;
import com.gestion.application.dto.RevisionTreatmentResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.revisiontreatment.RevisionTreatmentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/revision-treatments")
@RequiredArgsConstructor
public class RevisionTreatmentController {

  private final RevisionTreatmentService svc;

  /** POST /revision-treatments */
  @PostMapping
  public ResponseEntity<ApiResponse<RevisionTreatmentResponse>> create(
      @RequestBody RevisionTreatmentRequest req) {
    var dto = svc.create(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", dto));
  }

  /** GET /revision-treatments */
  @GetMapping
  public ResponseEntity<ApiResponse<List<RevisionTreatmentResponse>>> getAll() {
    return ResponseEntity.ok(new ApiResponse<>("success", svc.getAll()));
  }

  /** GET /revision-treatments/revision/{id} */
  @GetMapping("/revision/{id}")
  public ResponseEntity<ApiResponse<List<RevisionTreatmentResponse>>> getByRevision(
      @PathVariable String id) {
    Integer revId;
    try {
      revId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    return ResponseEntity.ok(new ApiResponse<>("success", svc.getByRevision(revId)));
  }

  /** DELETE /revision-treatments/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> delete(@PathVariable String id) {
    Integer tid;
    try {
      tid = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    svc.delete(tid);
    return ResponseEntity.ok(
        new SuccessfulDeleteResponse(
            "deleted", "Tratamiento de revisión con ID " + tid + " eliminado correctamente."));
  }
}

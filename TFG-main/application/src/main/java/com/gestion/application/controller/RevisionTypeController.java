package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.RevisionTypeRequest;
import com.gestion.application.dto.RevisionTypeResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.revisiontype.RevisionTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/revision-types")
@RequiredArgsConstructor
public class RevisionTypeController {

  private final RevisionTypeService svc;

  /** POST /revision-types */
  @PostMapping
  public ResponseEntity<ApiResponse<RevisionTypeResponse>> create(
      @RequestBody RevisionTypeRequest req) {
    var dto = svc.create(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", dto));
  }

  /** GET /revision-types */
  @GetMapping
  public ResponseEntity<ApiResponse<List<RevisionTypeResponse>>> getAll() {
    return ResponseEntity.ok(new ApiResponse<>("success", svc.getAll()));
  }

  /** GET /revision-types/{id} */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<RevisionTypeResponse>> getById(@PathVariable String id) {
    Integer i;
    try {
      i = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    return ResponseEntity.ok(new ApiResponse<>("success", svc.getById(i)));
  }

  /** PUT /revision-types/{id} */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<RevisionTypeResponse>> update(
      @PathVariable String id, @RequestBody RevisionTypeRequest req) {
    Integer i;
    try {
      i = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    return ResponseEntity.ok(new ApiResponse<>("updated", svc.update(i, req)));
  }

  /** DELETE /revision-types/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> delete(@PathVariable String id) {
    Integer i;
    try {
      i = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    svc.delete(i);
    return ResponseEntity.ok(
        new SuccessfulDeleteResponse(
            "deleted", "Tipo de revisión con ID " + i + " eliminado correctamente."));
  }
}

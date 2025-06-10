package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.CreateRevisionRequest;
import com.gestion.application.dto.RevisionResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.service.revision.RevisionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para “Revision”. Todas las respuestas exitosas usan ApiResponse o
 * SuccessfulDeleteResponse para formatear JSON.
 */
@RestController
@RequestMapping("/revisions")
@RequiredArgsConstructor
public class RevisionController {

  private final RevisionService revisionService;

  /** POST /revisions Crea una nueva revisión. Responde con status = "created". */
  @PostMapping
  public ResponseEntity<ApiResponse<RevisionResponse>> createRevision(
      @RequestBody CreateRevisionRequest request) {

    RevisionResponse created = revisionService.createRevision(request);
    ApiResponse<RevisionResponse> apiResponse = new ApiResponse<>("created", created);
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  /** PUT /revisions/{id} Actualiza una revisión existente. Responde con status = "updated". */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<RevisionResponse>> updateRevision(
      @PathVariable Integer id, @RequestBody CreateRevisionRequest request) {

    RevisionResponse updated = revisionService.updateRevision(id, request);
    ApiResponse<RevisionResponse> apiResponse = new ApiResponse<>("updated", updated);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * DELETE /revisions/{id} Elimina físicamente (real) la revisión. Responde con status = "deleted".
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> deleteRevision(@PathVariable Integer id) {
    revisionService.deleteRevision(id);
    SuccessfulDeleteResponse response =
        new SuccessfulDeleteResponse(
            "deleted", "Revisión con ID " + id + " eliminada correctamente.");
    return ResponseEntity.ok(response);
  }

  /** GET /revisions/{id} Obtiene una revisión por su ID. Responde con status = "success". */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<RevisionResponse>> getRevisionById(@PathVariable Integer id) {
    RevisionResponse dto = revisionService.getRevisionById(id);
    ApiResponse<RevisionResponse> apiResponse = new ApiResponse<>("success", dto);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * GET /revisions Lista todas las revisiones (visibles e invisibles). Responde con status =
   * "success".
   */
  @GetMapping
  public ResponseEntity<ApiResponse<List<RevisionResponse>>> getAllRevisions() {
    List<RevisionResponse> lista = revisionService.getAllRevisions();
    ApiResponse<List<RevisionResponse>> apiResponse = new ApiResponse<>("success", lista);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * GET /revisions/visible Lista solo las revisiones con isVisible = true. Responde con status =
   * "success".
   */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<List<RevisionResponse>>> getVisibleRevisions() {
    List<RevisionResponse> lista = revisionService.getVisibleRevisions();
    ApiResponse<List<RevisionResponse>> apiResponse = new ApiResponse<>("success", lista);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * GET /revisions/contact/{contactId} Lista revisiones por ID de contacto. Responde con status =
   * "success".
   */
  @GetMapping("/contact/{contactId}")
  public ResponseEntity<ApiResponse<List<RevisionResponse>>> getRevisionsByContact(
      @PathVariable Integer contactId) {
    List<RevisionResponse> lista = revisionService.getRevisionsByContact(contactId);
    ApiResponse<List<RevisionResponse>> apiResponse = new ApiResponse<>("success", lista);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * GET /revisions/type/{typeId} Lista revisiones por ID de tipo de revisión. Responde con status =
   * "success".
   */
  @GetMapping("/type/{typeId}")
  public ResponseEntity<ApiResponse<List<RevisionResponse>>> getRevisionsByType(
      @PathVariable Integer typeId) {
    List<RevisionResponse> lista = revisionService.getRevisionsByType(typeId);
    ApiResponse<List<RevisionResponse>> apiResponse = new ApiResponse<>("success", lista);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * PATCH /revisions/{id}/visible Marca una revisión como visible. Responde con status = "updated".
   */
  @PatchMapping("/{id}/visible")
  public ResponseEntity<ApiResponse<RevisionResponse>> setRevisionVisible(
      @PathVariable Integer id) {
    RevisionResponse nowVisible = revisionService.makeRevisionVisible(id);
    ApiResponse<RevisionResponse> apiResponse = new ApiResponse<>("updated", nowVisible);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * PATCH /revisions/{id}/invisible Marca una revisión como invisible. Responde con status =
   * "updated".
   */
  @PatchMapping("/{id}/invisible")
  public ResponseEntity<ApiResponse<RevisionResponse>> setRevisionInvisible(
      @PathVariable Integer id) {
    RevisionResponse nowInvisible = revisionService.makeRevisionInvisible(id);
    ApiResponse<RevisionResponse> apiResponse = new ApiResponse<>("updated", nowInvisible);
    return ResponseEntity.ok(apiResponse);
  }
}

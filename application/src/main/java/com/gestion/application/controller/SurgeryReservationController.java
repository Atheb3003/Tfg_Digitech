package com.gestion.application.controller;

import com.gestion.application.dto.*;
import com.gestion.application.model.Surgery;
import com.gestion.application.model.SurgeryReservation;
import com.gestion.application.service.surgery.SurgeryReservationService;
import com.gestion.application.service.surgery.impl.GetUnconfirmedVisibleSurgeryReservationsImpl;
import com.gestion.application.service.surgery.impl.SearchSurgeryReservationsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surgery-reservations")
@RequiredArgsConstructor
public class SurgeryReservationController {

  private final SurgeryReservationService service;
  private final GetUnconfirmedVisibleSurgeryReservationsImpl getUnconfirmedVisibleService;
  private final SearchSurgeryReservationsImpl searchService;


  /** POST /surgery-reservations */
  @PostMapping
  public ResponseEntity<ApiResponse<SurgeryReservationResponse>> createReservation(
          @RequestBody CreateSurgeryReservationRequest request
  ) {
    // 1) Creas la reserva y la guardas
    SurgeryReservation saved = service.createReservation(request);
    // 2) Obtienes el DTO a partir del ID generado
    SurgeryReservationResponse dto =
            service.getReservationById(saved.getIdSurgeryReservation());
    // 3) Devuelves el ApiResponse con el DTO
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse<>("created", dto));
  }


  /** GET /surgery-reservations (paginado) */
  @GetMapping
  public ResponseEntity<ApiResponse<Page<SurgeryReservationResponse>>> getAllReservations(
          @PageableDefault(sort = "estimatedDate", direction = Sort.Direction.DESC)
          Pageable pageable
  ) {
    Page<SurgeryReservationResponse> page = service.getAllReservations(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  /** GET /surgery-reservations/{id} */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<SurgeryReservationResponse>> getReservationById(
          @PathVariable Integer id
  ) {
    SurgeryReservationResponse dto = service.getReservationById(id);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }

  /** GET /surgery-reservations/patient/{id} (sin paginar) */
  @GetMapping("/patient/{id}")
  public ResponseEntity<ApiResponse<List<SurgeryReservationResponse>>> getByPatientId(
          @PathVariable Integer id
  ) {
    List<SurgeryReservationResponse> list = service.getReservationsByPatientId(id);
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** POST /surgery-reservations/confirm-surgery */
  @PostMapping("/confirm-surgery")
  public ResponseEntity<ApiResponse<SurgeryResponse>> createSurgery(
          @RequestBody CreateSurgeryRequest request
  ) {
    Surgery s = service.createSurgery(request);
    SurgeryResponse dto = new SurgeryResponse(
            s.getIdSurgery(),
            s.getSurgeryReservation().getIdSurgeryReservation(),
            s.getDate(),
            s.getObservations(),
            s.getIsVisible()
    );
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse<>("created", dto));
  }

  /** POST /surgery-reservations/surgeries/standalone */
  @PostMapping("/surgeries/standalone")
  public ResponseEntity<ApiResponse<SurgeryResponse>> createStandaloneSurgery(
          @RequestBody CreateSurgeryRequest request
  ) {
    Surgery s = service.createStandaloneSurgery(request);
    SurgeryResponse dto = new SurgeryResponse(
            s.getIdSurgery(),
            null,
            s.getDate(),
            s.getObservations(),
            s.getIsVisible()
    );
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ApiResponse<>("created", dto));
  }

  /** PUT /surgery-reservations/{id} */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<SurgeryReservationResponse>> updateReservation(
          @PathVariable Integer id,
          @RequestBody UpdateSurgeryReservationRequest request
  ) {
    SurgeryReservationResponse updated = service.updateReservation(id, request);
    return ResponseEntity.ok(new ApiResponse<>("updated", updated));
  }

  /** GET /surgery-reservations/hidden (paginado) */
  @GetMapping("/hidden")
  public ResponseEntity<ApiResponse<Page<SurgeryReservationResponse>>> getHiddenReservations(
          @PageableDefault(sort = "estimatedDate", direction = Sort.Direction.DESC)
          Pageable pageable
  ) {
    Page<SurgeryReservationResponse> page = service.getAllHidden(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  /** PUT /surgery-reservations/{id}/hide */
  @PutMapping("/{id}/hide")
  public ResponseEntity<ApiResponse<HideReservationResponse>> hideReservation(
          @PathVariable Integer id
  ) {
    service.hideReservation(id);
    HideReservationResponse dto = new HideReservationResponse(id, false);
    return ResponseEntity.ok(new ApiResponse<>("hidden", dto));
  }

  /**
   * GET /surgery-reservations/visible
   * Lista paginada de todas las reservas con isVisible=true
   */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<Page<SurgeryReservationResponse>>> getVisibleReservations(
          @PageableDefault(sort = "idSurgeryReservation", direction = Sort.Direction.DESC)
          Pageable pageable
  ) {
    Page<SurgeryReservationResponse> page = service.getOnlyVisibleReservations(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  /**
   * PATCH /surgery-reservations/{id}/add-pay
   * Añade un pago parcial a la reserva indicada.
   */
  @PutMapping("/{id}/add-pay")
  public ResponseEntity<ApiResponse<SurgeryReservationResponse>> addPayment(
          @PathVariable Integer id,
          @RequestBody AddPaymentRequest request
  ) {
    // 1) Aplica el pago parcial
    SurgeryReservation updatedEntity = service.addPayment(id, request.getAmount());

    // 2) Obtiene el DTO completo actualizado
    SurgeryReservationResponse dto =
            service.getReservationById(updatedEntity.getIdSurgeryReservation());

    return ResponseEntity.ok(new ApiResponse<>("updated", dto));
  }

  @GetMapping("/visible/unconfirmed")
  public ResponseEntity<ApiResponse<Page<SurgeryReservationResponse>>>
  getVisibleUnconfirmedReservations(
          @PageableDefault(
                  sort = "estimatedDate",
                  direction = Sort.Direction.DESC
          ) Pageable pageable)
  {
    Page<SurgeryReservationResponse> page =
            getUnconfirmedVisibleService.getUnconfirmedVisible(pageable);

    return ResponseEntity
            .ok(new ApiResponse<>("success", page));
  }

  @GetMapping("visible/search/{search}")
  public ResponseEntity<ApiResponse<Page<SurgeryReservationResponse>>> searchReservations(
          @PathVariable String search,
          @PageableDefault(sort = "estimatedDate", direction = Sort.Direction.DESC)
          Pageable pageable
  ) {
    Page<SurgeryReservationResponse> page =
            searchService.searchVisibleReservations(search, pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }


}

package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.transactiondetail.TransactionDetailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction-details")
@RequiredArgsConstructor
public class TransactionDetailController {

  private final TransactionDetailService service;

  /** POST /transaction-details */
  @PostMapping
  public ResponseEntity<ApiResponse<TransactionDetailResponse>> createDetail(
      @RequestBody TransactionDetailRequest req) {
    var dto = service.createDetail(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", dto));
  }

  /** GET /transaction-details */
  @GetMapping
  public ResponseEntity<ApiResponse<List<TransactionDetailResponse>>> getAll() {
    var list = service.getAllDetails();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /transaction-details/visible */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<List<TransactionDetailResponse>>> getVisible() {
    var list = service.getVisibleDetails();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /transaction-details/transaction/{id} */
  @GetMapping("/transaction/{id}")
  public ResponseEntity<ApiResponse<List<TransactionDetailResponse>>> getByTransaction(
      @PathVariable String id) {
    Integer txId;
    try {
      txId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    var list = service.getDetailsByTransaction(txId);
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** DELETE /transaction-details/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> deleteDetail(@PathVariable String id) {
    Integer dId;
    try {
      dId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    service.deleteDetail(dId);
    return ResponseEntity.ok(
        new SuccessfulDeleteResponse(
            "deleted", "Detalle de transacción con ID " + dId + " eliminado correctamente."));
  }

  /** PUT /transaction-details/visible/{id} */
  @PatchMapping("/visible/{id}")
  public ResponseEntity<ApiResponse<TransactionDetailResponse>> setVisible(
      @PathVariable String id) {
    Integer dId;
    try {
      dId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    var dto = service.makeDetailVisible(dId);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }

  /** PUT /transaction-details/invisible/{id} */
  @PatchMapping("/invisible/{id}")
  public ResponseEntity<ApiResponse<TransactionDetailResponse>> setInvisible(
      @PathVariable String id) {
    Integer dId;
    try {
      dId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }
    var dto = service.makeDetailInvisible(dId);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }
}

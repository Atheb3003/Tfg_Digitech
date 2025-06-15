package com.gestion.application.controller;

import com.gestion.application.dto.*;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.transactiondetail.TransactionDetailService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

  /** GET /transaction-details/sold-items */
  @GetMapping("/sold-items")
  public ResponseEntity<ApiResponse<Page<SoldItemResponse>>> getVisibleSoldItems(
      Pageable pageable) {
    var page = service.getVisibleSoldItems(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  /** GET /transaction-details/sold-items/search */
  @GetMapping("/sold-items/search/{term}")
  public ResponseEntity<ApiResponse<Page<SoldItemResponse>>> searchVisibleSoldItems(
      @PathVariable String term, Pageable pageable) {
    var page = service.searchVisibleSoldItems(term, pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  // Obtiene los ingresos de un rango de tiempo
  @GetMapping("/total-income")
  public TotalIncomeResponse getTotalIncome(
      @RequestParam(value = "startDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate startDate,
      @RequestParam(value = "endDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate endDate) {
    return service.getTotalIncome(startDate, endDate);
  }

  @GetMapping("/income-by-payment-method")
  public ResponseEntity<IncomeByPaymentMethodResponse> getIncomeByPaymentMethod(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate endDate) {
    return ResponseEntity.ok(service.getIncomeByPaymentMethod(startDate, endDate));
  }

  @GetMapping("/product-breakdown")
  public ResponseEntity<ProductBreakdownResponse> getProductBreakdown(
      @RequestParam(required = false) String startDate,
      @RequestParam(required = false) String endDate) {
    // Parsear fechas si llegan, si no son nulas
    LocalDate start = startDate != null ? LocalDate.parse(startDate) : null;
    LocalDate end = endDate != null ? LocalDate.parse(endDate) : null;
    return ResponseEntity.ok(service.getProductBreakdown(start, end));
  }

  @GetMapping("/type-breakdown")
  public ResponseEntity<TypeBreakdownResponse> getTypeBreakdown(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate endDate) {
    return ResponseEntity.ok(service.getTypeBreakdown(startDate, endDate));
  }

  /** GET /transaction-details/detail-info/{id} */
  @GetMapping("/detail-info/{id}")
  public ResponseEntity<ApiResponse<TransactionDetailInfoResponse>> getTransactionDetailInfo(
      @PathVariable Integer id) {
    var resp = service.getTransactionDetailInfo(id);
    return ResponseEntity.ok(new ApiResponse<>("success", resp));
  }
}

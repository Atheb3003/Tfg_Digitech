// ---------------------------------------------------------------------
// TransactionController.java
// ---------------------------------------------------------------------
package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.dto.TransactionSummaryDTO;
import com.gestion.application.service.transaction.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;               // <--- importar
import org.springframework.data.domain.Pageable;           // <--- importar
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;        // <--- importar
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService service;

  /** POST /transactions */
  @PostMapping
  public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(
          @RequestBody TransactionRequest req
  ) {
    TransactionResponse created = service.createTransaction(req);
    return ResponseEntity.ok(new ApiResponse<>("success", created));
  }

  /** GET /transactions/all (sin paginar) */
  @GetMapping("/all")
  public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactions() {
    List<TransactionResponse> list = service.getAllTransactions();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /transactions (paginado de resúmenes) */
  @GetMapping
  public ResponseEntity<ApiResponse<Page<TransactionSummaryDTO>>> getTransactionSummaries(
          @PageableDefault(sort = "transactionDate", direction = Sort.Direction.DESC)
          Pageable pageable
  ) {
    Page<TransactionSummaryDTO> page = service.getTransactionSummaries(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  /**
   * MODIFICADO: GET /transactions/visible (ahora paginado)
   * Ejemplo: /transactions/visible?page=0&size=20&sort=transactionDate,desc
   */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<Page<TransactionResponse>>> getVisibleTransactions(
          @PageableDefault(sort = "transactionDate", direction = Sort.Direction.DESC)
          Pageable pageable
  ) {
    Page<TransactionResponse> page = service.getVisibleTransactions(pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }

  /** GET /transactions/patient/{id} */
  @GetMapping("/patient/{id}")
  public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactionsByPatient(
          @PathVariable("id") Integer patientId
  ) {
    List<TransactionResponse> list = service.getTransactionsByPatient(patientId);
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** DELETE /transactions/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> deleteTransaction(
          @PathVariable("id") Integer id
  ) {
    service.deleteTransaction(id);
    return ResponseEntity.ok(new ApiResponse<>("success", "Transacción eliminada"));
  }

  /** PUT /transactions/visible/{id} */
  @PutMapping("/visible/{id}")
  public ResponseEntity<ApiResponse<TransactionResponse>> makeTransactionVisible(
          @PathVariable("id") Integer id
  ) {
    TransactionResponse resp = service.makeTransactionVisible(id);
    return ResponseEntity.ok(new ApiResponse<>("success", resp));
  }

  /** PUT /transactions/invisible/{id} */
  @PutMapping("/invisible/{id}")
  public ResponseEntity<ApiResponse<TransactionResponse>> makeTransactionInvisible(
          @PathVariable("id") Integer id
  ) {
    TransactionResponse resp = service.makeTransactionInvisible(id);
    return ResponseEntity.ok(new ApiResponse<>("success", resp));
  }

  /** GET /transactions/contact/{id} (paginado) */
  @GetMapping("/contact/{id}")
  public ResponseEntity<ApiResponse<Page<TransactionSummaryDTO>>> getTransactionsByContact(
          @PathVariable("id") Integer contactId,
          @PageableDefault(sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable
  ) {
    Page<TransactionSummaryDTO> page =
            service.getTransactionSummariesByContact(contactId, pageable);
    return ResponseEntity.ok(new ApiResponse<>("success", page));
  }
}

package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.transaction.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
      @RequestBody TransactionRequest req) {
    TransactionResponse resp = service.createTransaction(req);
    return ResponseEntity.status(201).body(new ApiResponse<>("created", resp));
  }

  /** GET /transactions */
  @GetMapping
  public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactions() {
    List<TransactionResponse> list = service.getAllTransactions();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /transactions/visible */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<List<TransactionResponse>>> getVisibleTransactions() {
    List<TransactionResponse> list = service.getVisibleTransactions();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /transactions/patient/{id} */
  @GetMapping("/patient/{id}")
  public ResponseEntity<ApiResponse<List<TransactionResponse>>> getByPatient(
      @PathVariable String id) {

    // 1) Validar formato de ID
    Integer patientId;
    try {
      patientId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      // Reutiliza la excepción genérica de ID mal formado
      throw new IdArgumentTypeException(id);
    }

    // 2) Delegate al servicio: éste usa PatientNotFoundException si no halla el paciente
    List<TransactionResponse> list = service.getTransactionsByPatient(patientId);

    // 3) Responder 200 con la lista (puede estar vacía si no hay transacciones)
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** DELETE /transactions/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> deleteTransaction(@PathVariable String id) {

    Integer txId;
    try {
      txId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    service.deleteTransaction(txId);

    SuccessfulDeleteResponse resp =
        new SuccessfulDeleteResponse(
            "deleted", "Transacción con ID " + txId + " eliminada correctamente.");
    return ResponseEntity.ok(resp);
  }

  /**
   * PUT /transactions/visible/{id} - 400 Bad Request si id no numérico → IdArgumentTypeException -
   * 404 Not Found si no existe → TransactionNotFoundException - 409 Conflict si ya estaba visible →
   * TransactionAlreadyVisibleException - 500 Internal Server Error resto
   *
   * <p>Responde: { "status":"success", "data": TransactionResponse }
   */
  @PatchMapping("/visible/{id}")
  public ResponseEntity<ApiResponse<TransactionResponse>> setVisible(@PathVariable String id) {

    Integer txId;
    try {
      txId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    TransactionResponse dto = service.makeTransactionVisible(txId);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }

  /**
   * PUT /transactions/invisible/{id} - 400 Bad Request si id no numérico → IdArgumentTypeException
   * - 404 Not Found si no existe → TransactionNotFoundException - 409 Conflict si ya estaba
   * invisible → TransactionAlreadyInvisibleException - 500 Internal Server Error resto
   *
   * <p>Responde: { "status":"success", "data": TransactionResponse }
   */
  @PatchMapping("/invisible/{id}")
  public ResponseEntity<ApiResponse<TransactionResponse>> setInvisible(@PathVariable String id) {

    Integer txId;
    try {
      txId = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    TransactionResponse dto = service.makeTransactionInvisible(txId);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }
}

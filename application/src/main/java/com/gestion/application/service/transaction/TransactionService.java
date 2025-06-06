package com.gestion.application.service.transaction;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.service.transaction.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final CreateTransactionImpl createImpl;
  private final GetAllTransactionsImpl listImpl;
  private final GetVisibleTransactionsImpl visibleImpl;
  private final GetTransactionsByPatientImpl byPatientImpl;
  private final DeleteTransactionImpl deleteImpl;
  private final MakeTransactionVisibleImpl makeVisibleImpl;
  private final MakeTransactionInvisibleImpl makeInvisibleImpl;
  private final TransactionMapper mapper;

  /** POST /transactions */
  public TransactionResponse createTransaction(TransactionRequest req) {
    return createImpl.create(req);
  }

  /** GET /transactions */
  public List<TransactionResponse> getAllTransactions() {
    return listImpl.getAllTransactions();
  }

  /** GET /transactions/visible */
  public List<TransactionResponse> getVisibleTransactions() {
    return visibleImpl.getVisibleTransactions();
  }

  /** GET /transactions/patient/{id} */
  public List<TransactionResponse> getTransactionsByPatient(Integer patientId) {
    return byPatientImpl.getTransactionsByPatient(patientId);
  }

  /** DELETE /transactions/{id} */
  public void deleteTransaction(Integer id) {
    deleteImpl.deleteTransaction(id);
  }

  /** PUT /transactions/visible/{id} */
  public TransactionResponse makeTransactionVisible(Integer id) {
    return mapper.toResponse(makeVisibleImpl.makeVisible(id));
  }

  /** PUT /transactions/invisible/{id} */
  public TransactionResponse makeTransactionInvisible(Integer id) {
    return mapper.toResponse(makeInvisibleImpl.makeInvisible(id));
  }
}

// ---------------------------------------------------------------------
// TransactionService.java
// ---------------------------------------------------------------------
package com.gestion.application.service.transaction;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.dto.TransactionSummaryDTO;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.TransactionRepository;
import com.gestion.application.service.transaction.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
  private final GetTransactionSummariesImpl getSummariesImpl;
  private final TransactionRepository transactionRepository;
  private final ContactRepository contactRepository;
  private final SearchTransactionsImpl searchImpl;

  /** POST /transactions */
  public TransactionResponse createTransaction(TransactionRequest req) {
    return createImpl.create(req);
  }

  /** GET /transactions/all (sin paginar) */
  public List<TransactionResponse> getAllTransactions() {
    return listImpl.getAllTransactions();
  }

  public Page<TransactionResponse> getVisibleTransactions(Pageable pageable) {
    return visibleImpl.getVisibleTransactions(pageable);
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

  /** GET /transactions (paginado) */
  public Page<TransactionSummaryDTO> getTransactionSummaries(Pageable pageable) {
    return getSummariesImpl.execute(pageable);
  }

  /** GET /transactions/contact/{id} (paginado) */
  public Page<TransactionSummaryDTO> getTransactionSummariesByContact(
      Integer contactId, Pageable pageable) {
    contactRepository
        .findById(contactId)
        .orElseThrow(() -> new ContactNotFoundException(contactId));
    return transactionRepository.findTransactionSummariesByContactId(contactId, pageable);
  }

  /**
   * GET /transactions/search/{search} Delegamos en SearchTransactionsImpl para mantener la lógica
   * encapsulada.
   */
  public Page<TransactionResponse> searchTransactions(String search, Pageable pageable) {
    return transactionRepository.searchVisibleTransactions(search, pageable);
  }
}

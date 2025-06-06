package com.gestion.application.service.transaction;

import com.gestion.application.dto.TransactionRequest;
import com.gestion.application.dto.TransactionResponse;
import com.gestion.application.dto.TransactionSummaryDTO;
import com.gestion.application.exception.ContactNotFoundException;
import com.gestion.application.mapper.TransactionMapper;
import com.gestion.application.repository.ContactRepository;
import com.gestion.application.repository.TransactionRepository;
import com.gestion.application.service.transaction.impl.CreateTransactionImpl;
import com.gestion.application.service.transaction.impl.DeleteTransactionImpl;
import com.gestion.application.service.transaction.impl.GetAllTransactionsImpl;
import com.gestion.application.service.transaction.impl.GetTransactionsByPatientImpl;
import com.gestion.application.service.transaction.impl.GetVisibleTransactionsImpl;
import com.gestion.application.service.transaction.impl.MakeTransactionInvisibleImpl;
import com.gestion.application.service.transaction.impl.MakeTransactionVisibleImpl;
import com.gestion.application.service.transaction.impl.GetTransactionSummariesImpl;
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

  /** POST /transactions */
  public TransactionResponse createTransaction(TransactionRequest req) {
    return createImpl.create(req);
  }

  /** GET /transactions (listado sin paginar) */
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

  /**
   * GET /transactions (paginado)
   *
   * Devuelve un Page<TransactionSummaryDTO> con todas las transacciones,
   * ordenadas según el Pageable (por defecto, por fecha desc.).
   */
  public Page<TransactionSummaryDTO> getTransactionSummaries(Pageable pageable) {
    return getSummariesImpl.execute(pageable);
  }

  /**
   * GET /transactions/contact/{id} (paginado)
   *
   * Obtiene una página de transacciones (resumidas) asociadas a un contacto específico.
   * Si el contacto no existe, lanza ContactNotFoundException.
   *
   * @param contactId ID del contacto
   * @param pageable  Parámetros de paginación (page, size, sort)
   * @return Page<TransactionSummaryDTO>
   */
  public Page<TransactionSummaryDTO> getTransactionSummariesByContact(
          Integer contactId,
          Pageable pageable
  ) {
    // 1) Verificar que el contacto existe (no necesariamente es paciente)
    contactRepository.findById(contactId)
            .orElseThrow(() -> new ContactNotFoundException(contactId));

    // 2) Devolver página de resúmenes obtenidos por la query personalizada
    return transactionRepository.findTransactionSummariesByContactId(contactId, pageable);
  }
}

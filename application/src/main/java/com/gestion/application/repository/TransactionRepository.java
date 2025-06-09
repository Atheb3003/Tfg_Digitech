package com.gestion.application.repository;

import com.gestion.application.dto.*;
import com.gestion.application.model.Transaction;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

  List<Transaction> findByPatient_IdPatient(Integer patientId);

  // Método existente para obtener todas las transacciones visibles sin paginar
  List<Transaction> findAllByIsVisibleTrue();

  // Método existente para paginar entidades Transaction
  Page<Transaction> findAllByIsVisibleTrue(Pageable pageable);

  /**
   * NUEVO MÉTODO: devuelve paginado TransactionResponse (incluyendo idContactString)
   * usando un JOIN único a Patient y Contact.
   */
  @Query("""
      SELECT new com.gestion.application.dto.TransactionResponse(
          t.idTransaction,
          t.transactionDate,
          t.amount,
          t.description,
          t.paymentMethod,
          t.isVisible,
          p.idPatient,
          c.idContactString
      )
      FROM Transaction t
      JOIN t.patient p
      JOIN p.contact c
      WHERE t.isVisible = true
  """)
  Page<TransactionResponse> findAllVisibleTransactions(Pageable pageable);

  // Query antigua de resumen, si aún la necesitas
  @Query("""
      SELECT new com.gestion.application.dto.TransactionSummaryDTO(
        t.idTransaction,
        t.transactionDate,
        t.amount,
        t.paymentMethod,
        p.idPatient,
        CONCAT(c.name, ' ', c.surname),
        c.idContact
      )
      FROM Transaction t
      JOIN t.patient p
      JOIN p.contact c
      WHERE t.isVisible = true
  """)
  Page<TransactionSummaryDTO> findAllTransactionSummaries(Pageable pageable);

  @Query("""
      SELECT new com.gestion.application.dto.TransactionSummaryDTO(
        t.idTransaction,
        t.transactionDate,
        t.amount,
        t.paymentMethod,
        p.idPatient,
        CONCAT(c.name, ' ', c.surname),
        c.idContact
      )
      FROM Transaction t
      JOIN t.patient p
      JOIN p.contact c
      WHERE c.idContact = :contactId
  """)
  Page<TransactionSummaryDTO> findTransactionSummariesByContactId(
          @Param("contactId") Integer contactId,
          Pageable pageable
  );

  @Query("""
      SELECT new com.gestion.application.dto.TransactionResponse(
        t.idTransaction,
        t.transactionDate,
        t.amount,
        t.description,
        t.paymentMethod,
        t.isVisible,
        p.idPatient,
        c.idContactString
      )
      FROM Transaction t
      JOIN t.patient p
      JOIN p.contact c
      WHERE t.isVisible = true
        AND (
          LOWER(CAST(t.idTransaction     AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(p.idPatient         AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(t.transactionDate   AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(t.paymentMethod     AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(c.idContactString   AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(c.nif               AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CONCAT(c.name, ' ', c.surname))      LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(c.telephoneNumber1  AS string)) LIKE LOWER(CONCAT('%', :search, '%')) OR
          LOWER(CAST(c.telephoneNumber2  AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
        )
  """)
  Page<TransactionResponse> searchVisibleTransactions(
          @Param("search") String search,
          Pageable pageable
  );

}

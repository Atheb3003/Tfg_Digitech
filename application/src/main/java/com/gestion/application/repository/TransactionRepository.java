// ---------------------------------------------------------------------
// TransactionRepository.java
// ---------------------------------------------------------------------
package com.gestion.application.repository;

import com.gestion.application.dto.TransactionSummaryDTO;
import com.gestion.application.model.Transaction;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;            // <--- importar
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

  List<Transaction> findByPatient_IdPatient(Integer patientId);

  // Método existente que usas para el servicio sin paginar
  List<Transaction> findAllByIsVisibleTrue();

  /**
   * NUEVO: paginar entidades Transaction donde isVisible = true.
   */
  Page<Transaction> findAllByIsVisibleTrue(Pageable pageable);

  // Query de resumen (ya existía): devuelve TransactionSummaryDTO paginado
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
}

package com.gestion.application.repository;

import com.gestion.application.dto.TransactionSummaryDTO;
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

  List<Transaction> findAllByIsVisibleTrue();

  /**
   * Obtener página de transacciones visibles en formato resumido.
   * Ahora incluye el campo idContact para que coincida con el constructor de TransactionSummaryDTO.
   */
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

  /**
   * Obtener página de transacciones (resumidas) asociadas a un contacto específico,
   * ordenadas por transactionDate. El Pageable controla orden y tamaño.
   */
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

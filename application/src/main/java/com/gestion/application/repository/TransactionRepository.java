package com.gestion.application.repository;

import com.gestion.application.model.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

  /** Devuelve todas las transacciones de un paciente dado su ID. */
  List<Transaction> findByPatient_IdPatient(Integer patientId);

  /** (Ya existente) Devuelve todas las transacciones con isVisible = true. */
  List<Transaction> findAllByIsVisibleTrue();
}

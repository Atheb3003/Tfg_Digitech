package com.gestion.application.repository;

import com.gestion.application.model.TransactionDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
  boolean existsByProduct_IdProduct(Integer idProduct);

  void deleteByTransaction_IdTransaction(Integer txId);

  List<TransactionDetail> findByTransaction_IdTransaction(Integer txId);

  List<TransactionDetail> findAllByIsVisibleTrue();

  Page<TransactionDetail> findAllByIsVisibleTrue(Pageable pageable);

  @Query("SELECT td FROM TransactionDetail td " +
          "WHERE td.isVisible = true " +
          "AND td.transaction.transactionDate >= :startDate " +
          "AND td.transaction.transactionDate <= :endDate")
  List<TransactionDetail> findAllByIsVisibleAndTransactionDateBetween(
          @Param("startDate") LocalDate startDate,
          @Param("endDate") LocalDate endDate
  );


}

package com.gestion.application.repository;

import com.gestion.application.model.TransactionDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {
  /** ¿Existe al menos un detalle que use este producto? */
  boolean existsByProduct_IdProduct(Integer idProduct);

  /** Borra en bloque todos los detalles asociados a la transacción {txId}. */
  void deleteByTransaction_IdTransaction(Integer txId);

  // Busca todos los detalles cuyo transaction.idTransaction == txId
  List<TransactionDetail> findByTransaction_IdTransaction(Integer txId);

  List<TransactionDetail> findAllByIsVisibleTrue();
}

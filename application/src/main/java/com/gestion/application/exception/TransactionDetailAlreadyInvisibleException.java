package com.gestion.application.exception;

public class TransactionDetailAlreadyInvisibleException extends RuntimeException {
  public TransactionDetailAlreadyInvisibleException(Integer id) {
    super("Detalle de transacción con ID " + id + " ya está invisible.");
  }
}

package com.gestion.application.exception;

public class TransactionDetailAlreadyVisibleException extends RuntimeException {
  public TransactionDetailAlreadyVisibleException(Integer id) {
    super("Detalle de transacción con ID " + id + " ya está visible.");
  }
}

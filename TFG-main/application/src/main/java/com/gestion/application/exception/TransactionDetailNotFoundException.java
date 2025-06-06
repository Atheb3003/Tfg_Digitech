package com.gestion.application.exception;

public class TransactionDetailNotFoundException extends RuntimeException {
  public TransactionDetailNotFoundException(Integer id) {
    super("Detalle de transacción con ID " + id + " no encontrado.");
  }
}

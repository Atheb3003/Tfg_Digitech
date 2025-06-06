package com.gestion.application.exception;

public class TransactionDetailCreationException extends RuntimeException {
  public TransactionDetailCreationException() {
    super("Error al crear detalle de transacción.");
  }
}

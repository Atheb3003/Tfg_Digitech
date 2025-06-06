package com.gestion.application.exception;

public class TransactionAlreadyInvisibleException extends RuntimeException {
  public TransactionAlreadyInvisibleException(Integer id) {
    super("Transacción con ID " + id + " ya está invisible.");
  }
}

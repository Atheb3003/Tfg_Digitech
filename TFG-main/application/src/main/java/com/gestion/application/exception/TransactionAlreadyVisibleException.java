package com.gestion.application.exception;

public class TransactionAlreadyVisibleException extends RuntimeException {
  public TransactionAlreadyVisibleException(Integer id) {
    super("Transacción con ID " + id + " ya está visible.");
  }
}

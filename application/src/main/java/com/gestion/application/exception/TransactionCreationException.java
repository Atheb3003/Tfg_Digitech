package com.gestion.application.exception;

/** Se lanza cuando ocurre un error inesperado guardando la transacción. */
public class TransactionCreationException extends RuntimeException {
  public TransactionCreationException() {
    super("Error inesperado al crear la transacción");
  }
}

package com.gestion.application.exception;

/** Se lanza cuando faltan o son erróneos datos al crear una transacción. */
public class TransactionInvalidDataException extends IllegalArgumentException {
  public TransactionInvalidDataException() {
    super("Algunos datos son inválidos.");
  }
}

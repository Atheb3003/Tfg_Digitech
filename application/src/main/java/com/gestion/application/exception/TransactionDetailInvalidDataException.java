package com.gestion.application.exception;

public class TransactionDetailInvalidDataException extends RuntimeException {
  public TransactionDetailInvalidDataException() {
    super("Datos inválidos para crear o actualizar detalle de transacción.");
  }
}

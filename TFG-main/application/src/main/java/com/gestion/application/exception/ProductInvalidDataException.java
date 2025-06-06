package com.gestion.application.exception;

/** Se lanza cuando faltan o son inválidos datos al crear/editar un producto. */
public class ProductInvalidDataException extends IllegalArgumentException {
  public ProductInvalidDataException() {
    super("Algunos datos son invalidos.");
  }

  /** Para mensajes específicos, p.ej. precio negativo */
  public ProductInvalidDataException(String message) {
    super(message);
  }
}

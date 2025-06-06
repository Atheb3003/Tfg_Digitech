package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Se lanza cuando el path-variable 'id' no tiene un formato numérico válido. */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdArgumentTypeException extends RuntimeException {
  /**
   * @param value el valor de id que no pudo convertirse a Integer
   */
  public IdArgumentTypeException(String value) {
    super("Error en el formato del id.");
  }
}

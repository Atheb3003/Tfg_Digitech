package com.gestion.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductConflictException extends RuntimeException {
  public ProductConflictException(Integer id) {
    super("El producto está vinculado a transacciones activas");
  }
}

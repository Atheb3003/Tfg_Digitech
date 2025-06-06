// src/main/java/com/gestion/application/exception/ConsultationTypeNotFoundException.java
package com.gestion.application.exception;

public class ConsultationTypeNotFoundException extends RuntimeException {
  public ConsultationTypeNotFoundException(Integer id) {
    super("Tipo de consulta con ID " + id + " no encontrado.");
  }
}

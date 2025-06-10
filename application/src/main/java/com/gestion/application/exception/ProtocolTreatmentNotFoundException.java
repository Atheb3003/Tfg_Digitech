package com.gestion.application.exception;

public class ProtocolTreatmentNotFoundException extends RuntimeException {
  public ProtocolTreatmentNotFoundException(Integer id) {
    super("Detalle de Protocolo con ID " + id + " no encontrado");
  }
}

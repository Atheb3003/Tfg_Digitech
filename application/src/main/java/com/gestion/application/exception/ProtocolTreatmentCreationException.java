// src/main/java/com/gestion/application/exception/ProtocolTreatmentCreationException.java
package com.gestion.application.exception;

public class ProtocolTreatmentCreationException extends RuntimeException {
    public ProtocolTreatmentCreationException(String message) {
        super(message);
    }

    public ProtocolTreatmentCreationException() {
        super("Error al crear ProtocolTreatment");
    }
}

// src/main/java/com/gestion/application/exception/GlobalExceptionHandler.java
package com.gestion.application.exception;

import com.gestion.application.dto.ErrorDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Import de todas las excepciones que manejamos

@RestControllerAdvice
public class GlobalExceptionHandler {

  // 404 — Contacto no encontrado
  @ExceptionHandler(ContactNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleContactNotFound(
      ContactNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Grupo de fotos no encontrado
  @ExceptionHandler(GroupPhotosNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleGroupPhotosNotFound(
      GroupPhotosNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Foto no encontrada
  @ExceptionHandler(PhotoNotFoundException.class)
  public ResponseEntity<ErrorDetails> handlePhotoNotFound(
      PhotoNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Protocolo no encontrado
  @ExceptionHandler(ProtocolNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleProtocolNotFound(
      ProtocolNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Paciente no encontrado
  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<ErrorDetails> handlePatientNotFound(
      PatientNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Reserva de cirugía no encontrada
  @ExceptionHandler(SurgeryReservationNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleSurgeryReservationNotFound(
      SurgeryReservationNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Tipo de producto no encontrado (limpiamos comillas simples)
  @ExceptionHandler(ProductTypeNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleProductTypeNotFound(
      ProductTypeNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err =
        new ErrorDetails(404, "Not Found", ex.getMessage().replace("'", ""), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Producto no encontrado
  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleProductNotFound(
      ProductNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Transacción no encontrada
  @ExceptionHandler(TransactionNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleTransactionNotFound(
      TransactionNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 404 — Detalle de transacción no encontrado
  @ExceptionHandler(TransactionDetailNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleTransactionDetailNotFound(
      TransactionDetailNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 400 — ID con formato inválido
  @ExceptionHandler(IdArgumentTypeException.class)
  public ResponseEntity<ErrorDetails> handleIdFormat(
      IdArgumentTypeException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  // 400 — Argumentos ilegales o genéricos
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorDetails> handleIllegalArgument(
      IllegalArgumentException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  // 400 — Datos inválidos al crear producto
  @ExceptionHandler(ProductInvalidDataException.class)
  public ResponseEntity<ErrorDetails> handleInvalidProductData(
      ProductInvalidDataException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  // 400 — Datos inválidos al crear o actualizar detalle de transacción
  @ExceptionHandler(TransactionDetailInvalidDataException.class)
  public ResponseEntity<ErrorDetails> handleInvalidTransactionDetailData(
      TransactionDetailInvalidDataException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  // 409 — Producto ya existe
  @ExceptionHandler(ProductAlreadyExistsException.class)
  public ResponseEntity<ErrorDetails> handleProductExists(
      ProductAlreadyExistsException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 409 — Transacción ya visible
  @ExceptionHandler(TransactionAlreadyVisibleException.class)
  public ResponseEntity<ErrorDetails> handleAlreadyVisible(
      TransactionAlreadyVisibleException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 409 — Transacción ya invisible
  @ExceptionHandler(TransactionAlreadyInvisibleException.class)
  public ResponseEntity<ErrorDetails> handleAlreadyInvisible(
      TransactionAlreadyInvisibleException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 409 — Detalle de transacción ya visible
  @ExceptionHandler(TransactionDetailAlreadyVisibleException.class)
  public ResponseEntity<ErrorDetails> handleDetailAlreadyVisible(
      TransactionDetailAlreadyVisibleException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 409 — Detalle de transacción ya invisible
  @ExceptionHandler(TransactionDetailAlreadyInvisibleException.class)
  public ResponseEntity<ErrorDetails> handleDetailAlreadyInvisible(
      TransactionDetailAlreadyInvisibleException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 500 — Error al crear producto
  @ExceptionHandler(ProductCreationException.class)
  public ResponseEntity<ErrorDetails> handleProductCreationError(
      ProductCreationException ex, HttpServletRequest req) {
    ErrorDetails err =
        new ErrorDetails(500, "Internal Server Error", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }

  // 500 — Error al buscar producto
  @ExceptionHandler(ProductSearchException.class)
  public ResponseEntity<ErrorDetails> handleProductSearchError(
      ProductSearchException ex, HttpServletRequest req) {
    ErrorDetails err =
        new ErrorDetails(500, "Internal Server Error", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }

  // 500 — Error al crear detalle de transacción
  @ExceptionHandler(TransactionDetailCreationException.class)
  public ResponseEntity<ErrorDetails> handleTransactionDetailCreationError(
      TransactionDetailCreationException ex, HttpServletRequest req) {
    ErrorDetails err =
        new ErrorDetails(500, "Internal Server Error", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }

  // 500 — Cualquier otra excepción no controlada
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleInternalServerError(
      Exception ex, HttpServletRequest req) {
    ErrorDetails err =
        new ErrorDetails(
            500,
            "Internal Server Error",
            "Ocurrió un error inesperado. Intenta más tarde.",
            req.getRequestURI());
    ex.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }

  // 404 — Consulta no encontrada
  @ExceptionHandler(ConsultationNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleConsultationNotFound(
      ConsultationNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 400 — Datos inválidos al crear consulta
  @ExceptionHandler(ConsultationCreationException.class)
  public ResponseEntity<ErrorDetails> handleConsultationCreationError(
      ConsultationCreationException ex, HttpServletRequest req) {
    ErrorDetails err =
        new ErrorDetails(500, "Internal Server Error", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
  }

  // 409 — Consulta ya visible
  @ExceptionHandler(ConsultationAlreadyVisibleException.class)
  public ResponseEntity<ErrorDetails> handleConsultationAlreadyVisible(
      ConsultationAlreadyVisibleException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 409 — Consulta ya invisible
  @ExceptionHandler(ConsultationAlreadyInvisibleException.class)
  public ResponseEntity<ErrorDetails> handleConsultationAlreadyInvisible(
      ConsultationAlreadyInvisibleException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(409, "Conflict", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
  }

  // 404 — Tipo de consulta no encontrado
  @ExceptionHandler(ConsultationTypeNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleConsultationTypeNotFound(
      ConsultationTypeNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 400 — Datos inválidos para tipo de consulta
  @ExceptionHandler(ConsultationTypeInvalidDataException.class)
  public ResponseEntity<ErrorDetails> handleConsultationTypeInvalidData(
      ConsultationTypeInvalidDataException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  // 404 — Tipo de revisión no encontrado
  @ExceptionHandler(RevisionTypeNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleRevisionTypeNotFound(
      RevisionTypeNotFoundException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  // 400 — Datos inválidos para tipo de revisión
  @ExceptionHandler(RevisionTypeInvalidDataException.class)
  public ResponseEntity<ErrorDetails> handleRevisionTypeInvalidData(
      RevisionTypeInvalidDataException ex, HttpServletRequest req) {
    ErrorDetails err = new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }

  // 404 — Tratamiento de revisión no encontrado
  @ExceptionHandler(RevisionTreatmentNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleRevTreatNotFound(
      RevisionTreatmentNotFoundException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorDetails(404, "Not Found", ex.getMessage(), req.getRequestURI()));
  }

  // 400 — Datos inválidos para tratamiento de revisión
  @ExceptionHandler(RevisionTreatmentInvalidDataException.class)
  public ResponseEntity<ErrorDetails> handleRevTreatInvalid(
      RevisionTreatmentInvalidDataException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDetails(400, "Bad Request", ex.getMessage(), req.getRequestURI()));
  }

  // 500 — Error al crear tratamiento de revisión
  @ExceptionHandler(RevisionTreatmentCreationException.class)
  public ResponseEntity<ErrorDetails> handleRevTreatCreationError(
      RevisionTreatmentCreationException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorDetails(500, "Internal Server Error", ex.getMessage(), req.getRequestURI()));
  }
}

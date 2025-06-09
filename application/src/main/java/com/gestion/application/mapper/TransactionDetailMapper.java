package com.gestion.application.mapper;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.model.*;
import org.springframework.stereotype.Component;

@Component
public class TransactionDetailMapper {

  public TransactionDetail toEntity(TransactionDetailRequest dto) {
    TransactionDetail detail = new TransactionDetail();

    // Transacción
    if (dto.getTransactionId() != null) {
      Transaction tx = new Transaction();
      tx.setIdTransaction(dto.getTransactionId());
      detail.setTransaction(tx);
    }

    // Producto
    if (dto.getProductId() != null) {
      Product p = new Product();
      p.setIdProduct(dto.getProductId());
      detail.setProduct(p);
    }

    // Tratamiento
    if (dto.getProtocolTreatmentId() != null) {
      ProtocolTreatment pt = new ProtocolTreatment();
      pt.setId(dto.getProtocolTreatmentId());
      detail.setProtocolTreatment(pt);
    }

    // Reserva cirugía
    if (dto.getSurgeryReservationId() != null) {
      SurgeryReservation sr = new SurgeryReservation();
      sr.setIdSurgeryReservation(dto.getSurgeryReservationId());
      detail.setSurgeryReservation(sr);
    }

    detail.setQuantity(dto.getQuantity());
    detail.setPrice(dto.getPrice());
    detail.setIsVisible(true);

    return detail;
  }

  public TransactionDetailResponse toResponse(TransactionDetail entity) {
    TransactionDetailResponse dto = new TransactionDetailResponse();

    dto.setIdDetail(entity.getIdDetail()); // ← ID propio

    if (entity.getTransaction() != null) {
      dto.setTransactionId(entity.getTransaction().getIdTransaction());
    }

    if (entity.getProduct() != null) {
      dto.setProductId(entity.getProduct().getIdProduct());
    }

    dto.setQuantity(entity.getQuantity());
    dto.setPrice(entity.getPrice());

    if (entity.getProtocolTreatment() != null) {
      dto.setProtocolTreatmentId(entity.getProtocolTreatment().getId());
    }

    if (entity.getSurgeryReservation() != null) {
      dto.setSurgeryReservationId(entity.getSurgeryReservation().getIdSurgeryReservation());
    }

    return dto;
  }
}

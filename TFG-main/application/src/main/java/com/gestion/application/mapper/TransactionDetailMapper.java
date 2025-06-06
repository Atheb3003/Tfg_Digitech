package com.gestion.application.mapper;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.Transaction;
import com.gestion.application.model.TransactionDetail;
import org.springframework.stereotype.Component;

/**
 * Mapea entre DTOs y entidad TransactionDetail.
 */
@Component
public class TransactionDetailMapper {

  /**
   * Convierte un TransactionDetailRequest a una entidad TransactionDetail lista para guardar.
   */
  public TransactionDetail toEntity(TransactionDetailRequest dto) {
    TransactionDetail detail = new TransactionDetail();

    // Asociar transacción por ID (solo establecemos la referencia, no cargamos toda la entidad)
    Transaction tx = new Transaction();
    tx.setIdTransaction(dto.getTransactionId());
    detail.setTransaction(tx);

    // Asociar producto por ID (igual, solo referencia)
    Product p = new Product();
    p.setIdProduct(dto.getProductId());
    detail.setProduct(p);

    detail.setQuantity(dto.getQuantity());
    detail.setPrice(dto.getPrice());
    detail.setIsVisible(true);

    return detail;
  }

  /**
   * Convierte la entidad TransactionDetail a DTO de respuesta, incluyendo datos de la transacción padre,
   * del paciente, contacto y nombre de tipo de producto.
   */
  public TransactionDetailResponse toResponse(TransactionDetail entity) {
    TransactionDetailResponse dto = new TransactionDetailResponse();

    // Datos de la transacción padre
    var tx = entity.getTransaction();
    dto.setIdTransaction(tx.getIdTransaction());
    dto.setTransactionDate(tx.getTransactionDate());
    dto.setAmount(tx.getAmount());
    dto.setPaymentMethod(tx.getPaymentMethod().name());

    // Datos de paciente y contacto
    var patient = tx.getPatient();
    dto.setIdPatient(patient.getIdPatient());
    dto.setFullName(patient.getContact().getName() + " " + patient.getContact().getSurname());
    dto.setIdContact(patient.getContact().getIdContact());

    // Nombre del tipo de producto
    dto.setProductType(entity.getProduct().getProductType().getTypeProduct());

    return dto;
  }
}

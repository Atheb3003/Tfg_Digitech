package com.gestion.application.mapper;

import com.gestion.application.dto.TransactionDetailRequest;
import com.gestion.application.dto.TransactionDetailResponse;
import com.gestion.application.model.Product;
import com.gestion.application.model.Transaction;
import com.gestion.application.model.TransactionDetail;
import org.springframework.stereotype.Component;

@Component
public class TransactionDetailMapper {

  public TransactionDetail toEntity(TransactionDetailRequest dto) {
    TransactionDetail detail = new TransactionDetail();
    detail.setQuantity(dto.getQuantity());
    detail.setPrice(dto.getPrice());

    Transaction tx = new Transaction();
    tx.setIdTransaction(dto.getTransactionId());
    detail.setTransaction(tx);

    Product p = new Product();
    p.setIdProduct(dto.getProductId());
    detail.setProduct(p);

    detail.setIsVisible(true);
    return detail;
  }

  public TransactionDetailResponse toResponse(TransactionDetail entity) {
    TransactionDetailResponse dto = new TransactionDetailResponse();
    dto.setIdDetail(entity.getIdDetail());
    dto.setTransactionId(entity.getTransaction().getIdTransaction());
    dto.setProductId(entity.getProduct().getIdProduct());
    dto.setQuantity(entity.getQuantity());
    dto.setPrice(entity.getPrice());
    dto.setIsVisible(entity.getIsVisible());
    return dto;
  }
}

package com.gestion.application.dto;

import com.gestion.application.model.PaymentMethod;
import java.time.LocalDate;
import lombok.Data;

@Data
public class SoldItemResponse {
  private String type;
  private String name;
  private Integer transactionId;
  private PaymentMethod paymentMethod;
  private Double amount;
  private String idContactString;
  private LocalDate transactionDate;
}

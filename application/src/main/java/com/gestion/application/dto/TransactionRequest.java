package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestion.application.model.PaymentMethod;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransactionRequest {
  @JsonProperty("transaction_date")
  private LocalDate transactionDate;

  private Double amount;

  private String description;

  @JsonProperty("payment_method")
  private PaymentMethod paymentMethod;

  @JsonProperty("id_patient")
  private Integer idPatient;
}

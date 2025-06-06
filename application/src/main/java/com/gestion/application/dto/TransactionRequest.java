package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransactionRequest {
  @JsonProperty("transaction_date")
  private LocalDate transactionDate;

  private Double amount;

  private String description;

  @JsonProperty("id_patient")
  private Integer idPatient;
}

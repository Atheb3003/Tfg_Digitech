package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;

@Data
public class TransactionDetailResponse {

  @JsonProperty("idTransaction")
  private Integer idTransaction;

  @JsonProperty("transactionDate")
  private LocalDate transactionDate;

  private Double amount;

  @JsonProperty("paymentMethod")
  private String paymentMethod;

  @JsonProperty("idPatient")
  private Integer idPatient;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("idContact")
  private Integer idContact;

  @JsonProperty("productType")
  private String productType;
}

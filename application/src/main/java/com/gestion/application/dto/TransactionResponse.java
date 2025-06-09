package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestion.application.model.PaymentMethod;
import java.time.LocalDate;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * DTO para respuesta de transacciones visibles,
 * construido directamente desde la query JPQL.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

  @JsonProperty("id_transaction")
  private Integer idTransaction;

  @JsonProperty("transaction_date")
  private LocalDate transactionDate;

  private Double amount;
  private String description;

  @JsonProperty("payment_method")
  private PaymentMethod paymentMethod;

  @JsonProperty("is_visible")
  private Boolean isVisible;

  @JsonProperty("id_patient")
  private Integer idPatient;

  @JsonProperty("id_contact_string")
  private String idContactString;

}

package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gestion.application.model.PaymentMethod;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de respuesta para búsqueda de transacciones, contiene todos los campos por los que se puede
 * filtrar.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTransactionResponseDTO {

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

  @JsonProperty("dni")
  private String dni;

  @JsonProperty("contact_full_name")
  private String contactFullName;

  @JsonProperty("telephone_number_1")
  private String telephoneNumber1;

  @JsonProperty("telephone_number_2")
  private String telephoneNumber2;
}

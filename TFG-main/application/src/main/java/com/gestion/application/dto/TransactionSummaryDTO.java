package com.gestion.application.dto;

import com.gestion.application.model.PaymentMethod;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Resumen de una transacción para listados.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionSummaryDTO {

  private Integer idTransaction;
  private LocalDate transactionDate;
  private Double amount;
  private PaymentMethod paymentMethod;
  private Integer idPatient;
  private String fullName;
  private Integer idContact;
}


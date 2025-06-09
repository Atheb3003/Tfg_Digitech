package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDetailRequest {

  @JsonProperty("id_transaction")
  private Integer transactionId;

  @JsonProperty("id_product")
  private Integer productId;

  private Integer quantity;

  private BigDecimal price;

  @JsonProperty("id_protocol_treatment")
  private Integer protocolTreatmentId;

  @JsonProperty("id_surgery_reservation")
  private Integer surgeryReservationId;
}

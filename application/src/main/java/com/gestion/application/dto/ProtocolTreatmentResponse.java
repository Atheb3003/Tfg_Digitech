package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor    // ← Genera un constructor con todos los campos
public class ProtocolTreatmentResponse {

  private Integer id;

  @JsonProperty("id_product")
  private Integer productId;

  @JsonProperty("product_name")
  private String productName;

  @JsonProperty("product_price")
  private BigDecimal price;

  @JsonProperty("is_paid")
  private Boolean isPaid;

  @JsonProperty("is_finished")
  private Boolean isFinished;
}

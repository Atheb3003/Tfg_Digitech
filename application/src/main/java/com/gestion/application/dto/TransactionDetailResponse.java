package com.gestion.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDetailResponse {

  @JsonProperty("id_detail")
  private Integer idDetail;

  @JsonProperty("id_transaction")
  private Integer transactionId;

  @JsonProperty("id_product")
  private Integer productId;

  private Integer quantity;

  private BigDecimal price;

  @JsonProperty("is_visible")
  private Boolean isVisible;
}

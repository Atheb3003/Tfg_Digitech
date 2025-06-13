package com.gestion.application.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProtocolSearchTreatmentDto {
    private Integer treatmentId;
    private BigDecimal treatmentPrice;
    private String productName;
    private Boolean paid;
    private Boolean complete;
}

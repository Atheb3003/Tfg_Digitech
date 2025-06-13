package com.gestion.application.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDetailInfoResponse {
    private Integer idDetail;
    private String type; // "PRODUCT", "PROTOCOL_TREATMENT", "SURGERY"

    // PRODUCT
    private String productName;
    private Integer quantity;
    private BigDecimal price; // Para product y protocol

    // PROTOCOL_TREATMENT
    private Boolean protocolFinished;
    private Integer protocolId;

    // SURGERY
    private String surgicalTechnique;
    private LocalDate estimatedDate;
    private Integer follicularUnits;
    private BigDecimal surgeryPrice;
    private String surgeryDescription;
}

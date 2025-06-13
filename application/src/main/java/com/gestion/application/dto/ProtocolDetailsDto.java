package com.gestion.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProtocolDetailsDto {
    private Integer idProtocol;
    private String protocolDescription;
    private BigDecimal totalPrice;
    private String idContactString;
    private Boolean isFinished;
    private List<ProtocolTreatmentInfoDto> protocolTreatments;
}
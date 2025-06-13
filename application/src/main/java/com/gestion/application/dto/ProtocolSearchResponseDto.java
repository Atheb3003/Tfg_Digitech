package com.gestion.application.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProtocolSearchResponseDto {
    private Integer protocolId;
    private String protocolDescription;
    private BigDecimal protocolTotalPrice;
    private String contactIdString;
    private Boolean finished;
    private List<ProtocolSearchTreatmentDto> treatments;
}

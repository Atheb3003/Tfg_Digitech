package com.gestion.application.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * DTO para añadir un pago a una SurgeryReservation existente.
 */
@Data
public class AddPaymentRequest {
    private BigDecimal amount;
}

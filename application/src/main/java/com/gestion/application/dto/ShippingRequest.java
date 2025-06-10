package com.gestion.application.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class ShippingRequest {
  private Long idContacto;
  private LocalDate fechaEnvio;
  private String localizador;
  private String pais;
  private String metodoPago;
  private List<Long> productIds;
  private boolean internacional;
  private boolean enviado;
  private LocalDate fechaRealEnvio;
  private String idUnicoPaciente;

  // Getters y Setters
}

package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RevisionRequest {
  private Integer idPatient;
  private Integer idType;
  private Integer idProtocol;
  private String observations;
  private LocalDate revisionDate;
}

package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class RevisionResponse {
  private Integer idRevision;
  private Integer idPatient;
  private String patientName;
  private Integer idType;
  private String typeName;
  private Integer idProtocol;
  private String observations;
  private LocalDate revisionDate;
  private Boolean isVisible;
}

package com.gestion.application.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/** DTO que se devuelve al cliente con los datos de una Revision. */
@Getter
@Setter
public class RevisionResponse {
  private Integer idRevision;
  private Integer idContact;
  private Integer idType;
  private Integer idProtocol; // puede ser null
  private String observations;
  private LocalDate revisionDate;
  private Integer idPatient; // puede ser null
  private Boolean isVisible;
}

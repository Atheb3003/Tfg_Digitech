package com.gestion.application.dto;

import lombok.Data;

@Data
public class CreateGroupPhotosRequest {
  private Integer contactId;
  private Integer consultationId;
  private Integer revisionId;
  private String title;
  private String description;
  private Boolean isVisible;
}

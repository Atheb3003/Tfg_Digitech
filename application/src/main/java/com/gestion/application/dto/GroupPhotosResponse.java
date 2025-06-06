package com.gestion.application.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class GroupPhotosResponse {
  private Integer groupId;
  private String title;
  private String description;
  private LocalDate creationDate;

  private Integer contactId;
  private Integer consultationId;
  private Integer revisionId;

  private List<String> photos;
}

package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "revision_type")
@Data
public class RevisionType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idRevisionType;

  @Column(name = "type_name", nullable = false)
  private String typeName;
}

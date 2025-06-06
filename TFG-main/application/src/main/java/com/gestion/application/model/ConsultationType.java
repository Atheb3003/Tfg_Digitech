package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "consultation_type")
public class ConsultationType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idType;

  @Column(nullable = false)
  private String typeName;
}

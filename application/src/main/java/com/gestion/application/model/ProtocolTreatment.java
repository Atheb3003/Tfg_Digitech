package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "protocol_treatment")
@Data
public class ProtocolTreatment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_protocol")
  private Protocol protocol;

  @ManyToOne
  @JoinColumn(name = "id_product")
  private Product product;

  @Column(name = "is_finished")
  private Boolean isFinished = false;

  @Column(name = "is_paid")
  private Boolean isPaid = false;
}

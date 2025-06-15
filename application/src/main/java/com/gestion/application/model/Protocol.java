package com.gestion.application.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "protocol")
@Data
public class Protocol {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idProtocol;

  private BigDecimal price;

  private String description;

  private Boolean isFinished = false;

  @ManyToOne
  @JoinColumn(name = "id_contact")
  private Contact contact;

  @OneToMany(mappedBy = "protocol", cascade = CascadeType.ALL)
  private List<ProtocolTreatment> treatments;
}

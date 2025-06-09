// src/main/java/com/gestion/application/model/TransactionDetail.java
package com.gestion.application.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Entity
@Table(name = "transaction_details")
@Data
public class TransactionDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_detail")
  private Integer idDetail;

  @ManyToOne
  @JoinColumn(name = "id_transaction", nullable = false)
  private Transaction transaction;

  @ManyToOne
  @JoinColumn(name = "id_product", nullable =true)
  private Product product;

  private Integer quantity;

  private BigDecimal price;

  @ManyToOne(optional = true)
  @JoinColumn(name = "id_protocol_treatment", nullable = true)
  private ProtocolTreatment protocolTreatment;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idSurgeryReservation", nullable = true)
  private SurgeryReservation surgeryReservation;



  @Column(name = "is_visible")
  private Boolean isVisible = true;
}

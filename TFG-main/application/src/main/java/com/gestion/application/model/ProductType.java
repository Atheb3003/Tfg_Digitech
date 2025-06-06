package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product_type")
@Data
public class ProductType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idProductType;

  @Column(name = "type_product", nullable = false)
  private String typeProduct;
}

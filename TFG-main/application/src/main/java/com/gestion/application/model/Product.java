package com.gestion.application.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idProduct;

  private String name;
  private String description;
  private Double price;

  private LocalDate creationDate;
  private LocalDate updateDate;

  @ManyToOne
  @JoinColumn(name = "id_product_type")
  private ProductType productType;

  private Boolean isVisible;
}

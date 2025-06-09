package com.gestion.application.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "shippings")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idContacto;

    private LocalDate fechaEnvio;

    private String localizador;

    private String pais;

    private String metodoPago;

    @ElementCollection
    @CollectionTable(name = "shipping_products", joinColumns = @JoinColumn(name = "shipping_id"))
    @Column(name = "product_id")
    private List<Long> productIds;

    private boolean internacional;

    private boolean enviado;

    private LocalDate fechaRealEnvio;

    private String idUnicoPaciente;
}

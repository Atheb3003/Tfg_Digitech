package com.gestion.application.repository;

import com.gestion.application.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  /** Comprueba si ya existe un producto con ese nombre (case-sensitive). */
  boolean existsByName(String name);

  /** Devuelve sólo los productos donde isVisible == true */
  List<Product> findByIsVisibleTrue();
}

package com.gestion.application.repository;

import com.gestion.application.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  boolean existsByName(String name);

  // (Sin paginar) para que GetVisibleProductsImpl funcione
  List<Product> findByIsVisibleTrue();

  // (Paginada) para GetVisibleProductsPageImpl y ProductService
  Page<Product> findByIsVisibleTrue(Pageable pageable);
}

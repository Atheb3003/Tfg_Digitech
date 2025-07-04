package com.gestion.application.repository;

import com.gestion.application.model.Shipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
  Page<Shipping> findAllByOrderByIdDesc(Pageable pageable);
}

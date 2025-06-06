package com.gestion.application.service.product.impl;

import com.gestion.application.dto.ProductResponse;
import com.gestion.application.mapper.ProductMapper;
import com.gestion.application.model.Product;
import com.gestion.application.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetVisibleProductsPageImpl {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Page<ProductResponse> getVisibleProducts(Pageable pageable) {
        Page<Product> page = repository.findByIsVisibleTrue(pageable);
        return page.map(mapper::toResponse);
    }
}

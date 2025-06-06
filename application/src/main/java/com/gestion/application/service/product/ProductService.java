package com.gestion.application.service.product;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.service.product.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Fachada de servicio para Product */
@Service
@RequiredArgsConstructor
public class ProductService {

  private final CreateProductImpl createImpl;
  private final GetAllProductsImpl listImpl;
  private final GetVisibleProductsImpl visibleImpl;
  private final GetProductByIdImpl byIdImpl;
  private final UpdateProductImpl updateImpl;
  private final DeleteProductImpl deleteImpl;
  private final ToggleProductVisibilityImpl toggleImpl;

  /** POST /products */
  public ProductResponse createProduct(ProductRequest req) {
    return createImpl.createPRoduct(req);
  }

  /** GET /products */
  public List<ProductResponse> getAllProducts() {
    return listImpl.getAllProducts();
  }

  /** GET /products/visible */
  public List<ProductResponse> getVisibleProducts() {
    return visibleImpl.getVisibleProducts();
  }

  /** GET /products/{id} */
  public ProductResponse getProductById(Integer id) {
    return byIdImpl.getById(id);
  }

  /** Actualiza un producto existente (PUT /products/{id}) */
  public ProductResponse updateProduct(Integer id, ProductRequest req) {
    return updateImpl.update(id, req);
  }

  /** DELETE /products/{id} */
  public void deleteProduct(Integer id) {
    deleteImpl.deleteProduct(id);
  }

  /** PATCH /products/{id}/visibility */
  public ProductResponse toggleProductVisibility(Integer id) {
    return toggleImpl.toggleVisibility(id);
  }
}

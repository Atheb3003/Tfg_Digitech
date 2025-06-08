package com.gestion.application.service.product;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.service.product.impl.CreateProductImpl;
import com.gestion.application.service.product.impl.DeleteProductImpl;
import com.gestion.application.service.product.impl.GetAllProductsImpl;
import com.gestion.application.service.product.impl.GetProductByIdImpl;
import com.gestion.application.service.product.impl.GetVisibleProductsImpl;
import com.gestion.application.service.product.impl.GetVisibleProductsPageImpl;
import com.gestion.application.service.product.impl.ToggleProductVisibilityImpl;
import com.gestion.application.service.product.impl.UpdateProductImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

  // Nuevo: impl que ofrece paginación
  private final GetVisibleProductsPageImpl visiblePageImpl;

  public ProductResponse createProduct(ProductRequest req) {
    return createImpl.createPRoduct(req);
  }

  /**
   * GET /products?page=X&size=Y&sort=...
   * — Devuelve Page<ProductResponse> con isVisible == true
   */
  public Page<ProductResponse> getVisibleProducts(Pageable pageable) {
    return visiblePageImpl.getVisibleProducts(pageable);
  }

  /**
   * GET /products/all  (sin paginar, si se necesita)
   */
  public List<ProductResponse> getAllProducts() {
    return listImpl.getAllProducts();
  }

  /**
   * GET /products/visible  (sin paginar, si se necesita)
   */
  public List<ProductResponse> getVisibleProductsList() {
    return visibleImpl.getVisibleProducts();
  }

  public ProductResponse getProductById(Integer id) {
    return byIdImpl.getById(id);
  }

  public ProductResponse updateProduct(Integer id, ProductRequest req) {
    return updateImpl.update(id, req);
  }

  public void deleteProduct(Integer id) {
    deleteImpl.deleteProduct(id);
  }

  public ProductResponse toggleProductVisibility(Integer id) {
    return toggleImpl.toggleVisibility(id);
  }
}

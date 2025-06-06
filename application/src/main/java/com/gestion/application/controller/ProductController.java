package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.service.product.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Endpoints para Product */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  /** Crear un producto */
  @PostMapping
  public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
      @RequestBody ProductRequest req) {
    ProductResponse dto = productService.createProduct(req);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", dto));
  }

  /** Listar todos los productos */
  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {
    List<ProductResponse> list = productService.getAllProducts();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /products/visible */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getVisibleProducts() {
    List<ProductResponse> list = productService.getVisibleProducts();
    return ResponseEntity.ok(new ApiResponse<>("success", list));
  }

  /** GET /products/{id} */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Integer id) {
    ProductResponse dto = productService.getProductById(id);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }

  /** PUT /products/{id} */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
      @PathVariable Integer id, @RequestBody ProductRequest req) {
    ProductResponse dto = productService.updateProduct(id, req);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }

  /** DELETE /products/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<SuccessfulDeleteResponse>> deleteProduct(
      @PathVariable Integer id) {
    productService.deleteProduct(id);
    SuccessfulDeleteResponse resp =
        new SuccessfulDeleteResponse(
            "deleted", "Producto con id " + id + " eliminado correctamente");
    return ResponseEntity.ok(new ApiResponse<>("deleted", resp));
  }

  /**
   * PATCH /products/{id}/visibility Invierte la visibilidad: si estaba visible, pasa a invisible, y
   * viceversa.
   */
  @PatchMapping("/{id}/visibility")
  public ResponseEntity<ApiResponse<ProductResponse>> toggleVisibility(@PathVariable Integer id) {
    ProductResponse dto = productService.toggleProductVisibility(id);
    return ResponseEntity.ok(new ApiResponse<>("success", dto));
  }
}

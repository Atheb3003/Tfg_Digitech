package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.service.product.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
          @RequestBody ProductRequest req) {
    ProductResponse created = productService.createProduct(req);
    ApiResponse<ProductResponse> apiResponse = new ApiResponse<>("created", created);
    return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
  }

  /**
   * GET /products?page=0&size=10&sort=name,asc
   * — Devuelve una página de productos con isVisible == true
   */
  @GetMapping
  public ResponseEntity<ApiResponse<Page<ProductResponse>>> getAllProducts(Pageable pageable) {
    Page<ProductResponse> page = productService.getVisibleProducts(pageable);
    ApiResponse<Page<ProductResponse>> apiResponse = new ApiResponse<>("success", page);
    return ResponseEntity.ok(apiResponse);
  }

  /**
   * GET /products/visible  (versión sin paginar, si la necesitas)
   */
  @GetMapping("/visible")
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getVisibleProducts() {
    List<ProductResponse> list = productService.getVisibleProductsList();
    ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>("success", list);
    return ResponseEntity.ok(apiResponse);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
          @PathVariable("id") Integer id,
          @RequestBody ProductRequest req) {
    ProductResponse updated = productService.updateProduct(id, req);
    ApiResponse<ProductResponse> apiResponse = new ApiResponse<>("success", updated);
    return ResponseEntity.ok(apiResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> deleteProduct(@PathVariable Integer id) {
    productService.deleteProduct(id);
    SuccessfulDeleteResponse response =
            new SuccessfulDeleteResponse(
                    "deleted", "Producto con ID " + id + " eliminado correctamente.");
    return ResponseEntity.ok(response);
  }

  @PatchMapping("/{id}/visibility")
  public ResponseEntity<ApiResponse<ProductResponse>> toggleVisibility(@PathVariable Integer id) {
    ProductResponse dto = productService.toggleProductVisibility(id);
    ApiResponse<ProductResponse> apiResponse = new ApiResponse<>("success", dto);
    return ResponseEntity.ok(apiResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Integer id) {
    ProductResponse dto = productService.getProductById(id);
    ApiResponse<ProductResponse> apiResponse = new ApiResponse<>("success", dto);
    return ResponseEntity.ok(apiResponse);
  }
}

package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.dto.SuccessfulDeleteResponse;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.producttype.ProductTypeService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductTypeController {

  private final ProductTypeService productTypeService;

  /** POST /products/type Crea un nuevo tipo de producto. */
  @PostMapping("/type")
  public ResponseEntity<ApiResponse<ProductTypeResponse>> createProductType(
      @RequestBody ProductTypeRequest request) {
    ProductTypeResponse response = productTypeService.createProductType(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("created", response));
  }

  /** GET /products/types Devuelve todos los tipos de producto. */
  @GetMapping("/types")
  public ResponseEntity<?> getAllProductTypes() {
    List<ProductTypeResponse> list = productTypeService.getAllProductTypes();
    return ResponseEntity.ok(Map.of("status", "success", "data", list));
  }

  /**
   * GET /products/type/{id} Devuelve un tipo de producto por ID. Lanza IdArgumentTypeException si
   * el formato del ID no es numérico.
   */
  @GetMapping("/type/{id}")
  public ResponseEntity<?> getProductTypeById(@PathVariable String id) {
    Integer idInt;
    try {
      idInt = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      // Formato incorrecto: lanzamos nuestra excepción
      throw new IdArgumentTypeException(id);
    }
    // Si el id es válido, delegamos al servicio
    ProductTypeResponse dto = productTypeService.getProductTypeById(idInt);
    return ResponseEntity.ok(Map.of("status", "success", "data", List.of(dto)));
  }

  @PutMapping("/type/{id}")
  public ResponseEntity<ApiResponse<ProductTypeResponse>> updateProductType(
      @PathVariable String id, @RequestBody ProductTypeRequest request) {

    // 1) Validar formato de ID
    Integer idInt;
    try {
      idInt = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    // 2) Delegar a la fachada
    ProductTypeResponse updated = productTypeService.updateProductType(idInt, request);

    // 3) Responder
    return ResponseEntity.ok(new ApiResponse<>("updated", updated));
  }

  /**
   * DELETE /products/type/{id} Elimina un tipo de producto por ID. - 400 si el ID no es numérico. -
   * 404 si no existe. - 500 para cualquier otro error.
   */
  @DeleteMapping("/type/{id}")
  public ResponseEntity<SuccessfulDeleteResponse> deleteProductType(@PathVariable String id) {
    // 1) Validación de formato
    Integer idInt;
    try {
      idInt = Integer.valueOf(id);
    } catch (NumberFormatException ex) {
      throw new IdArgumentTypeException(id);
    }

    // 2) Delegar al servicio
    productTypeService.deleteProductType(idInt);

    // 3) Construir respuesta
    SuccessfulDeleteResponse resp =
        new SuccessfulDeleteResponse(
            "deleted", "Tipo de producto con ID " + idInt + " eliminado correctamente.");
    return ResponseEntity.ok(resp);
  }
}

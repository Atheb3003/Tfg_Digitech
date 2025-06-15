package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.product.ProductService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class ProductControllerTest {

  @Mock private ProductService productService;

  @InjectMocks private ProductController controller;

  private ProductRequest request;
  private ProductResponse response;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    request = new ProductRequest();
    request.setName("Test Product");
    request.setDescription("Some description");
    request.setPrice(99.99);
    request.setIdProductType(1);

    response = new ProductResponse();
    response.setIdProduct(1);
    response.setName("Test Product");
    response.setIsVisible(true);
    response.setCreationDate(LocalDate.now());
  }

  @Test
  void testCreateProduct() {
    when(productService.createProduct(request)).thenReturn(response);

    var result = controller.createProduct(request);

    assertEquals(201, result.getStatusCodeValue());
    assertEquals("created", result.getBody().getStatus());
    assertEquals(response, result.getBody().getData());
  }

  @Test
  void testGetAllProducts() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<ProductResponse> page = new PageImpl<>(List.of(response));

    when(productService.getVisibleProducts(pageable)).thenReturn(page);

    var result = controller.getAllProducts(pageable);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData().getContent().size());
  }

  @Test
  void testGetVisibleProducts() {
    when(productService.getVisibleProductsList()).thenReturn(List.of(response));

    var result = controller.getVisibleProducts();

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals(1, result.getBody().getData().size());
  }

  @Test
  void testGetProductById() {
    when(productService.getProductById(1)).thenReturn(response);

    var result = controller.getProductById(1);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals("Test Product", result.getBody().getData().getName());
  }

  @Test
  void testUpdateProduct() {
    when(productService.updateProduct(1, request)).thenReturn(response);

    var result = controller.updateProduct(1, request);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("success", result.getBody().getStatus());
    assertEquals("Test Product", result.getBody().getData().getName());
  }

  @Test
  void testDeleteProduct() {
    doNothing().when(productService).deleteProduct(1);

    var result = controller.deleteProduct(1);

    assertEquals(200, result.getStatusCodeValue());
    assertTrue(result.getBody().getMessage().contains("eliminado correctamente"));
  }

  @Test
  void testToggleVisibility() {
    response.setIsVisible(false);
    when(productService.toggleProductVisibility(1)).thenReturn(response);

    var result = controller.toggleVisibility(1);

    assertEquals(200, result.getStatusCodeValue());
    assertFalse(result.getBody().getData().getIsVisible());
  }
}

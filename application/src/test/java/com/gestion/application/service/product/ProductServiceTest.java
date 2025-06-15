package com.gestion.application.service.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductRequest;
import com.gestion.application.dto.ProductResponse;
import com.gestion.application.service.product.impl.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.*;

class ProductServiceTest {

  private CreateProductImpl createImpl;
  private GetAllProductsImpl listImpl;
  private GetVisibleProductsImpl visibleImpl;
  private GetProductByIdImpl byIdImpl;
  private UpdateProductImpl updateImpl;
  private DeleteProductImpl deleteImpl;
  private ToggleProductVisibilityImpl toggleImpl;
  private GetVisibleProductsPageImpl visiblePageImpl;

  private ProductService service;

  @BeforeEach
  void setup() {
    createImpl = mock(CreateProductImpl.class);
    listImpl = mock(GetAllProductsImpl.class);
    visibleImpl = mock(GetVisibleProductsImpl.class);
    byIdImpl = mock(GetProductByIdImpl.class);
    updateImpl = mock(UpdateProductImpl.class);
    deleteImpl = mock(DeleteProductImpl.class);
    toggleImpl = mock(ToggleProductVisibilityImpl.class);
    visiblePageImpl = mock(GetVisibleProductsPageImpl.class);

    service =
        new ProductService(
            createImpl,
            listImpl,
            visibleImpl,
            byIdImpl,
            updateImpl,
            deleteImpl,
            toggleImpl,
            visiblePageImpl);
  }

  @Test
  void testCreateProduct() {
    ProductRequest req = new ProductRequest();
    ProductResponse expected = new ProductResponse();

    when(createImpl.createPRoduct(req)).thenReturn(expected);

    ProductResponse result = service.createProduct(req);

    assertEquals(expected, result);
    verify(createImpl).createPRoduct(req);
  }

  @Test
  void testGetAllProducts() {
    List<ProductResponse> expected = Arrays.asList(new ProductResponse());
    when(listImpl.getAllProducts()).thenReturn(expected);

    List<ProductResponse> result = service.getAllProducts();

    assertEquals(expected, result);
    verify(listImpl).getAllProducts();
  }

  @Test
  void testGetVisibleProductsList() {
    List<ProductResponse> expected = Arrays.asList(new ProductResponse());
    when(visibleImpl.getVisibleProducts()).thenReturn(expected);

    List<ProductResponse> result = service.getVisibleProductsList();

    assertEquals(expected, result);
    verify(visibleImpl).getVisibleProducts();
  }

  @Test
  void testGetVisibleProductsPaged() {
    Pageable pageable = PageRequest.of(0, 10);
    Page<ProductResponse> expected = new PageImpl<>(Arrays.asList(new ProductResponse()));
    when(visiblePageImpl.getVisibleProducts(pageable)).thenReturn(expected);

    Page<ProductResponse> result = service.getVisibleProducts(pageable);

    assertEquals(expected, result);
    verify(visiblePageImpl).getVisibleProducts(pageable);
  }

  @Test
  void testGetProductById() {
    Integer id = 1;
    ProductResponse expected = new ProductResponse();
    when(byIdImpl.getById(id)).thenReturn(expected);

    ProductResponse result = service.getProductById(id);

    assertEquals(expected, result);
    verify(byIdImpl).getById(id);
  }

  @Test
  void testUpdateProduct() {
    Integer id = 1;
    ProductRequest req = new ProductRequest();
    ProductResponse expected = new ProductResponse();
    when(updateImpl.update(id, req)).thenReturn(expected);

    ProductResponse result = service.updateProduct(id, req);

    assertEquals(expected, result);
    verify(updateImpl).update(id, req);
  }

  @Test
  void testDeleteProduct() {
    Integer id = 2;
    service.deleteProduct(id);
    verify(deleteImpl).deleteProduct(id);
  }

  @Test
  void testToggleProductVisibility() {
    Integer id = 3;
    ProductResponse expected = new ProductResponse();
    when(toggleImpl.toggleVisibility(id)).thenReturn(expected);

    ProductResponse result = service.toggleProductVisibility(id);

    assertEquals(expected, result);
    verify(toggleImpl).toggleVisibility(id);
  }
}

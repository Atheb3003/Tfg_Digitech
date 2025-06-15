package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.exception.IdArgumentTypeException;
import com.gestion.application.service.producttype.ProductTypeService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class ProductTypeControllerTest {

  @Mock private ProductTypeService productTypeService;

  @InjectMocks private ProductTypeController controller;

  private ProductTypeRequest request;
  private ProductTypeResponse response;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    request = new ProductTypeRequest();
    request.setTypeProduct("Vitaminas");

    response = new ProductTypeResponse();
    response.setIdProductType(1);
    response.setTypeProduct("Vitaminas");
  }

  @Test
  void testCreateProductType() {
    when(productTypeService.createProductType(request)).thenReturn(response);

    var result = controller.createProductType(request);

    assertEquals(201, result.getStatusCodeValue());
    assertEquals("created", result.getBody().getStatus());
    assertEquals(response, result.getBody().getData());
  }

  @Test
  void testGetAllProductTypes() {
    when(productTypeService.getAllProductTypes()).thenReturn(List.of(response));

    var result = controller.getAllProductTypes();

    assertEquals(200, result.getStatusCodeValue());
    Map<?, ?> body = (Map<?, ?>) result.getBody();
    assertEquals("success", body.get("status"));
    assertEquals(1, ((List<?>) body.get("data")).size());
  }

  @Test
  void testGetProductTypeById_valid() {
    when(productTypeService.getProductTypeById(1)).thenReturn(response);

    var result = controller.getProductTypeById("1");

    assertEquals(200, result.getStatusCodeValue());
    List<?> data = (List<?>) ((Map<?, ?>) result.getBody()).get("data");
    assertEquals(1, data.size());
  }

  @Test
  void testGetProductTypeById_invalid() {
    assertThrows(IdArgumentTypeException.class, () -> controller.getProductTypeById("abc"));
  }

  @Test
  void testUpdateProductType_valid() {
    when(productTypeService.updateProductType(1, request)).thenReturn(response);

    var result = controller.updateProductType("1", request);

    assertEquals(200, result.getStatusCodeValue());
    assertEquals("updated", result.getBody().getStatus());
    assertEquals("Vitaminas", result.getBody().getData().getTypeProduct());
  }

  @Test
  void testUpdateProductType_invalid() {
    assertThrows(IdArgumentTypeException.class, () -> controller.updateProductType("abc", request));
  }

  @Test
  void testDeleteProductType_valid() {
    doNothing().when(productTypeService).deleteProductType(1);

    var result = controller.deleteProductType("1");

    assertEquals(200, result.getStatusCodeValue());
    assertTrue(result.getBody().getMessage().contains("eliminado correctamente"));
  }

  @Test
  void testDeleteProductType_invalid() {
    assertThrows(IdArgumentTypeException.class, () -> controller.deleteProductType("xyz"));
  }
}

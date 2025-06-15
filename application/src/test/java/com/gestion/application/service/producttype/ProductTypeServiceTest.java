package com.gestion.application.service.producttype;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.service.producttype.impl.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTypeServiceTest {

  private CreateProductTypeImpl createImpl;
  private GetAllProductTypesImpl listImpl;
  private GetProductTypeByIdImpl getByIdImpl;
  private UpdateProductTypeImpl updateImpl;
  private DeleteProductTypeImpl deleteImpl;

  private ProductTypeService service;

  @BeforeEach
  void setup() {
    createImpl = mock(CreateProductTypeImpl.class);
    listImpl = mock(GetAllProductTypesImpl.class);
    getByIdImpl = mock(GetProductTypeByIdImpl.class);
    updateImpl = mock(UpdateProductTypeImpl.class);
    deleteImpl = mock(DeleteProductTypeImpl.class);

    service = new ProductTypeService(createImpl, listImpl, getByIdImpl, updateImpl, deleteImpl);
  }

  @Test
  void testCreateProductType() {
    ProductTypeRequest request = new ProductTypeRequest();
    ProductTypeResponse response = new ProductTypeResponse();

    when(createImpl.create(request)).thenReturn(response);

    ProductTypeResponse result = service.createProductType(request);

    assertEquals(response, result);
    verify(createImpl).create(request);
  }

  @Test
  void testGetAllProductTypes() {
    List<ProductTypeResponse> expected = Arrays.asList(new ProductTypeResponse());

    when(listImpl.getAll()).thenReturn(expected);

    List<ProductTypeResponse> result = service.getAllProductTypes();

    assertEquals(expected, result);
    verify(listImpl).getAll();
  }

  @Test
  void testGetProductTypeById() {
    Integer id = 1;
    ProductTypeResponse expected = new ProductTypeResponse();

    when(getByIdImpl.getById(id)).thenReturn(expected);

    ProductTypeResponse result = service.getProductTypeById(id);

    assertEquals(expected, result);
    verify(getByIdImpl).getById(id);
  }

  @Test
  void testUpdateProductType() {
    Integer id = 1;
    ProductTypeRequest request = new ProductTypeRequest();
    ProductTypeResponse expected = new ProductTypeResponse();

    when(updateImpl.update(id, request)).thenReturn(expected);

    ProductTypeResponse result = service.updateProductType(id, request);

    assertEquals(expected, result);
    verify(updateImpl).update(id, request);
  }

  @Test
  void testDeleteProductType() {
    Integer id = 2;

    service.deleteProductType(id);

    verify(deleteImpl).deleteProductType(id);
  }
}

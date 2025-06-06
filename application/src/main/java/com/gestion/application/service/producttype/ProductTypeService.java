package com.gestion.application.service.producttype;

import com.gestion.application.dto.ProductTypeRequest;
import com.gestion.application.dto.ProductTypeResponse;
import com.gestion.application.service.producttype.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

  private final CreateProductTypeImpl createImpl;
  private final GetAllProductTypesImpl listImpl;
  private final GetProductTypeByIdImpl getByIdImpl;
  private final UpdateProductTypeImpl updateImpl;
  private final DeleteProductTypeImpl deleteImpl;

  /** POST /products/type */
  public ProductTypeResponse createProductType(ProductTypeRequest request) {
    return createImpl.create(request);
  }

  /** GET /products/types */
  public List<ProductTypeResponse> getAllProductTypes() {
    return listImpl.getAll();
  }

  /** GET /products/type/{id} */
  public ProductTypeResponse getProductTypeById(Integer id) {
    return getByIdImpl.getById(id);
  }

  /** PUT /products/type/{id} */
  public ProductTypeResponse updateProductType(Integer id, ProductTypeRequest request) {
    return updateImpl.update(id, request);
  }

  /** DELETE /products/type/{id} */
  public void deleteProductType(Integer id) {
    deleteImpl.deleteProductType(id);
  }
}

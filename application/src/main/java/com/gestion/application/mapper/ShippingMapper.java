package com.gestion.application.mapper;

import com.gestion.application.dto.ShippingRequest;
import com.gestion.application.dto.ShippingResponse;
import com.gestion.application.model.Shipping;

public class ShippingMapper {

  public static Shipping toEntity(ShippingRequest request) {
    Shipping shipping = new Shipping();
    shipping.setIdContacto(request.getIdContacto());
    shipping.setFechaEnvio(request.getFechaEnvio());
    shipping.setLocalizador(request.getLocalizador());
    shipping.setPais(request.getPais());
    shipping.setMetodoPago(request.getMetodoPago());
    shipping.setProductIds(request.getProductIds());
    shipping.setInternacional(request.isInternacional());
    shipping.setEnviado(request.isEnviado());
    shipping.setFechaRealEnvio(request.getFechaRealEnvio());
    shipping.setIdUnicoPaciente(request.getIdUnicoPaciente());
    return shipping;
  }

  public static ShippingResponse toResponse(Shipping shipping) {
    ShippingResponse response = new ShippingResponse();
    response.setId(shipping.getId());
    response.setIdContacto(shipping.getIdContacto());
    response.setFechaEnvio(shipping.getFechaEnvio());
    response.setLocalizador(shipping.getLocalizador());
    response.setPais(shipping.getPais());
    response.setMetodoPago(shipping.getMetodoPago());
    response.setProductIds(shipping.getProductIds());
    response.setInternacional(shipping.isInternacional());
    response.setEnviado(shipping.isEnviado());
    response.setFechaRealEnvio(shipping.getFechaRealEnvio());
    response.setIdUnicoPaciente(shipping.getIdUnicoPaciente());
    return response;
  }
}

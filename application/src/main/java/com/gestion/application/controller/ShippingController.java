package com.gestion.application.controller;

import com.gestion.application.dto.MarkAsShippedRequest;
import com.gestion.application.dto.ShippingRequest;
import com.gestion.application.dto.ShippingResponse;
import com.gestion.application.service.shipping.ShippingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shippings")
public class ShippingController {

  private final ShippingService shippingService;

  public ShippingController(ShippingService shippingService) {
    this.shippingService = shippingService;
  }

  @PostMapping
  public ResponseEntity<ShippingResponse> crear(@RequestBody ShippingRequest request) {
    ShippingResponse response = shippingService.crearShipping(request);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Page<ShippingResponse>> obtenerTodos(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(shippingService.obtenerTodos(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ShippingResponse> obtenerPorId(@PathVariable Long id) {
    return shippingService
        .obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    shippingService.eliminar(id);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{id}/marcar-enviado")
  public ResponseEntity<ShippingResponse> marcarComoEnviado(
      @PathVariable Long id, @RequestBody MarkAsShippedRequest request) {

    return shippingService
        .marcarComoEnviado(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}

package com.gestion.application.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.*;
import com.gestion.application.service.shipping.ShippingService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;

class ShippingControllerTest {

  @InjectMocks private ShippingController controller;

  @Mock private ShippingService shippingService;

  private ShippingResponse dummyResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    dummyResponse = new ShippingResponse();
    dummyResponse.setId(1L);
    dummyResponse.setIdContacto(2L);
    dummyResponse.setFechaEnvio(LocalDate.now());
    dummyResponse.setLocalizador("LOC123");
    dummyResponse.setPais("España");
    dummyResponse.setMetodoPago("Tarjeta");
    dummyResponse.setProductIds(List.of(100L, 101L));
    dummyResponse.setInternacional(false);
    dummyResponse.setEnviado(false);
    dummyResponse.setFechaRealEnvio(null);
    dummyResponse.setIdUnicoPaciente("PACIENTE-001");
  }

  @Test
  void testCrear() {
    ShippingRequest request = new ShippingRequest();
    when(shippingService.crearShipping(request)).thenReturn(dummyResponse);

    ResponseEntity<ShippingResponse> response = controller.crear(request);

    assertEquals(201, response.getStatusCodeValue());
    assertEquals(dummyResponse, response.getBody());
  }

  @Test
  void testObtenerTodos() {
    Page<ShippingResponse> page = new PageImpl<>(List.of(dummyResponse));
    when(shippingService.obtenerTodos(PageRequest.of(0, 10))).thenReturn(page);

    ResponseEntity<Page<ShippingResponse>> response = controller.obtenerTodos(0, 10);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(1, response.getBody().getContent().size());
  }

  @Test
  void testObtenerPorId_found() {
    when(shippingService.obtenerPorId(1L)).thenReturn(Optional.of(dummyResponse));

    ResponseEntity<ShippingResponse> response = controller.obtenerPorId(1L);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(dummyResponse, response.getBody());
  }

  @Test
  void testObtenerPorId_notFound() {
    when(shippingService.obtenerPorId(99L)).thenReturn(Optional.empty());

    ResponseEntity<ShippingResponse> response = controller.obtenerPorId(99L);

    assertEquals(404, response.getStatusCodeValue());
  }

  @Test
  void testEliminar() {
    doNothing().when(shippingService).eliminar(1L);

    ResponseEntity<Void> response = controller.eliminar(1L);

    assertEquals(204, response.getStatusCodeValue());
    verify(shippingService).eliminar(1L);
  }

  @Test
  void testMarcarComoEnviado_found() {
    MarkAsShippedRequest request = new MarkAsShippedRequest();
    when(shippingService.marcarComoEnviado(1L, request)).thenReturn(Optional.of(dummyResponse));

    ResponseEntity<ShippingResponse> response = controller.marcarComoEnviado(1L, request);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals(dummyResponse, response.getBody());
  }

  @Test
  void testMarcarComoEnviado_notFound() {
    MarkAsShippedRequest request = new MarkAsShippedRequest();
    when(shippingService.marcarComoEnviado(1L, request)).thenReturn(Optional.empty());

    ResponseEntity<ShippingResponse> response = controller.marcarComoEnviado(1L, request);

    assertEquals(404, response.getStatusCodeValue());
  }
}

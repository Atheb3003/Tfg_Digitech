package com.gestion.application.service.shipping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.gestion.application.dto.MarkAsShippedRequest;
import com.gestion.application.dto.ShippingRequest;
import com.gestion.application.dto.ShippingResponse;
import com.gestion.application.mapper.ShippingMapper;
import com.gestion.application.model.Shipping;
import com.gestion.application.repository.ShippingRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;

class ShippingServiceTest {

  @Mock private ShippingRepository shippingRepository;

  @InjectMocks private ShippingService shippingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void crearShipping_shouldSaveAndReturnResponse() {
    ShippingRequest request = new ShippingRequest();
    request.setIdContacto(1L);
    request.setFechaEnvio(LocalDate.now());
    request.setLocalizador("LOC123");
    request.setPais("Spain");
    request.setMetodoPago("Credit Card");
    request.setProductIds(List.of(10L, 20L));
    request.setInternacional(false);
    request.setEnviado(false);
    request.setFechaRealEnvio(null);
    request.setIdUnicoPaciente("PAC123");

    Shipping shippingEntity = ShippingMapper.toEntity(request);
    shippingEntity.setId(100L); // Simular id generado al guardar

    when(shippingRepository.save(any(Shipping.class))).thenReturn(shippingEntity);

    ShippingResponse response = shippingService.crearShipping(request);

    assertNotNull(response);
    assertEquals(100L, response.getId());
    assertEquals(request.getIdContacto(), response.getIdContacto());
    assertEquals(request.getLocalizador(), response.getLocalizador());
    verify(shippingRepository, times(1)).save(any(Shipping.class));
  }

  @Test
  void obtenerPorId_shouldReturnResponse_whenFound() {
    Shipping shipping = new Shipping();
    shipping.setId(1L);
    shipping.setIdContacto(10L);
    shipping.setLocalizador("LOC001");

    when(shippingRepository.findById(1L)).thenReturn(Optional.of(shipping));

    Optional<ShippingResponse> result = shippingService.obtenerPorId(1L);

    assertTrue(result.isPresent());
    assertEquals(1L, result.get().getId());
    verify(shippingRepository).findById(1L);
  }

  @Test
  void obtenerPorId_shouldReturnEmpty_whenNotFound() {
    when(shippingRepository.findById(2L)).thenReturn(Optional.empty());

    Optional<ShippingResponse> result = shippingService.obtenerPorId(2L);

    assertFalse(result.isPresent());
    verify(shippingRepository).findById(2L);
  }

  @Test
  void eliminar_shouldCallDeleteById() {
    Long idToDelete = 5L;

    doNothing().when(shippingRepository).deleteById(idToDelete);

    shippingService.eliminar(idToDelete);

    verify(shippingRepository).deleteById(idToDelete);
  }

  @Test
  void marcarComoEnviado_shouldUpdateAndReturnResponse_whenShippingExists() {
    Long id = 1L;
    Shipping shipping = new Shipping();
    shipping.setId(id);
    shipping.setEnviado(false);

    MarkAsShippedRequest request = new MarkAsShippedRequest();
    request.setEnviado(true);
    request.setFechaRealEnvio(LocalDate.now());

    when(shippingRepository.findById(id)).thenReturn(Optional.of(shipping));
    when(shippingRepository.save(any(Shipping.class))).thenReturn(shipping);

    Optional<ShippingResponse> responseOpt = shippingService.marcarComoEnviado(id, request);

    assertTrue(responseOpt.isPresent());
    ShippingResponse response = responseOpt.get();
    assertTrue(response.isEnviado());
    assertEquals(request.getFechaRealEnvio(), response.getFechaRealEnvio());
    verify(shippingRepository).findById(id);
    verify(shippingRepository).save(shipping);
  }

  @Test
  void marcarComoEnviado_shouldReturnEmpty_whenShippingNotFound() {
    Long id = 10L;
    MarkAsShippedRequest request = new MarkAsShippedRequest();
    when(shippingRepository.findById(id)).thenReturn(Optional.empty());

    Optional<ShippingResponse> responseOpt = shippingService.marcarComoEnviado(id, request);

    assertFalse(responseOpt.isPresent());
    verify(shippingRepository).findById(id);
    verify(shippingRepository, never()).save(any());
  }
}

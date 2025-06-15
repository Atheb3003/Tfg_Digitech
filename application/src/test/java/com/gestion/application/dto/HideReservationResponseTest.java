package com.gestion.application.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HideReservationResponseTest {

  @Test
  void testConstructorAndGetters() {
    HideReservationResponse response = new HideReservationResponse(123, false);

    assertEquals(123, response.getIdSurgeryReservation());
    assertFalse(response.getIsVisible());
  }

  @Test
  void testSetters() {
    HideReservationResponse response = new HideReservationResponse(456, true);

    response.setIdSurgeryReservation(789);
    response.setIsVisible(false);

    assertEquals(789, response.getIdSurgeryReservation());
    assertFalse(response.getIsVisible());
  }
}

package com.gestion.application.dto;

import java.util.Set;
import lombok.Data;

/** DTO para devolver la información pública de un usuario. */
@Data
public class UserResponse {
  private Long id;
  private String username;
  private Set<String> roles;
}

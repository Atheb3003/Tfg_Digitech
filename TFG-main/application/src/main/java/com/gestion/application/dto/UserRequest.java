package com.gestion.application.dto;

import java.util.List;
import lombok.Data;

@Data
public class UserRequest {
  private String username;
  private String password;
  private List<String> roles;
}

package com.gestion.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.gestion.application.dto.RoleRequest;
import com.gestion.application.dto.RoleResponse;
import com.gestion.application.model.Role;
import org.junit.jupiter.api.Test;

public class RoleMapperTest {

  @Test
  void testToDto() {
    Role role = new Role();
    role.setId(1L);
    role.setName("ADMIN");

    RoleResponse response = RoleMapper.toDto(role);

    assertEquals(1L, response.getId());
    assertEquals("ADMIN", response.getName());
  }

  @Test
  void testToEntity() {
    RoleRequest request = new RoleRequest();
    request.setName("USER");

    Role entity = RoleMapper.toEntity(request);

    assertEquals("USER", entity.getName());
    // Id no se asigna en el mapper
    assertNull(entity.getId());
  }
}

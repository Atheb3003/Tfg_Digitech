package com.gestion.application.service.role;

import com.gestion.application.dto.RoleRequest;
import com.gestion.application.dto.RoleResponse;
import com.gestion.application.service.role.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Fachada de servicio para Role */
@Service
@RequiredArgsConstructor
public class RoleService {

  private final CreateRoleImpl createImpl;
  private final GetAllRolesImpl listImpl;
  private final UpdateRoleImpl updateImpl;
  private final DeleteRoleImpl deleteImpl;

  /** POST /roles */
  public RoleResponse createRole(RoleRequest request) {
    return createImpl.createRole(request);
  }

  /** GET /roles */
  public List<RoleResponse> getAllRoles() {
    return listImpl.getAllRoles();
  }

  /** PUT /roles/{id} */
  public RoleResponse updateRole(Long id, RoleRequest request) {
    return updateImpl.updateRole(id, request);
  }

  /** DELETE /roles/{id} */
  public void deleteRole(Long id) {
    deleteImpl.deleteRole(id);
  }
}

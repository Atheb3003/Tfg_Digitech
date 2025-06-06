package com.gestion.application.controller;

import com.gestion.application.dto.*;
import com.gestion.application.service.role.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

  private final GetAllRolesImpl getAllRoles;
  private final CreateRoleImpl createRole;
  private final UpdateRoleImpl updateRole;
  private final DeleteRoleImpl deleteRole;

  /** GET /api/roles */
  @GetMapping
  public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {
    List<RoleResponse> roles = getAllRoles.getAllRoles();
    return ResponseEntity.ok(new ApiResponse<>("success", roles));
  }

  /** POST /api/roles */
  @PostMapping
  public ResponseEntity<ApiResponse<RoleResponse>> createRole(@RequestBody RoleRequest request) {
    RoleResponse role = createRole.createRole(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("success", role));
  }

  /** PUT /api/roles/{id} */
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<RoleResponse>> updateRole(
      @PathVariable Long id, @RequestBody RoleRequest request) {
    RoleResponse role = updateRole.updateRole(id, request);
    return ResponseEntity.ok(new ApiResponse<>("success", role));
  }

  /** DELETE /api/roles/{id} */
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<SuccessfulDeleteResponse>> deleteRole(@PathVariable Long id) {
    deleteRole.deleteRole(id);
    SuccessfulDeleteResponse response =
        new SuccessfulDeleteResponse("deleted", "Rol eliminado correctamente.");
    return ResponseEntity.ok(new ApiResponse<>("success", response));
  }
}

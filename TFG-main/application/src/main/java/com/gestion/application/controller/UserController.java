package com.gestion.application.controller;

import com.gestion.application.dto.*;
import com.gestion.application.service.user.impl.*;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final CreateUserImpl createUserImpl;
  private final UpdateUserImpl updateUserImpl;
  private final DeleteUserImpl deleteUserImpl;
  private final GetAllUsersImpl getAllUsersImpl;
  private final AssignRolesImpl assignRolesImpl;

  // ✅ Crear usuario
  @PostMapping
  public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody UserRequest request) {
    UserResponse createdUser = createUserImpl.createUser(request);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>("success", createdUser));
  }

  // ✅ Listar todos los usuarios
  @GetMapping
  public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
    List<UserResponse> users = getAllUsersImpl.getAllUsers();
    return ResponseEntity.ok(new ApiResponse<>("success", users));
  }

  // ✅ Actualizar usuario
  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> updateUser(
      @PathVariable Long id, @RequestBody UserRequest request) {
    UserResponse updatedUser = updateUserImpl.updateUser(id, request);
    return ResponseEntity.ok(new ApiResponse<>("success", updatedUser));
  }

  // ✅ Eliminar usuario
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<SuccessfulDeleteResponse>> deleteUser(@PathVariable Long id) {
    deleteUserImpl.deleteUser(id);

    // Crea el objeto de respuesta que incluye el "status" y el "message"
    SuccessfulDeleteResponse response =
        new SuccessfulDeleteResponse(
            "deleted", "Usuario con id " + id + " eliminado correctamente");

    // Devuelve el JSON con el formato deseado
    return ResponseEntity.ok(new ApiResponse<>("deleted", response));
  }

  // ✅ Asignar roles a usuario
  @PatchMapping("/{id}/roles")
  public ResponseEntity<ApiResponse<String>> assignRoles(
      @PathVariable Long id, @RequestBody Map<String, List<String>> body) {
    List<String> roles = body.get("roles");
    assignRolesImpl.assignRoles(id, roles);
    return ResponseEntity.ok(new ApiResponse<>("success", "Roles asignados correctamente."));
  }
}

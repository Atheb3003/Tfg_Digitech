package com.gestion.application.service.user;

import com.gestion.application.dto.UserRequest;
import com.gestion.application.dto.UserResponse;
import com.gestion.application.service.user.impl.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Fachada de servicio para User */
@Service
@RequiredArgsConstructor
public class UserService {

  private final CreateUserImpl createImpl;
  private final GetAllUsersImpl listImpl;
  private final UpdateUserImpl updateImpl;
  private final DeleteUserImpl deleteImpl;
  private final AssignRolesImpl assignImpl;

  /** POST /users */
  public UserResponse createUser(UserRequest request) {
    return createImpl.createUser(request);
  }

  /** GET /users */
  public List<UserResponse> getAllUsers() {
    return listImpl.getAllUsers();
  }

  /** PUT /users/{id} */
  public UserResponse updateUser(Long id, UserRequest request) {
    return updateImpl.updateUser(id, request);
  }

  /** DELETE /users/{id} */
  public void deleteUser(Long id) {
    deleteImpl.deleteUser(id);
  }

  /** POST /users/{id}/roles */
  public void assignRoles(Long id, List<String> roles) {
    assignImpl.assignRoles(id, roles);
  }
}

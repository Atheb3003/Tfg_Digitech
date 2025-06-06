package com.gestion.application.service.user.impl;

import com.gestion.application.exception.UserNotFoundException;
import com.gestion.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserImpl {

  private final UserRepository userRepository;

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException(id);
    }
    userRepository.deleteById(id);
  }
}

package com.gestion.application.service.user.impl;

import com.gestion.application.dto.UserResponse;
import com.gestion.application.mapper.UserMapper;
import com.gestion.application.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllUsersImpl {

  private final UserRepository userRepository;

  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream().map(UserMapper::toDto).collect(Collectors.toList());
  }
}

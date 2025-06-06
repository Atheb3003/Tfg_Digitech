package com.gestion.application.controller;

import com.gestion.application.dto.ApiResponse;
import com.gestion.application.dto.AuthRequest;
import com.gestion.application.dto.AuthResponse;
import com.gestion.application.dto.ErrorDetails;
import com.gestion.application.security.CustomUserDetailsService;
import com.gestion.application.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private CustomUserDetailsService userDetailsService;

  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestBody AuthRequest request, HttpServletRequest servletRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

      UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
      String jwt = jwtUtil.generateToken(userDetails);

      ApiResponse<AuthResponse> response = new ApiResponse<>("success", new AuthResponse(jwt));
      return ResponseEntity.ok(response);

    } catch (BadCredentialsException e) {
      ErrorDetails err =
          new ErrorDetails(
              401,
              "Unauthorized",
              "Usuario o contraseña incorrecta.",
              servletRequest.getRequestURI());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);

    } catch (UsernameNotFoundException e) {
      ErrorDetails err =
          new ErrorDetails(
              404, "Not Found", "Usuario no encontrado.", servletRequest.getRequestURI());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    } catch (Exception e) {
      ErrorDetails err =
          new ErrorDetails(
              500,
              "Internal Server Error",
              "Ocurrió un error inesperado.",
              servletRequest.getRequestURI());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
  }
}

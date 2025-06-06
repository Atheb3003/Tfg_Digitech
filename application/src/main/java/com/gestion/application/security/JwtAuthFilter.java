package com.gestion.application.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Este filtro se ejecuta en cada petición y verifica si hay un JWT válido. Si lo hay, autentica al
 * usuario en el contexto de Spring.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  @Autowired private JwtUtil jwtUtil;

  @Autowired private UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    String username = null;
    String jwt = null;

    // Verificamos si el header contiene un Bearer Token
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      jwt = authHeader.substring(7); // eliminar el prefijo "Bearer "
      username = jwtUtil.extractUsername(jwt); // extraer username del token
    }

    // Si hay username y no hay autenticación activa en el contexto, validamos el token
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      if (jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    // Continuar con la siguiente parte de la cadena
    chain.doFilter(request, response);
  }
}

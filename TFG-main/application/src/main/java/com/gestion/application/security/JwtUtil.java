package com.gestion.application.security;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * JwtUtil se encarga de generar, extraer y validar tokens JWT. Es usado para autenticar usuarios en
 * una API REST sin sesiones.
 */
@Component
public class JwtUtil {

  private static final String SECRET_KEY = "clave-super-secreta-para-firmar-el-token";

  // Extrae el nombre de usuario (username) del token
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  // Extrae la fecha de expiración del token
  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  // Método genérico para extraer cualquier dato del token
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  // Genera un token JWT con el username como sujeto
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  // Crea el token con firma HMAC-SHA256
  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  // Valida que el token no haya expirado y sea del usuario correcto
  public boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  // Verifica si el token ha expirado
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  // Decodifica y verifica la firma del token
  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
  }
}

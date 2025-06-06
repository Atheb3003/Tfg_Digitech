package com.gestion.application.security;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configura Spring Security:
 * - Desactiva CSRF (para APIs REST)
 * - Define qué rutas son públicas
 * - Aplica el filtro JWT a cada petición
 * - Configura el handler de errores 401 y 403
 */
@Configuration
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;
  private final CustomUserDetailsService userDetailsService;
  private final AccessDeniedHandler accessDeniedHandler;
  private final AuthenticationEntryPoint authenticationEntryPoint; // 👈 Añadido

  public SecurityConfig(
          JwtAuthFilter jwtAuthFilter,
          CustomUserDetailsService userDetailsService,
          AccessDeniedHandler accessDeniedHandler,
          AuthenticationEntryPoint authenticationEntryPoint // 👈 Añadido
  ) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.userDetailsService = userDetailsService;
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint; // 👈 Asignado
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    // Endpoints públicos
                    .requestMatchers("/login", "/h2-console/**", "/api/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users").permitAll() // Solo mientras desarrollas, luego quitar
                    .requestMatchers(HttpMethod.POST, "/roles").permitAll() // Solo mientras desarrollas, luego quitar
                    .requestMatchers(HttpMethod.GET, "/patients").permitAll()
                    .requestMatchers(HttpMethod.GET, "/patients").permitAll()
                    .requestMatchers(HttpMethod.POST, "/contacts").permitAll()
                    .requestMatchers(HttpMethod.GET, "/contacts").permitAll()

                    // Endpoints que requieren rol ADMIN
                    .requestMatchers(HttpMethod.POST, "/products/type").hasRole("ADMIN")

                    // Cualquier otra petición requiere autenticación
                    .anyRequest().authenticated()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex
                    .accessDeniedHandler(accessDeniedHandler) // Para 403
                    .authenticationEntryPoint(authenticationEntryPoint) // 👈 Para 401
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

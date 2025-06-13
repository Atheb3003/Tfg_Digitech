package com.gestion.application.security;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configura Spring Security: - Desactiva CSRF (para APIs REST) - Define qué rutas son públicas -
 * Aplica el filtro JWT a cada petición - Configura el handler de errores 401 y 403
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;
  private final CustomUserDetailsService userDetailsService;
  private final AccessDeniedHandler accessDeniedHandler;
  private final AuthenticationEntryPoint authenticationEntryPoint;

  public SecurityConfig(
      JwtAuthFilter jwtAuthFilter,
      CustomUserDetailsService userDetailsService,
      AccessDeniedHandler accessDeniedHandler,
      AuthenticationEntryPoint authenticationEntryPoint) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.userDetailsService = userDetailsService;
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> {})
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            auth ->
                auth
                    // Endpoints públicos
                    .requestMatchers("/login", "/api/auth/**")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/users")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/roles")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/patients")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/contacts")
                    .permitAll()

                    // Rutas protegidas
                    .requestMatchers(HttpMethod.GET, "/contacts/search/{term}")
                    .hasRole("ADMIN") // Cambiado a "authenticated()"
                    .requestMatchers(HttpMethod.GET, "/contacts")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/patients")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/patients/{id}/to-patient")
                    .hasRole("ADMIN")
                    //                    .requestMatchers(HttpMethod.GET,
                    // "/patients/search/{term}")
                    //                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/contacts")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/contacts/{id}")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/contacts/{id}")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/products")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/transactions")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/transactions")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/patients/*/to-patient")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/transactions/contact/{id}")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/consultation-types")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/revisions")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/revisions")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/revisions/visible")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/revisions/contact/*")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/revisions/types/*")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/revisions/*")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/revisions/*/visible")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/revisions/*/invisible")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/revisions/*")
                    .hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/revisions/*")
                    .hasRole("ADMIN")


                    .requestMatchers(HttpMethod.GET, "/surgery-reservations/*")
                    .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/surgery-reservations/**")
                        .hasRole("ADMIN")

                    // Cualquier otra petición requiere autenticación
                    .anyRequest()
                    .authenticated())
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(
            ex ->
                ex.accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(authenticationEntryPoint))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
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

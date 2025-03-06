 package com.Plataforma_Educativa.config;

 import lombok.RequiredArgsConstructor;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
 import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 
 import org.springframework.security.config.http.SessionCreationPolicy;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.SecurityFilterChain;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Plataforma_Educativa.security.JwtAuthenticationFilter;
 
 @Configuration
 @EnableWebSecurity
 @EnableMethodSecurity
 @RequiredArgsConstructor
 public class SecurityConfig {
         //en esta clase se configura los beans y permisos a ciertas url que se tendrán
     private final JwtAuthenticationFilter jwtRequestFilter;
 
 
     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
         return authenticationConfiguration.getAuthenticationManager();
     }
 
     @Bean
     public static PasswordEncoder passwordEncoder() {
         return new BCryptPasswordEncoder();
     }
 
     @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .cors().and()
                 .csrf().disable()
                 .authorizeHttpRequests((authz) -> authz
                         .requestMatchers("/auth/signup", "/auth/signin").permitAll() // Permitir sin autenticación
                         .requestMatchers("/api/v1/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/webjars/**").permitAll() // Incluir rutas adicionales
                         .anyRequest().authenticated() // Cualquier otra solicitud requiere autenticación
                 )
                 .httpBasic() // Deshabilitar autenticación básica
                 .and()
                 .formLogin().disable() // Deshabilitar formulario de login
                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Sin estado

 
         // Añadir filtro JWT
         http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
 
         return http.build();
     }
 
 
 
 }
 
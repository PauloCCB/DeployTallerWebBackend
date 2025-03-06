package com.Plataforma_Educativa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;


@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // Orígenes permitidos
        config.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "https://deploytallerweb-production.up.railway.app/"
        ));
        // Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Encabezados permitidos
        config.setAllowedHeaders(List.of("*"));
        // Permitir el envío de credenciales (cookies, autenticación)
        config.setAllowCredentials(true);
        // Encabezados expuestos (por ejemplo, para leer Set-Cookie)
        config.setExposedHeaders(List.of("Set-Cookie", "JSESSIONID"));
        // Duración de la configuración de CORS (en segundos)
        config.setMaxAge(3600L);

        // Asignar la configuración a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
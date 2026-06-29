package com.example.Catalogo.infrastructure.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilitamos CSRF porque las APIs REST no usan cookies (evita ataques de sesión)
                .csrf(csrf -> csrf.disable())

                // Configuramos una arquitectura Stateless (Sin estado, obligatoria para microservicios)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Reglas de autorización de accesos
                .authorizeHttpRequests(auth -> auth
                        // API First / Clean Code: Permitimos acceso libre total a las consultas del catálogo (GET)
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()

                        // 👇 AQUÍ SE COLOCA LA NUEVA LÍNEA PARA PERMITIR EL POST TEMPORALMENTE
                        //.requestMatchers(HttpMethod.POST, "/api/v1/products/**").permitAll()

                        // Permitimos acceso libre a la consola web de H2 para que el evaluador audite las tablas
                        .requestMatchers("/h2-console/**").permitAll()

                        // Cualquier otra ruta o método (como POST, PUT, DELETE) requerirá autenticación estricta
                        .anyRequest().authenticated()
                );

        // Permitimos que la consola de H2 se renderice correctamente en las vistas de tipo Frame
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}

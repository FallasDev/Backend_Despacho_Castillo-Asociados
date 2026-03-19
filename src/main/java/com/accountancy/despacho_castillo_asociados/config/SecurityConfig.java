package com.accountancy.despacho_castillo_asociados.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth

                        // Rutas públicas
                        .anyRequest().permitAll()
/*                        // Rutas públicas
                        .requestMatchers("/auth/**").permitAll()

                        // Rutas protegidas por permisos
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAuthority("USER_READ")
                        .requestMatchers(HttpMethod.POST, "/users/**").hasAuthority("USER_CREATE")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("USER_DELETE")

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()

 */);


        return http.build();
    }


}
package com.accountancy.despacho_castillo_asociados.config.jwt;

import com.accountancy.despacho_castillo_asociados.infrastructure.security.CustomUserDetailsService;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String token = getTokenFromCookie(request);



        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            final String username = jwtService.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(token, userDetails)) {

                    // Extraemos permisos del token
                    Claims claims = jwtService.extractAllClaims(token);
                    List<String> permissions = claims.get("permissions", List.class);

                    Collection<GrantedAuthority> authorities = permissions != null
                            ? permissions.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
                            : Collections.emptyList();

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    authorities
                            );

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // 🔥 CLAVE: token expirado = NO autenticado, pero no error
            // No haces nada, dejas continuar el flujo
        } catch (Exception e) {
            // Opcional: loggear otros errores
            System.out.println("JWT error: " + e.getMessage());
        }
        
        filterChain.doFilter(request, response);
    }

    private String getTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if ("auth_token".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
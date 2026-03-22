package com.accountancy.despacho_castillo_asociados.application.service.Auth;

import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.ILoginUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.IRefreshTokenUseCase;
import com.accountancy.despacho_castillo_asociados.config.jwt.JwtService;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginResponse;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.security.CustomUserDetailsService;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements ILoginUseCase, IRefreshTokenUseCase {

    private final UserRepository userRepository;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                      CustomUserDetailsService userDetailsService,
                      JwtService jwtService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public LoginResponse execute(LoginRequest request) throws BadRequestException {
        // Buscar usuario por email
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email o contraseña incorrectos"));

        // Validar contraseña (comparación directa)
        if (!request.getPassword().equals(user.getPassword())) {
            throw new BadRequestException("Email o contraseña incorrectos");
        }

        // Validar que el usuario está activo
        if (!user.isActive()) {
            throw new BadRequestException("El usuario está inactivo");
        }

        // Cargar detalles del usuario para obtener autoridades
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        // Generar token JWT
        String token = jwtService.generateToken(userDetails);

        return new LoginResponse(token, 3600, user.getId() ,user.getRole().getName(), user.getName());
    }

    @Override
    @Transactional
    public LoginResponse execute(String token) {
        try {
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.isTokenValid(token, userDetails)) {
                String newToken = jwtService.generateToken(userDetails);
                User user = userRepository.findByEmail(username).orElse(null);

                if (user != null) {
                    return new LoginResponse(newToken, 3600, user.getId(), user.getRole().getName(), user.getName());
                }
            }

            throw new BadRequestException("Token inválido o expirado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("No se pudo renovar el token");
        }
    }
}


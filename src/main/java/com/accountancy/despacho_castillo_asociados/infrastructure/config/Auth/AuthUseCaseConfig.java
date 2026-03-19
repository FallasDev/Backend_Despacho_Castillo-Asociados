package com.accountancy.despacho_castillo_asociados.infrastructure.config.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Auth.AuthService;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.ILoginUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.IRefreshTokenUseCase;
import com.accountancy.despacho_castillo_asociados.config.jwt.JwtService;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthUseCaseConfig {

    @Bean
    public AuthService authService(UserRepository userRepository,
                                    CustomUserDetailsService userDetailsService,
                                    JwtService jwtService) {
        return new AuthService(userRepository, userDetailsService, jwtService);
    }

    @Bean
    public ILoginUseCase loginUseCase(AuthService authService) {
        return authService;
    }


    @Bean
    public IRefreshTokenUseCase refreshTokenUseCase(AuthService authService) {
        return authService;
    }
}


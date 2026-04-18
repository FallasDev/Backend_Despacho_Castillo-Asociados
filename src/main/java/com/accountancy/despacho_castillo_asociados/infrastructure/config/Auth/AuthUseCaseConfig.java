package com.accountancy.despacho_castillo_asociados.infrastructure.config.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Auth.AuthService;
import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.ILoginUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.IRefreshTokenUseCase;
import com.accountancy.despacho_castillo_asociados.config.jwt.JwtService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.RefreshToken.RefreshTokenRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode.VerificationCodeRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfig {

    @Bean
    public AuthService authService(UserRepository userRepository,
                                   CustomUserDetailsService userDetailsService,
                                   JwtService jwtService,
                                   ClientRepository clientRepository,
                                   EmailService emailService,
                                   VerificationCodeRepository verificationCodeRepository,
                                   RefreshTokenRepository refreshTokenRepository) {
        return new AuthService(userRepository, clientRepository, userDetailsService, jwtService, emailService,
                verificationCodeRepository, refreshTokenRepository);
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


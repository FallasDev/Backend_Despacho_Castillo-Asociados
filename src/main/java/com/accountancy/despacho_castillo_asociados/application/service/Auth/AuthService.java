package com.accountancy.despacho_castillo_asociados.application.service.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.IFindCodeByEmail;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.ILoginClientUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.ILoginUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.IRefreshTokenUseCase;
import com.accountancy.despacho_castillo_asociados.config.jwt.JwtService;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginResponse;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode.VerificationCodeRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.security.CustomUserDetailsService;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements ILoginUseCase, IRefreshTokenUseCase, ILoginClientUseCase, IFindCodeByEmail {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, ClientRepository clientRepository,
                       CustomUserDetailsService userDetailsService,
                       JwtService jwtService, EmailService emailService,
                       VerificationCodeRepository verificationCodeRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.emailService = emailService;
            this.verificationCodeRepository = verificationCodeRepository;
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

    @Override
    @Transactional
    public LoginResponse executeClient(LoginRequest request) throws BadRequestException, MessagingException {
        Client client = clientRepository.findByEmailAndActive(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email o contraseña incorrectos"));

        if (!request.getPassword().equals(client.getPassword())) {
            throw new BadRequestException("Email o contraseña incorrectos");
        }

        if (!client.isActive()) {
            throw new BadRequestException("El cliente está inactivo");
        }

        if (!client.isEnabled()) {
            throw new BadRequestException("El cliente no ha verificado su cuenta");
        }

        UserDetails userDetails = userDetailsService.loadClientByUserName(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        emailService.sendHtmlEmail(
            client.getEmail(),
            "Nuevo inicio de sesión",
            "<p>Hola " + client.getName() + ",</p>" +
            "<p>Se ha detectado un nuevo inicio de sesión en tu cuenta. Si no fuiste tú, por favor contacta con nuestro soporte.</p>" +
            "<p>Si fuiste tú, puedes ignorar este mensaje.</p>" +
            "<p>Saludos,<br/>Despacho Castillo & Asociados</p>"
        );

        return new LoginResponse(token, 3600, client.getId(), "CLIENT", client.getName());

    }

    @Override
    public Optional<VerificationCode> verifyCode(String email, String code) {
        Optional<VerificationCode> optCode = verificationCodeRepository.findByEmailAndCodeAndUsedFalse(
                email, code
        );

        if (optCode.isEmpty()) {
            throw new BadRequestException("Código inválido o ya utilizado");
        }

        VerificationCode opt = optCode.get();

        if (opt.isExpired()) {
            throw new BadRequestException("El código ha expirado");
        }

        Client client = clientRepository.findByEmailAndActive(email)
                .orElseThrow(() -> new BadRequestException("Cliente no encontrado"));

        clientRepository.enabledClient(client.getId());

        verificationCodeRepository.MarkAsUsed(opt.getId());

        return Optional.of(opt);
    }
}


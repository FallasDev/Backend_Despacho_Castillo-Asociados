package com.accountancy.despacho_castillo_asociados.application.service.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.application.usecase.Auth.*;
import com.accountancy.despacho_castillo_asociados.config.jwt.JwtService;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginResponse;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.RefreshToken;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserSummary;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.RefreshToken.RefreshTokenRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode.VerificationCodeRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.security.CustomUserDetailsService;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import com.accountancy.despacho_castillo_asociados.shared.utils.GenerateOtp;
import com.accountancy.despacho_castillo_asociados.shared.utils.GenerateRefreshToken;
import com.accountancy.despacho_castillo_asociados.shared.utils.HtmlContent;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService implements ILoginUseCase, IRefreshTokenUseCase, ILoginClientUseCase, IFindCodeByEmail,
        IResendCode, IGetCurrentUser, IGetCurrentClient ,ICreateRefreshToken, IValidateRefreshToken {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, ClientRepository clientRepository,
                       CustomUserDetailsService userDetailsService,
                       JwtService jwtService, EmailService emailService,
                       VerificationCodeRepository verificationCodeRepository, @Qualifier("refreshTokenRepository") RefreshTokenRepository repository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.emailService = emailService;
            this.verificationCodeRepository = verificationCodeRepository;
        this.refreshTokenRepository = repository;
    }

    @Override
    @Transactional
    public LoginResponse execute(LoginRequest request) throws BadRequestException {

        // Buscar usuario por email con role eager-loaded
        var user = userRepository.findByEmailWithRole(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email o contraseña incorrectos"));

        // Validar contraseña (comparación directa)
        if (!request.getPassword().equals(user.getPassword())) {
            throw new BadRequestException("Email o contraseña incorrectos");
        }

        // Validar que el usuario está activo
        if (!user.isActive()) {
            throw new BadRequestException("El usuario está inactivo");
        }

        // Inicializar nombre del role dentro de la transacción
        String roleName = user.getRole() != null ? user.getRole().getName() : null;

        // Cargar detalles del usuario para obtener autoridades
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        // Generar token JWT
        String token = jwtService.generateToken(userDetails);
        String refreshToken = createRefreshToken(user).getToken();

        return new LoginResponse(token, 3600, user.getId(), roleName, user.getName(), refreshToken);
    }

    @Transactional
    public LoginResponse refresh(String refreshToken) {

        RefreshToken rt = validateRefreshToken(refreshToken);

        User user = rt.getUser();

        String roleName = user.getRole() != null ? user.getRole().getName() : null;

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        String newAccessToken = jwtService.generateToken(userDetails);

        rt.setRevoked(true);
        refreshTokenRepository.save(rt);

        RefreshToken newRefreshToken = createRefreshToken(user);

        return new LoginResponse(
                newAccessToken,
                3600,
                user.getId(),
                roleName,
                user.getName(),
                newRefreshToken.getToken()
        );
    }


    @Override
    @Transactional
    public LoginResponse executeClient(LoginRequest request) throws BadRequestException, MessagingException {
        Client client = clientRepository.findByEmailAndActive(request.getEmail())
                .orElseThrow(() -> new BadRequestException("Email o contraseña incorrectos"));

        System.out.println("Contraseña ingresada: " + request.getPassword() + ", Contraseña almacenada: " + client.getPassword());

        if (!request.getPassword().equals(client.getPassword())) {
            throw new BadRequestException("Email o contraseña incorrectos");
        }

        if (!client.isActive()) {
            throw new BadRequestException("El cliente está inactivo");
        }

        if (!client.isEnabled()) {
            throw new BadRequestException("El cliente no ha verificado su cuenta");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println("UserDetails cargados para cliente: " + userDetails.getUsername() + ", Authorities: " + userDetails.getAuthorities());

        String token = jwtService.generateToken(userDetails);
        String refreshToken = createClientRefreshToken(clientRepository.findByEmailAndActive(request.getEmail()).orElseThrow(() -> new BadRequestException("Usuario no encontrado"))).getToken();

        emailService.sendHtmlEmail(
            client.getEmail(),
            "Nuevo inicio de sesión",
            "<p>Hola " + client.getName() + ",</p>" +
            "<p>Se ha detectado un nuevo inicio de sesión en tu cuenta. Si no fuiste tú, por favor contacta con nuestro soporte.</p>" +
            "<p>Si fuiste tú, puedes ignorar este mensaje.</p>" +
            "<p>Saludos,<br/>Despacho Castillo & Asociados</p>"
        );

        return new LoginResponse(token, 3600, client.getId(), "CLIENT", client.getName(), refreshToken);

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

    @Override
    public void resendCode(String email) throws MessagingException {

        Client client = clientRepository.findByEmailAndActive(email)
                .orElseThrow(() -> new BadRequestException("Cliente no encontrado"));


        if (client.isEnabled()) {
            throw new BadRequestException("El cliente ya ha verificado su cuenta");
        }

        VerificationCode existingCode = verificationCodeRepository.findLastCodeByEmail(client.getEmail()).orElse(null);

        boolean isCodeAlreadySent = existingCode != null && existingCode.getCreatedAt().isAfter(LocalDateTime.now().minusMinutes(2));

        if (isCodeAlreadySent) {
            throw new BadRequestException("Ya se ha enviado un código de verificación recientemente. Por favor, espere 2 minutos antes de solicitar otro.");
        }

        String otp = GenerateOtp.execute();


        String subject = "Código de verificación para tu cuenta";
        String body = new HtmlContent().generateVerificationEmail(client.getName(), otp);

        emailService.sendHtmlEmail(client.getEmail(), subject, body);


        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(client.getEmail());
        verificationCode.setCode(otp);
        verificationCode.setExpiryDate(java.time.LocalDateTime.now().plusMinutes(15));


        verificationCodeRepository.save(verificationCode);
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        System.out.println("Intentando obtener el usuario actual del contexto de seguridad...");
        String username = jwtService.extractUsernameFromContext();

        if (username == null) {
            throw new BadRequestException("Usuario no autenticado");
        }

        return userRepository.findByEmailWithRole(username)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado"));
    }

    // Nuevo: devolver resumen seguro para /me (solo campos necesarios)
    @Transactional
    public UserSummary getCurrentUserSummary() {
        System.out.println("Intentando obtener resumen del usuario actual...");
        String username = jwtService.extractUsernameFromContext();

        if (username == null) {
            throw new BadRequestException("Usuario no autenticado");
        }

        User user = userRepository.findByEmailWithRole(username)
                .orElseThrow(() -> new BadRequestException("Usuario no encontrado"));

        String roleName = user.getRole() != null ? user.getRole().getName() : null;

        return new UserSummary(user.getId(), user.getName(), user.getEmail(), roleName);
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(GenerateRefreshToken.execute());
        token.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
        token.setRevoked(false);

        return refreshTokenRepository.save(token);
    }

    @Override
    public RefreshToken createClientRefreshToken(Client client) {
        RefreshToken token = new RefreshToken();
        token.setClient(client);
        token.setToken(GenerateRefreshToken.execute());
        token.setExpiryDate(Instant.now().plus(7, ChronoUnit.DAYS));
        token.setRevoked(false);

        return refreshTokenRepository.save(token);
    }

    @Override
    public RefreshToken validateRefreshToken(String token) {
        RefreshToken rt = Optional.ofNullable(refreshTokenRepository.findByToken(token))
                .orElseThrow(() -> new BadRequestException("Refresh token inválido"));

        if (rt.isRevoked() || rt.getExpiryDate().isBefore(Instant.now())) {
            throw new BadRequestException("Expired or revoked refresh token");
        }

        return rt;
    }

    @Override
    public Client getCurrentClient() {

        String username = jwtService.extractClientFromContext();

        System.out.println("Username extraído del contexto: " + username);

        if (username == null) {
            throw new BadRequestException("Cliente no autenticado");
        }

        return clientRepository.findByEmailAndActive(username)
                .orElseThrow(() -> new BadRequestException("Cliente no encontrado"));

    }
}





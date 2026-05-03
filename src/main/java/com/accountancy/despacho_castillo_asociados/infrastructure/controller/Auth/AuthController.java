package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Auth.AuthService;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserSummary;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.execute(request);

        ResponseCookie authCookie = createAuthCookie(response.getToken());
        ResponseCookie refreshCookie = createRefreshCookie(response.getRefreshToken());

        return ResponseEntity.ok()
                .headers(headers -> {
                    headers.add(HttpHeaders.SET_COOKIE, authCookie.toString());
                    headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
                })
                .body(new ApiResponse<>(true, "Login exitoso", null));
    }

    @GetMapping("/me")
    @Transactional
    public ResponseEntity<ApiResponse<UserSummary>> getCurrentUser() {
        System.out.println("Obteniendo usuario autenticado...");
            UserSummary user = authService.getCurrentUserSummary();
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Usuario autenticado", user));
    }

    @GetMapping("/me/client")
    @Transactional
    public ResponseEntity<ApiResponse<Client>> getCurrentClient() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Autenticación actual: " + authentication);
        Client client = authService.getCurrentClient();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Cliente autenticado", client));
    }



    @PostMapping("/login-client")
    public ResponseEntity<ApiResponse<LoginResponse>> loginClient(@RequestBody LoginRequest request) throws MessagingException {

        System.out.println("Intentando login para cliente con email: " + request.getEmail());

        LoginResponse response = authService.executeClient(request);

        ResponseCookie authCookie = createAuthCookie(response.getToken());
        ResponseCookie refreshCookie = createRefreshCookie(response.getRefreshToken());

        return ResponseEntity.ok()
                .headers(headers -> {
                    headers.add(HttpHeaders.SET_COOKIE, authCookie.toString());
                    headers.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());
                })
                .body(new ApiResponse<>(true, "Login exitoso", null));
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue("refresh_token") String refreshToken) {

        LoginResponse response = authService.refresh(refreshToken);

        ResponseCookie authCookie = createAuthCookie(response.getToken());
        ResponseCookie newRefreshCookie = createRefreshCookie(response.getRefreshToken());

        return ResponseEntity.ok()
                .headers(headers -> {
                    headers.add(HttpHeaders.SET_COOKIE, authCookie.toString());
                    headers.add(HttpHeaders.SET_COOKIE, newRefreshCookie.toString());
                })
                .body(new ApiResponse<>(true, "Token renovado", null));
    }

    @NonNull
    private ResponseCookie createAuthCookie(String token) {
        return ResponseCookie.from("auth_token", token)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(15 * 60)
                .sameSite("Lax")
                .build();
    }

    @NonNull
    private ResponseCookie createRefreshCookie(String refreshToken) {
        return ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/auth")
                .maxAge(7 * 24 * 60 * 60)
                .sameSite("Strict")
                .build();
    }



    @GetMapping("/resend-code")
    public ResponseEntity<?> resendCode(@RequestParam String email) throws MessagingException {

        authService.resendCode(email);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Código de verificación reenviado exitosamente", null)
        );

    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyCode(@RequestBody VerificationCodeRequest request) {

        Optional<VerificationCode> verificationCode = authService.verifyCode(request.getEmail(), request.getCode());

        if (verificationCode.isPresent()) {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Código verificado exitosamente", null)
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, "Código de verificación inválido o expirado", null));
        }

    }

}

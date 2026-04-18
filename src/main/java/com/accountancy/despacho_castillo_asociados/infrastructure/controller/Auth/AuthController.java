package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Auth.AuthService;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.*;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.mail.MessagingException;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;

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
    public ResponseEntity<ApiResponse<User>> getCurrentUser() {

            User user = authService.getCurrentUser();
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Usuario autenticado", user));

    }



    @PostMapping("/login-client")
    public ResponseEntity<ApiResponse<LoginResponse>> loginClient(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.executeClient(request);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Login exitoso", response)
            );
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Error interno del servidor", null));
        }
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
}


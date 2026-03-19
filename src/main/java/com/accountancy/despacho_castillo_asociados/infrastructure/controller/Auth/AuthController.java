package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Auth;

import com.accountancy.despacho_castillo_asociados.application.service.Auth.AuthService;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginResponse;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.execute(request);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Login exitoso", response)
            );
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(
            @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            LoginResponse response = authService.execute(token);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Token renovado exitosamente", response)
            );
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}


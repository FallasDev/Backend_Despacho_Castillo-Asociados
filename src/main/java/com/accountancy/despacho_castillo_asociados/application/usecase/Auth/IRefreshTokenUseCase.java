package com.accountancy.despacho_castillo_asociados.application.usecase.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginResponse;

public interface IRefreshTokenUseCase {
    LoginResponse refresh(String token);
}


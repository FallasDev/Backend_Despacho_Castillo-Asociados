package com.accountancy.despacho_castillo_asociados.application.usecase.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.LoginResponse;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public interface ILoginClientUseCase {
    LoginResponse execute(LoginRequest loginRequest) throws BadRequestException;
}

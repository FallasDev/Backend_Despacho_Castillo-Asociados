package com.accountancy.despacho_castillo_asociados.application.usecase.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;

import java.util.Optional;

public interface IFindCodeByEmail {

    Optional<VerificationCode> verifyCode(String email, String code);

}

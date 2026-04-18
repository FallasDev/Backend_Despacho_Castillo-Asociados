package com.accountancy.despacho_castillo_asociados.application.usecase.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.RefreshToken;

public interface IValidateRefreshToken {

    RefreshToken validateRefreshToken(String token);

}

package com.accountancy.despacho_castillo_asociados.application.usecase.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.RefreshToken;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;

public interface ICreateRefreshToken {

    RefreshToken createRefreshToken(User user);
    RefreshToken createClientRefreshToken(Client client);

}

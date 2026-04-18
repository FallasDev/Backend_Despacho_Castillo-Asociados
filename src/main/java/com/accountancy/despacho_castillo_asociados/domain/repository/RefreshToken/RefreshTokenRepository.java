package com.accountancy.despacho_castillo_asociados.domain.repository.RefreshToken;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.RefreshToken;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.RefreshToken.RefreshTokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken refreshToken);
    RefreshToken findByToken(String token);

}

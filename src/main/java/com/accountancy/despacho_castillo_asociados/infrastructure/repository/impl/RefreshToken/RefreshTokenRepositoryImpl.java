package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.RefreshToken;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.RefreshToken;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.RefreshToken.RefreshTokenRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.RefreshToken.RefreshTokenEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.RefreshToken.RefreshTokenJPARepository;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {


    private final RefreshTokenJPARepository refreshTokenJPARepository;

    public RefreshTokenRepositoryImpl(RefreshTokenJPARepository refreshTokenJPARepository) {
        this.refreshTokenJPARepository = refreshTokenJPARepository;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setToken(refreshToken.getToken());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(refreshToken.getUser().getId());
        refreshTokenEntity.setUser(userEntity);
        refreshTokenEntity.setExpiryDate(refreshToken.getExpiryDate());
        refreshTokenEntity.setRevoked(refreshToken.isRevoked());

        RefreshTokenEntity savedEntity = refreshTokenJPARepository.save(refreshTokenEntity);

        RefreshToken savedRefreshToken = new RefreshToken();
        savedRefreshToken.setId(savedEntity.getId());
        savedRefreshToken.setToken(savedEntity.getToken());
        savedRefreshToken.setUser(refreshToken.getUser());
        savedRefreshToken.setExpiryDate(savedEntity.getExpiryDate());
        savedRefreshToken.setRevoked(savedEntity.isRevoked());
        return savedRefreshToken;




    }

    @Override
    public RefreshToken findByToken(String token) {

        RefreshTokenEntity refreshTokenEntity = refreshTokenJPARepository.findByToken(token).getFirst();

        if (refreshTokenEntity == null) {
            return null;
        }

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setId(refreshTokenEntity.getId());
        refreshToken.setToken(refreshTokenEntity.getToken());
        refreshToken.setExpiryDate(refreshTokenEntity.getExpiryDate());
        refreshToken.setRevoked(refreshTokenEntity.isRevoked());

        return refreshToken;

    }
}

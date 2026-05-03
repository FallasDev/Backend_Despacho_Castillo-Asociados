package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.RefreshToken;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.RefreshToken;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.RefreshToken.RefreshTokenRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.RefreshToken.RefreshTokenEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.RefreshToken.RefreshTokenJPARepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

        if (refreshToken.getUser() != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(refreshToken.getUser().getId());
            refreshTokenEntity.setUser(userEntity);
        }

        if (refreshToken.getClient() != null) {
            ClientEntity clientEntity = new ClientEntity();
            clientEntity.setId(refreshToken.getClient().getId());
            refreshTokenEntity.setClient(clientEntity);
        }

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

        Optional<RefreshTokenEntity> opt = refreshTokenJPARepository.findByToken(token);
        if (opt.isEmpty()) {
            return null;
        }
        RefreshTokenEntity e = opt.get();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setId(e.getId());
        refreshToken.setToken(e.getToken());
        refreshToken.setExpiryDate(e.getExpiryDate());
        refreshToken.setRevoked(e.isRevoked());

        if (e.getUser() != null) {
            User user = new User();
            user.setId(e.getUser().getId());
            // mapear email para que el AuthService pueda recargar UserDetails
            user.setEmail(e.getUser().getEmail());
            refreshToken.setUser(user);
        }

        if (e.getClient() != null) {
            Client client = new Client();
            client.setId(e.getClient().getId());
            client.setEmail(e.getClient().getEmail());
            refreshToken.setClient(client);
        }

        return refreshToken;

    }
}

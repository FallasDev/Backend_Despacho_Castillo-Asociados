package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.RefreshToken;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.RefreshToken.RefreshTokenEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Report.JPAReportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefreshTokenJPARepository extends JpaRepository<RefreshTokenEntity, Long> {

    List<RefreshTokenEntity> findByToken(String token);


}

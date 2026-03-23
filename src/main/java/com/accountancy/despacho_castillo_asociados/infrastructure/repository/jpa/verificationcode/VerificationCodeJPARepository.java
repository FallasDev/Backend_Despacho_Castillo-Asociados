package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.verificationcode;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.verificationcode.VerificationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeJPARepository extends JpaRepository<VerificationCodeEntity, Integer> {
    VerificationCodeEntity findByEmailAndCodeAndUsedFalse(String email, String code);
}

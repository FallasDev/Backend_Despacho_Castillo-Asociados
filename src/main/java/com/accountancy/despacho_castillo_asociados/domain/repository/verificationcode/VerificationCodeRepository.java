package com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;

import java.util.Optional;

public interface VerificationCodeRepository {

    Optional<VerificationCode> findByEmailAndCodeAndUsedFalse(String email, String code);

    VerificationCode save(VerificationCode verificationCode);

    void MarkAsUsed(int id);
}

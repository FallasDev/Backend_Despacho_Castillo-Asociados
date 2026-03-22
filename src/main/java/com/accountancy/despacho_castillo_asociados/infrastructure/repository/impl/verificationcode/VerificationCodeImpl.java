package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.verificationcode;

import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCodeRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode.VerificationCodeRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.verificationcode.VerificationCodeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.verificationcode.VerificationCodeJPARepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class VerificationCodeImpl implements VerificationCodeRepository  {

    private final VerificationCodeJPARepository verificationCodeJpaRepository;

    public VerificationCodeImpl(VerificationCodeJPARepository verificationCodeJpaRepository) {
        this.verificationCodeJpaRepository = verificationCodeJpaRepository;
    }

    @Override
    public Optional<VerificationCode> findByEmailAndCodeAndUsedFalse(String email, String code) {

        VerificationCodeEntity verificationCode = verificationCodeJpaRepository.findByEmailAndCodeAndUsedFalse(email, code);


        if (verificationCode == null) {
            return Optional.empty();
        }

        return Optional.of(new VerificationCode(
                Math.toIntExact(verificationCode.getId()),
                verificationCode.getEmail(),
                verificationCode.getCode(),
                verificationCode.getExpiryDate(),
                verificationCode.isUsed(),
                verificationCode.getCreatedAt()
        ));

    }

    @Override
    public VerificationCode save(VerificationCode verificationCode) {

        VerificationCodeEntity entity = new VerificationCodeEntity();
        entity.setEmail(verificationCode.getEmail());
        entity.setCode(verificationCode.getCode());
        entity.setExpiryDate(verificationCode.getExpiryDate());
        entity.setUsed(verificationCode.isUsed());
        entity.setCreatedAt(LocalDateTime.now());

        VerificationCodeEntity savedEntity = verificationCodeJpaRepository.save(entity);

        return new VerificationCode(
                Math.toIntExact(savedEntity.getId()),
                savedEntity.getEmail(),
                savedEntity.getCode(),
                savedEntity.getExpiryDate(),
                savedEntity.isUsed(),
                savedEntity.getCreatedAt()
        );

    }

    @Override
    public void MarkAsUsed(int id) {

        VerificationCodeEntity entity = verificationCodeJpaRepository.findById((int) id).orElseThrow(() -> new BadRequestException("Verification code not found"));

        entity.setUsed(true);

        verificationCodeJpaRepository.save(entity);

    }
}

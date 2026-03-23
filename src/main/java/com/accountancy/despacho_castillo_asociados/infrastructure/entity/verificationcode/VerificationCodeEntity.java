package com.accountancy.despacho_castillo_asociados.infrastructure.entity.verificationcode;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification_codes")
@Getter
@Setter
public class VerificationCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;           // o userId si prefieres

    @Column(nullable = false)
    private String code;            // "483920" o "A7B9K2"

    @Column(nullable = false)
    private LocalDateTime expiryDate;

    @Column(nullable = false)
    private boolean used = false;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }
}
package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.User;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAUserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByNameAndIsActiveTrue(String name);
    Optional<UserEntity> findByNameAndIsActiveFalse(String name);
    Optional<UserEntity> findBySurname(String surname);
    Optional<UserEntity> findBySurnameAndIsActiveTrue(String surname);
    Optional<UserEntity> findBySurnameAndIsActiveFalse(String surname);
    Optional<UserEntity> findByEmail(String email);
    Page<UserEntity> findAll(Pageable pageable);
}

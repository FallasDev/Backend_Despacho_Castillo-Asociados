package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Client;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByName(String name);
    Optional<ClientEntity> findByNameAndIsActiveTrue(String name);
    Optional<ClientEntity> findByNameAndIsActiveFalse(String name);
    Optional<ClientEntity> findBySurname(String surname);
    Optional<ClientEntity> findBySurnameAndIsActiveTrue(String surname);
    Optional<ClientEntity> findBySurnameAndIsActiveFalse(String surname);
    List<ClientEntity> findByEmailAndIsActive(String email, boolean isActive);
    Page<ClientEntity> findAll(Pageable pageable);
}


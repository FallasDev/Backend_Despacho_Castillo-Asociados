package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.User;

import aj.org.objectweb.asm.commons.Remapper;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select u from UserEntity u left join fetch u.role where u.email = :email")
    Optional<UserEntity> findByEmailWithRole(@Param("email") String email);

    @Query("select u from UserEntity u left join fetch u.role  where u.id = :id")
    Optional<UserEntity> findByIdWithRole(@Param("id") int id);

    // Nuevo: cargar role y sus permisos y permisos -> permission para evitar LazyInitializationException
    @Query("select distinct u from UserEntity u " +
            "left join fetch u.role r " +
            "left join fetch r.permissions pr " +
            "left join fetch pr.permission p " +
            "where u.email = :email")
    Optional<UserEntity> findByEmailWithRoleAndPermissions(@Param("email") String email);

    Optional<UserEntity> findByEmailAndIsActiveTrue(String email, boolean isActive);

    Page<UserEntity> findByIsActiveIsTrue(boolean isActive, Pageable pageable);
}

package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.User.JPAUserRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JPAUserRepository jpaUserRepository;

    public UserRepositoryImpl(JPAUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User create(UserRequest userRequest) {
        UserEntity entity = new UserEntity(
                userRequest.getName(),
                userRequest.getSuername(),
                userRequest.getPhotoProfileUrl(),
                userRequest.getPhoneNumber(),
                userRequest.getPerosnalId(),
                userRequest.getEmail(),
                userRequest.getRole() != null ?
                    new RoleEntity(userRequest.getRole().getId(), "", "", new java.util.ArrayList<>(), true) : null,
                userRequest.getPassword(),
                userRequest.getAddress(),
                true
        );

        UserEntity saved = jpaUserRepository.save(entity);
        return getUserFromEntity(saved);
    }

    @Override
    public User update(UserRequest userRequest, int id) {
        Optional<UserEntity> existing = jpaUserRepository.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        UserEntity entity = existing.get();
        entity.setName(userRequest.getName());
        entity.setSuername(userRequest.getSuername());
        entity.setPhotoProfileUrl(userRequest.getPhotoProfileUrl());
        entity.setPhoneNumber(userRequest.getPhoneNumber());
        entity.setPerosnalId(userRequest.getPerosnalId());
        entity.setEmail(userRequest.getEmail());
        if (userRequest.getRole() != null) {
            entity.setRole(new RoleEntity(userRequest.getRole().getId(), "", "", new java.util.ArrayList<>(), true));
        }
        entity.setPassword(userRequest.getPassword());
        entity.setAddress(userRequest.getAddress());

        UserEntity updated = jpaUserRepository.save(entity);
        return getUserFromEntity(updated);
    }

    @Override
    public void activate(int id) {
        Optional<UserEntity> existing = jpaUserRepository.findById(id);
        if (existing.isPresent()) {
            UserEntity entity = existing.get();
            entity.setActive(true);
            jpaUserRepository.save(entity);
        }
    }

    @Override
    public boolean deactivate(int id) {
        Optional<UserEntity> existing = jpaUserRepository.findById(id);
        if (existing.isPresent()) {
            UserEntity entity = existing.get();
            entity.setActive(false);
            jpaUserRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> findById(int id) {
        return jpaUserRepository.findById(id)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> fintByName(String name) {
        return jpaUserRepository.findByName(name)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> fintByNameAndIsActive(String name) {
        return jpaUserRepository.findByNameAndIsActiveTrue(name)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> fintByNameAndIsInactive(String name) {
        return jpaUserRepository.findByNameAndIsActiveFalse(name)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> fintBySurname(String surname) {
        return jpaUserRepository.findBySuername(surname)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> fintBySurnameAndIsActive(String surname) {
        return jpaUserRepository.findBySuernameAndIsActiveTrue(surname)
                .map(this::getUserFromEntity);
    }

    @Override
    public Optional<User> fintBySurnameAndIsInactive(String surname) {
        return jpaUserRepository.findBySuernameAndIsActiveFalse(surname)
                .map(this::getUserFromEntity);
    }

    @Override
    public PageResult<User> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<UserEntity> entityPage = jpaUserRepository.findAll(pageable);

        List<UserEntity> users = entityPage.getContent();

        return new PageResult<>(
                users.stream()
                        .map(this::getUserFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @NonNull
    private User getUserFromEntity(@NonNull UserEntity entity) {
        com.accountancy.despacho_castillo_asociados.domain.model.Role.Role domainRole = null;
        if (entity.getRole() != null) {
            domainRole = new com.accountancy.despacho_castillo_asociados.domain.model.Role.Role(
                    entity.getRole().getId(),
                    entity.getRole().getName(),
                    entity.getRole().getDescription(),
                    new java.util.ArrayList<>(),
                    entity.getRole().isActive()
            );
        }

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getSuername(),
                entity.getPhotoProfileUrl(),
                entity.getPhoneNumber(),
                entity.getPerosnalId(),
                entity.getEmail(),
                domainRole,
                entity.getPassword(),
                entity.getAddress(),
                entity.isActive()
        );
    }
}


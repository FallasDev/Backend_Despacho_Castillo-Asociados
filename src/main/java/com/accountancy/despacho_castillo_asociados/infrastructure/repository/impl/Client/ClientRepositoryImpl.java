package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Client.JPAClientRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private final JPAClientRepository jpaClientRepository;

    public ClientRepositoryImpl(JPAClientRepository jpaClientRepository) {
        this.jpaClientRepository = jpaClientRepository;
    }

    @Override
    public Client create(ClientRequest clientRequest) {
        ClientEntity entity = new ClientEntity(
                clientRequest.getName(),
                clientRequest.getSuername(),
                clientRequest.getPhotoProfileUrl(),
                clientRequest.getPhoneNumber(),
                clientRequest.getPerosnalId(),
                clientRequest.getEmail(),
                clientRequest.getRole() != null ?
                    new RoleEntity(clientRequest.getRole().getId(), "", "", new java.util.ArrayList<>(), true) : null,
                clientRequest.getPassword(),
                clientRequest.getAddress(),
                true
        );

        ClientEntity saved = jpaClientRepository.save(entity);
        return getClientFromEntity(saved);
    }

    @Override
    public Client update(ClientRequest clientRequest, int id) {
        Optional<ClientEntity> existing = jpaClientRepository.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        ClientEntity entity = existing.get();
        entity.setName(clientRequest.getName());
        entity.setSuername(clientRequest.getSuername());
        entity.setPhotoProfileUrl(clientRequest.getPhotoProfileUrl());
        entity.setPhoneNumber(clientRequest.getPhoneNumber());
        entity.setPerosnalId(clientRequest.getPerosnalId());
        entity.setEmail(clientRequest.getEmail());
        if (clientRequest.getRole() != null) {
            entity.setRole(new RoleEntity(clientRequest.getRole().getId(), "", "", new java.util.ArrayList<>(), true));
        }
        entity.setPassword(clientRequest.getPassword());
        entity.setAddress(clientRequest.getAddress());

        ClientEntity updated = jpaClientRepository.save(entity);
        return getClientFromEntity(updated);
    }

    @Override
    public void activate(int id) {
        Optional<ClientEntity> existing = jpaClientRepository.findById(id);
        if (existing.isPresent()) {
            ClientEntity entity = existing.get();
            entity.setActive(true);
            jpaClientRepository.save(entity);
        }
    }

    @Override
    public boolean deactivate(int id) {
        Optional<ClientEntity> existing = jpaClientRepository.findById(id);
        if (existing.isPresent()) {
            ClientEntity entity = existing.get();
            entity.setActive(false);
            jpaClientRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Client> findById(int id) {
        return jpaClientRepository.findById(id)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintByName(String name) {
        return jpaClientRepository.findByName(name)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintByNameAndIsActive(String name) {
        return jpaClientRepository.findByNameAndIsActiveTrue(name)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintByNameAndIsInactive(String name) {
        return jpaClientRepository.findByNameAndIsActiveFalse(name)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintBySurname(String surname) {
        return jpaClientRepository.findBySuername(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintBySurnameAndIsActive(String surname) {
        return jpaClientRepository.findBySuernameAndIsActiveTrue(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintBySurnameAndIsInactive(String surname) {
        return jpaClientRepository.findBySuernameAndIsActiveFalse(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public PageResult<Client> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ClientEntity> entityPage = jpaClientRepository.findAll(pageable);

        List<ClientEntity> clients = entityPage.getContent();

        return new PageResult<>(
                clients.stream()
                        .map(this::getClientFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @NonNull
    private Client getClientFromEntity(@NonNull ClientEntity entity) {
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

        return new Client(
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


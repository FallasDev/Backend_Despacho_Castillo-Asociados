package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
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
        entity.setSurname(clientRequest.getSuername());
        entity.setPhotoProfileUrl(clientRequest.getPhotoProfileUrl());
        entity.setPhoneNumber(clientRequest.getPhoneNumber());
        entity.setPersonalId(clientRequest.getPerosnalId());
        entity.setEmail(clientRequest.getEmail());
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
        return jpaClientRepository.findBySurname(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintBySurnameAndIsActive(String surname) {
        return jpaClientRepository.findBySurnameAndIsActiveTrue(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> fintBySurnameAndIsInactive(String surname) {
        return jpaClientRepository.findBySurnameAndIsActiveFalse(surname)
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

        return new Client(
                entity.getId(),
                entity.getName(),
                entity.getSurname(),
                entity.getPhotoProfileUrl(),
                entity.getPhoneNumber(),
                entity.getPersonalId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getAddress(),
                entity.isActive()
        );
    }
}


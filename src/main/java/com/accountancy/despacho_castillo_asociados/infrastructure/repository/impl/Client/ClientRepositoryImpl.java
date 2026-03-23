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

        System.out.println("Creating client: "
         + clientRequest.getName() + " "
                + clientRequest.getEmail() + " "
    + clientRequest.getPhoneNumber() + " "
                + clientRequest.getPersonalId() + " "
                + clientRequest.getAddress() + " "
        + clientRequest.getPhotoProfileUrl() + " "
                + clientRequest.getSurname() + " " );

        ClientEntity entity = new ClientEntity(
                clientRequest.getName(),
                clientRequest.getSurname(),
                clientRequest.getPhotoProfileUrl(),
                clientRequest.getPhoneNumber(),
                clientRequest.getPersonalId(),
                clientRequest.getEmail(),
                clientRequest.getPassword(),
                clientRequest.getAddress(),
                true,
                false
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
        entity.setSurname(clientRequest.getSurname());
        entity.setPhotoProfileUrl(clientRequest.getPhotoProfileUrl());
        entity.setPhoneNumber(clientRequest.getPhoneNumber());
        entity.setPersonalId(clientRequest.getPersonalId());
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
    public Optional<Client> findByName(String name) {
        return jpaClientRepository.findByName(name)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> findByNameAndIsActive(String name) {
        return jpaClientRepository.findByNameAndIsActiveTrue(name)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> findByNameAndIsInactive(String name) {
        return jpaClientRepository.findByNameAndIsActiveFalse(name)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> findBySurname(String surname) {
        return jpaClientRepository.findBySurname(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> findBySurnameAndIsActive(String surname) {
        return jpaClientRepository.findBySurnameAndIsActiveTrue(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> findBySurnameAndIsInactive(String surname) {
        return jpaClientRepository.findBySurnameAndIsActiveFalse(surname)
                .map(this::getClientFromEntity);
    }

    @Override
    public Optional<Client> findByEmailAndActive(String email) {
        return jpaClientRepository.findByEmailAndIsActive(email, true)
                .stream()
                .findFirst()
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

    @Override
    public void enabledClient(int id) {

        Optional<ClientEntity> client = jpaClientRepository.findById(id);

        if (client.isPresent()) {
            Optional<ClientEntity> existing = jpaClientRepository.findById(id);
            if (existing.isPresent()) {
                ClientEntity entity = existing.get();
                entity.setEnabled(true);
                jpaClientRepository.save(entity);
            }
        }

    }

    @Override
    public boolean existByEmailAndPasswordAndActive(String email, String password) {
        return false;
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
                entity.isActive(),
                entity.isEnabled()
        );
    }
}


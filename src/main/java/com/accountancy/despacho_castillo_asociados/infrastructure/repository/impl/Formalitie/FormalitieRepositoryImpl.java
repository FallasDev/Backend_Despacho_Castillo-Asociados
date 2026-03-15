package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie.FormalitieJpaSpecificationBuilder;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie.JPAFormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class FormalitieRepositoryImpl implements FormalitieRepository {


    private final JPAFormalitieRepository jpaFormalitieRepository;
    private final FormalitieJpaSpecificationBuilder specBuilder;

    public FormalitieRepositoryImpl(JPAFormalitieRepository jpaFormalitieRepository, FormalitieJpaSpecificationBuilder specBuilder) {
        this.jpaFormalitieRepository = jpaFormalitieRepository;
        this.specBuilder = specBuilder;
    }


    @Override
    public Formalitie create(FormalitieRequest formalitieRequest, DomainService service, Client client, User user) {


        FormalitieEntity entity = new FormalitieEntity();
        entity.setState(FormalitiesState.PENDING.getId());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setService(
                new ServiceEntity(
                    service.getId(),
                    service.getName(),
                    service.getDescription(),
                    service.isActive()
                )
        );
        return getFormalitie(client, user, entity);
    }

    @Override
    public Formalitie update(FormalitieRequest formalitieRequest, int id,Client client, User user) {

        FormalitieEntity existingEntity = jpaFormalitieRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return null;
        }

        existingEntity.setService(
                new ServiceEntity(
                        formalitieRequest.getServiceId(),
                        null,
                        null,
                        false
                )
        );

        return getFormalitie(client, user, existingEntity);

    }

    @NonNull
    private Formalitie getFormalitie(Client client, User user, FormalitieEntity existingEntity) {
        existingEntity.setClient(
                new ClientEntity(
                        client.getId(),
                        client.getName(),
                        client.getSuername(),
                        client.getPhotoProfileUrl(),
                        client.getPhoneNumber(),
                        client.getPerosnalId(),
                        client.getEmail(),
                        client.getPassword(),
                        client.getAddress(),
                        client.isActive()
                )
        );

        if (user == null) {
            return getFormalitieFromEntity(jpaFormalitieRepository.save(existingEntity));
        }

        existingEntity.setUser(
                new UserEntity(
                        user.getId(),
                        user.getName(),
                        user.getSuername(),
                        user.getPhotoProfileUrl(),
                        user.getPhoneNumber(),
                        user.getPerosnalId(),
                        user.getEmail(),
                        new RoleEntity(
                                user.getRole().getId(),
                                user.getRole().getName(),
                                user.getRole().getDescription(),
                                null,
                                user.getRole().isActive()
                        ),
                        user.getPassword(),
                        user.getAddress(),
                        user.isActive()
                )
        );

        FormalitieEntity updatedEntity = jpaFormalitieRepository.save(existingEntity);
        return getFormalitieFromEntity(updatedEntity);
    }

    @Override
    public boolean changeFormalitieState(int id, FormalitiesState state) {

        FormalitieEntity entity = jpaFormalitieRepository.findById(id).orElse(null);

        if (entity == null) {
            return false;
        }

        entity.setState(state.getId());
        jpaFormalitieRepository.save(entity);
        return true;
    }

    @Override
    public Optional<Formalitie> findById(int id) {
        return jpaFormalitieRepository.findById(id)
                .map(this::getFormalitieFromEntity);
    }

    @Override
    public PageResult<Formalitie> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<FormalitieEntity> entityPage = jpaFormalitieRepository.findAll(pageable);

        return getFormalitiePageResult(page, size, entityPage);

    }

    @Override
    public PageResult<Formalitie> findByFilter(SearchFormalitie searchFormalitie, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Specification<FormalitieEntity> spec = specBuilder.build(searchFormalitie);

        Page<FormalitieEntity> entityPage = jpaFormalitieRepository.findAll(spec, pageable);

        return getFormalitiePageResult(page, size, entityPage);
    }

    @Override
    public PageResult<Formalitie> findByClientId(int clientId, int page, int size) {
        return null;
    }

    @Override
    public PageResult<Formalitie> findByUserId(int userId, int page, int size) {
        return null;
    }

    @Override
    public PageResult<Formalitie> findByServiceId(int serviceId, int page, int size) {
        return null;
    }

    @Override
    public boolean handleFormalitie(int id, int userId) {

        FormalitieEntity entity = jpaFormalitieRepository.findById(id).orElse(null);

        if (entity == null || entity.getState() != FormalitiesState.PENDING.getId()) {
            return false;
        }

        entity.setState(FormalitiesState.IN_PROGRESS.getId());
        entity.setUserId(userId);

        jpaFormalitieRepository.save(entity);
        return true;

    }

    @NonNull
    private PageResult<Formalitie> getFormalitiePageResult(int page, int size, Page<FormalitieEntity> entityPage) {
        List<FormalitieEntity> formalities = entityPage.getContent();

        List<Formalitie> formalitieList = formalities.stream()
                .map(this::getFormalitieFromEntity)
                .toList();

        return new PageResult<>(
                formalitieList,
                page,
                size,
                formalitieList.size(),
                entityPage.getTotalPages()
        );
    }

    @NonNull
    private Formalitie getFormalitieFromEntity(@NonNull FormalitieEntity entity) {
        return new Formalitie(
                entity.getId(),
                new DomainService(
                        entity.getService().getId(),
                        entity.getService().getName(),
                        entity.getService().getDescription(),
                        entity.getService().isActive()
                ),
                null,
                null,
                FormalitiesState.fromId(entity.getState()),
                entity.getCreatedAt()
        );
    }

}

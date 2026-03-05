package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
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
    public Formalitie create(FormalitieRequest formalitieRequest, DomainService service) {


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


        FormalitieEntity saved = jpaFormalitieRepository.save(entity);
        return getFormalitieFromEntity(saved);
    }

    @Override
    public Formalitie update(FormalitieRequest formalitieRequest, int id) {

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

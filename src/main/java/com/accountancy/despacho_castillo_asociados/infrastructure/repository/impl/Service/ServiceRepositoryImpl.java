package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Service;


import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Service.JPAServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    private final JPAServiceRepository jpaServiceRepository;

    public ServiceRepositoryImpl(JPAServiceRepository jpaServiceRepository) {
        this.jpaServiceRepository = jpaServiceRepository;
    }

    @Override
    public DomainService create(ServiceRequest serviceRequest) {


        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName(serviceRequest.getName());
        serviceEntity.setDescription(serviceRequest.getDescription());
        serviceEntity.setActive(true);

        ServiceEntity savedEntity = jpaServiceRepository.save(serviceEntity);

        return new DomainService(
                savedEntity.getId(),
                savedEntity.getName(),
                savedEntity.getDescription(),
                savedEntity.isActive()
        );

    }

    @Override
    public DomainService update(ServiceRequest serviceRequest, int id) {

        ServiceEntity existingEntity = jpaServiceRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return null;
        }

        existingEntity.setName(serviceRequest.getName());
        existingEntity.setDescription(serviceRequest.getDescription());
        ServiceEntity updatedEntity = jpaServiceRepository.save(existingEntity);
        return new DomainService(
                updatedEntity.getId(),
                updatedEntity.getName(),
                updatedEntity.getDescription(),
                updatedEntity.isActive()
        );

    }

    @Override
    public boolean deactivate(int id) {

        ServiceEntity existingEntity = jpaServiceRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return false;
        }

        if (!existingEntity.isActive()) {
            return false;
        }

        existingEntity.setActive(false);
        jpaServiceRepository.save(existingEntity);
        return true;
    }
    @Override
    public void activate(int id) {

        ServiceEntity existingEntity = jpaServiceRepository.findById(id).orElse(null);

        if (existingEntity != null) {
            existingEntity.setActive(true);
            jpaServiceRepository.save(existingEntity);
        }

    }

    @Override
    public Optional<DomainService> findById(int id) {

        return jpaServiceRepository.findById(id).map(entity -> new DomainService(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        ));

    }

    @Override
    public Optional<DomainService> findByName(String name) {
        return jpaServiceRepository.findByName(name).map(entity -> new DomainService(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        ));
    }

    @Override
    public PageResult<DomainService> findByContainsNameLetterUseCase(String name, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ServiceEntity> serviceEntityPage = jpaServiceRepository.findByNameContainingIgnoreCase(name, pageable);

        return getDomainServicePageResult(page, size, serviceEntityPage);
    }

    @Override
    public Optional<DomainService> findByNameAndIsActive(String name) {

        ServiceEntity entity = jpaServiceRepository.findByNameAndActiveIsTrue(name).orElse(null);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(new DomainService(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        ));
    }



    @Override
    public Optional<DomainService> findByNameAndIsInactive(String name) {

        ServiceEntity entity = jpaServiceRepository.findByNameAndActiveIsFalse(name).orElse(null);
        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(new DomainService(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        ));

    }

    @Override
    public PageResult<DomainService> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ServiceEntity> serviceEntityPage = jpaServiceRepository.findAll(pageable);

        return getDomainServicePageResult(page, size, serviceEntityPage);
    }

    @Override
    public List<DomainService> findAllWithoutPagination() {
        return jpaServiceRepository.findAll().stream().map(entity -> new DomainService(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        )).filter(DomainService::isActive).toList();
    }

    @Override
    public boolean existsByNameAndIsActive(String name) {
        return jpaServiceRepository.existsByNameAndActive(name, true);
    }

    @Override
    public boolean existsByNameAndIsInactive(String name) {
        return jpaServiceRepository.existsByNameAndActive(name, false);
    }

    @NonNull
    private PageResult<DomainService> getDomainServicePageResult(int page, int size, Page<ServiceEntity> serviceEntityPage) {
        List<ServiceEntity> serviceEntities = serviceEntityPage.getContent();

        List<DomainService> domainServices = serviceEntities.stream().map(entity -> new DomainService(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        )).filter(DomainService::isActive).toList();

        return new PageResult<>(
                domainServices,
                page,
                size,
                domainServices.size(),
                serviceEntityPage.getTotalPages()
        );
    }
}

package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.ServiceCustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField.ServiceCustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.CustomField.JPACustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Service.JPAServiceRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField.JPAServiceCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ServiceCustomFieldImpl implements ServiceCustomFieldsRepository {

    private final JPAServiceCustomFieldRepository jpaServiceCustomField;
    private final JPAServiceRepository jpaServiceRepository;
    private final JPACustomFieldRepository jpaCustomFieldRepository;

    public ServiceCustomFieldImpl(JPAServiceCustomFieldRepository jpaServiceCustomField, JPAServiceRepository jpaServiceRepository, JPACustomFieldRepository jpaCustomFieldRepository) {
        this.jpaServiceCustomField = jpaServiceCustomField;
        this.jpaServiceRepository = jpaServiceRepository;
        this.jpaCustomFieldRepository = jpaCustomFieldRepository;
    }

    @Override
    public ServiceCustomField create(ServiceCustomFieldRequest request) {

        ServiceCustomFieldEntity serviceCustomField = new ServiceCustomFieldEntity();
        serviceCustomField.setServiceId(request.getServiceId());
        serviceCustomField.setCustomFieldId(request.getCustomFieldId());
        serviceCustomField.setActive(true);

        ServiceCustomFieldEntity savedEntity = jpaServiceCustomField.save(serviceCustomField); // Aquí deberías guardar la entidad usando el repositorio JPA

        return getServiceCustomField(savedEntity);

    }

    @Override
    public ServiceCustomField update(ServiceCustomFieldRequest request, int id) {
        return null;
    }

    @Override
    public boolean deactivate(int id) {

        ServiceCustomFieldEntity existingEntity = jpaServiceCustomField.findById(id).orElse(null);

        if (existingEntity == null) {
            return false;
        }

        existingEntity.setActive(false);
        jpaServiceCustomField.save(existingEntity);
        return true;
    }

    @Override
    public void activate(int id) {

        ServiceCustomFieldEntity existingEntity = jpaServiceCustomField.findById(id).orElse(null);

        if (existingEntity != null) {
            existingEntity.setActive(true);
            jpaServiceCustomField.save(existingEntity);
        }

    }

    @Override
    public Optional<ServiceCustomField> findById(int id) {
        return jpaServiceCustomField.findById(id).map(this::getServiceCustomField);
    }



    @Override
    public PageResult<ServiceCustomField> findByServiceId(int serviceId, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ServiceCustomFieldEntity> serviceCustomFieldPage = jpaServiceCustomField.findByServiceId(serviceId, pageable);

        List<ServiceCustomFieldEntity> serviceCustomFieldEntities = serviceCustomFieldPage.getContent();

        return new PageResult<>(
                serviceCustomFieldEntities.stream().map(
                        this::getServiceCustomField
                ).filter(Objects::nonNull).filter(ServiceCustomField::isActive).toList(),
                serviceCustomFieldPage.getNumber(),
                serviceCustomFieldPage.getSize(),
                serviceCustomFieldPage.getTotalElements(),
                serviceCustomFieldPage.getTotalPages()
        );

    }

    @Override
    public PageResult<ServiceCustomField> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ServiceCustomFieldEntity> serviceCustomFieldPage = jpaServiceCustomField.findAll(pageable);

        List<ServiceCustomFieldEntity> serviceCustomFieldEntities = serviceCustomFieldPage.getContent();

        return new PageResult<>(
                serviceCustomFieldEntities.stream().map(
                        this::getServiceCustomField
                ).filter(Objects::nonNull).filter(ServiceCustomField::isActive).toList(),
                serviceCustomFieldPage.getNumber(),
                serviceCustomFieldPage.getSize(),
                serviceCustomFieldPage.getTotalElements(),
                serviceCustomFieldPage.getTotalPages()
        );
    }

    @Override
    public boolean existsById(int id) {
        return jpaServiceCustomField.existsById(id);
    }

    @Override
    public boolean existsByServiceId(int serviceId) {
        return jpaServiceCustomField.existsByServiceId(serviceId);
    }



    @Override
    public Optional<ServiceCustomField> findByServiceIdAndCustomFieldIdAndIsInactive(int serviceId, int customFieldId) {
        return jpaServiceCustomField.findByServiceIdAndCustomFieldIdAndActive(serviceId,customFieldId,false)
                .map(this::getServiceCustomField);
    }

    @Override
    public Optional<ServiceCustomField> findByServiceIdAndCustomFieldId(int serviceId, int customFieldId) {
        return jpaServiceCustomField.findByServiceIdAndCustomFieldId(serviceId,customFieldId)
                .map(this::getServiceCustomField);
    }


    @Nullable
    private ServiceCustomField getServiceCustomField(ServiceCustomFieldEntity entity) {
        ServiceEntity serviceEntity = jpaServiceRepository.findById(entity.getServiceId()).orElse(null);
        CustomFieldEntity customFieldEntity = jpaCustomFieldRepository.findById(entity.getCustomFieldId()).orElse(null);

        if (serviceEntity == null || customFieldEntity == null) {
            return null; // O maneja el error según tus necesidades
        }

        CustomField domainCustomField = new CustomFieldBuilder()
                .setId(customFieldEntity.getId())
                .setName(customFieldEntity.getName())
                .setIsRequired(customFieldEntity.isRequired())
                .setIsExclusive(customFieldEntity.isExclusive())
                .setIsActive(customFieldEntity.isActive())
                .getResult();

        DomainService domainService = new DomainService(
                serviceEntity.getId(),
                serviceEntity.getName(),
                serviceEntity.getDescription(),
                serviceEntity.isActive()
        );

        return new ServiceCustomField(
                entity.getId(),
                domainService,
                domainCustomField,
                entity.isActive()
        );
    }
}

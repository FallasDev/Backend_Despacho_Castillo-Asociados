package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.ServiceCustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField.ServiceCustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Service.JPAServiceRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField.JPAServiceCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ServiceCustomFieldImpl implements ServiceCustomFieldsRepository {

    private final JPAServiceCustomFieldRepository jpaServiceCustomField;

    public ServiceCustomFieldImpl(JPAServiceCustomFieldRepository jpaServiceCustomField) {
        this.jpaServiceCustomField = jpaServiceCustomField;
    }

    @Override
    public ServiceCustomField create(ServiceCustomFieldRequest request, DomainService service, List<CustomField> customFields) {

        ServiceCustomFieldEntity serviceCustomField = new ServiceCustomFieldEntity();
        ServiceEntity serviceEntity = new ServiceEntity(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.isActive()
        );
        serviceCustomField.setService(serviceEntity);
        serviceCustomField.setName(request.getName());
        setValues(customFields, serviceCustomField);


        serviceCustomField.setActive(true);

        ServiceCustomFieldEntity savedEntity = jpaServiceCustomField.save(serviceCustomField); // Aquí deberías guardar la entidad usando el repositorio JPA

        return getServiceCustomField(savedEntity);

    }



    @Override
    public List<ServiceCustomField> createAll(List<ServiceCustomField> requests) {
        return null;
    }

    @Override
    public ServiceCustomField update(ServiceCustomFieldRequest request, DomainService service, List<CustomField> customFields, int id) {

        ServiceCustomFieldEntity existingEntity = jpaServiceCustomField.findById(id).orElseThrow(
                () -> new BadRequestException("ServiceCustomField with id " + id + " not found")
        );

        System.out.println("Updating ServiceCustomField with ID in impl: " + id);


        existingEntity.setName(request.getName());
        existingEntity.setService(new ServiceEntity(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.isActive()
        ));
        setValues(customFields, existingEntity);

        ServiceCustomFieldEntity updatedEntity = jpaServiceCustomField.save(existingEntity);
        return getServiceCustomField(updatedEntity);



    }

    private void setValues(List<CustomField> customFields, ServiceCustomFieldEntity existingEntity) {
        List<CustomFieldEntity> list = customFields.stream()
                .map(cf -> {
                    CustomFieldEntity e = new CustomFieldEntity();
                    e.setId(cf.getId());
                    e.setName(cf.getName());
                    e.setRequired(cf.isRequired());
                    e.setExclusive(cf.isExclusive());
                    e.setActive(cf.isActive());
                    e.setPlaceholder(cf.getPlaceholder());
                    e.setHelpText(cf.getHelpText());
                    e.setDefaultValue(cf.getDefaultValue());
                    e.setType(new TypeEntity(
                            cf.getType().getId(),
                            cf.getType().getName(),
                            cf.getType().isActive()
                    ));
                    return e;
                })
                .collect(Collectors.toList());

        existingEntity.setCustomFields(list);
    }

    @Override
    public boolean deactivate(int id) {

        ServiceCustomFieldEntity existingEntity = jpaServiceCustomField.findById(id).orElse(null);

        if (existingEntity == null || !existingEntity.isActive()) {
            return false;
        }

        existingEntity.setActive(false);
        jpaServiceCustomField.save(existingEntity);
        return true;
    }

    @Override
    @Transactional
    public boolean deactivateByServiceId(int serviceId) {
        int updatedRows = jpaServiceCustomField.deactivateByServiceId(serviceId);
        return updatedRows > 0;
    }

    @Override
    @Transactional
    public boolean deactivateByCustomFieldId(int customFieldId) {
//        int updatedRows = jpaServiceCustomField.deactivateByCustomFieldId(customFieldId);
//        return updatedRows > 0;
        return false;
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

        return getServiceCustomFieldPageResult(serviceCustomFieldPage);

    }

    @Override
    public PageResult<ServiceCustomField> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ServiceCustomFieldEntity> serviceCustomFieldPage = jpaServiceCustomField.findAll(pageable);

        return getServiceCustomFieldPageResult(serviceCustomFieldPage);
    }

    @Override
    public Optional<ServiceCustomField> findByFormalitieId(int customFieldId) {
        return null;
    }

    @Override
    public List<ServiceCustomField> findAllWithoutPagination() {
        return jpaServiceCustomField.findAll().stream()
                .map(this::getServiceCustomField)
                .filter(Objects::nonNull)
                .filter(ServiceCustomField::isActive)
                .toList();
    }

    @NonNull
    private PageResult<ServiceCustomField> getServiceCustomFieldPageResult(Page<ServiceCustomFieldEntity> serviceCustomFieldPage) {
        List<ServiceCustomFieldEntity> serviceCustomFieldEntities = serviceCustomFieldPage.getContent();

        List<ServiceCustomField> serviceCustomFields = serviceCustomFieldEntities.stream()
                .map(this::getServiceCustomField)
                .filter(Objects::nonNull)
                .filter(ServiceCustomField::isActive)
                .toList();

        return new PageResult<>(
                serviceCustomFields,
                serviceCustomFieldPage.getNumber(),
                serviceCustomFieldPage.getSize(),
                serviceCustomFields.size(),
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
    public Optional<ServiceCustomField> findByNameAndIsInactive(String name) {
        return jpaServiceCustomField.findByNameAndActive(name, false)
                .map(this::getServiceCustomField);
    }


    @Nullable
    private ServiceCustomField getServiceCustomField(ServiceCustomFieldEntity entity) {

        System.out.println("Mapping ServiceCustomFieldEntity to ServiceCustomField: " + entity.toString());

        List<CustomField> domainCustomFields = entity.getCustomFields().stream().map(
                customFieldEntity -> new CustomField(
                        customFieldEntity.getId(),
                        customFieldEntity.getName(),
                        customFieldEntity.isRequired(),
                        customFieldEntity.isExclusive(),
                        customFieldEntity.isActive(),
                        customFieldEntity.getPlaceholder(),
                        customFieldEntity.getHelpText(),
                        customFieldEntity.getDefaultValue(),
                        new Type(
                                customFieldEntity.getType().getId(),
                                customFieldEntity.getType().getName(),
                                customFieldEntity.getType().isActive()
                        )
                )
        ).toList();

        DomainService domainService = new DomainService(
                entity.getService().getId(),
                entity.getService().getName(),
                entity.getService().getDescription(),
                entity.getService().isActive()
        );

        return new ServiceCustomField(
                entity.getId(),
                entity.getName(),
                domainService,
                domainCustomFields,
                entity.isActive()
        );
    }
}

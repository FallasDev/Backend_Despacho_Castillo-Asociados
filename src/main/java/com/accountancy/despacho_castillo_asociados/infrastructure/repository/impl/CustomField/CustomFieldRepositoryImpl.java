package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.CustomField;


import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.CustomField.JPACustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomFieldRepositoryImpl implements CustomFieldRepository {


    private final JPACustomFieldRepository jPACustomFieldRepository;

    public CustomFieldRepositoryImpl(JPACustomFieldRepository jPACustomFieldRepository) {
        this.jPACustomFieldRepository = jPACustomFieldRepository;
    }

    @Override
    public CustomField create(CustomFieldRequest customField, Type type) {

        CustomFieldEntity customFieldEntity = new CustomFieldEntity();
        customFieldEntity.setName(customField.getName());
        customFieldEntity.setRequired(customField.isRequired());
        customFieldEntity.setExclusive(customField.isExclusive());
        customFieldEntity.setActive(true);
        customFieldEntity.setType(
                new TypeEntity(
                        type.getId(),
                        type.getName(),
                        type.isActive()
                )
        );

        jPACustomFieldRepository.save(customFieldEntity);

        return getCustomField(customFieldEntity).orElse(null);

    }

    @Override
    public CustomField update(CustomFieldRequest customField, int id, Type type) {

        CustomFieldEntity existingCustomField = jPACustomFieldRepository.findById(id).orElse(null);

        if (existingCustomField == null) {
            return null;
        }

        existingCustomField.setName(customField.getName());
        existingCustomField.setRequired(customField.isRequired());
        existingCustomField.setExclusive(customField.isExclusive());


        existingCustomField.setType(
                new TypeEntity(
                        type.getId(),
                        type.getName(),
                        type.isActive()
                )
        );

        CustomFieldEntity entity = jPACustomFieldRepository.save(existingCustomField);

        return getCustomField(entity).orElse(null);

    }

    @Override
    public boolean deactivate(int id) {

        CustomFieldEntity customField = jPACustomFieldRepository.findById(id).orElse(null);
        if (customField != null && customField.isActive()) {
            customField.setActive(false);
            jPACustomFieldRepository.save(customField);
            return true;
        }

        return false;
    }

    @Override
    public void activate(int id) {

        CustomFieldEntity customField = jPACustomFieldRepository.findById(id).orElse(null);

        if (customField == null) {
            return;
        }

        customField.setActive(true);
        jPACustomFieldRepository.save(customField);

    }

    @Override
    public Optional<CustomField> findById(int id) {

        CustomFieldEntity customField = jPACustomFieldRepository.findById(id).orElse(null);

        if (customField == null) {
            return Optional.empty();
        }

        return getCustomField(customField);
    }

    @Override
    public Optional<CustomField> findByName(String name) {

        CustomFieldEntity customField = jPACustomFieldRepository.findByName(name).orElse(null);

        return getCustomField(customField);

    }

    @Override
    public Optional<CustomField> findByNameAndIsActive(String name) {

        CustomFieldEntity customField = jPACustomFieldRepository.findByNameAndActiveIsTrue(name).orElse(null);

        return getCustomField(customField);


    }

    @Override
    public Optional<CustomField> findByNameAndIsInactive(String name) {
        CustomFieldEntity customField = jPACustomFieldRepository.findByNameAndActiveIsFalse(name).orElse(null);
        return getCustomField(customField);
    }

    @Override
    public PageResult<CustomField> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<CustomFieldEntity> customFields = jPACustomFieldRepository.findAll(pageable);

        List<CustomFieldEntity> customFieldList = customFields.getContent();

        List<CustomField> customFieldResultList = customFieldList.stream()
                .map(this::getCustomField)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(CustomField::isActive)
                .toList();

        return new PageResult<>(
                customFieldResultList,
                page,
                size,
                customFieldResultList.size(),
                customFields.getTotalPages()
        );

    }

    @Override
    public PageResult<CustomField> findByTypeId(int typeId, int page, int size) {
        return null;
    }

    @Override
    public boolean existsByNameAndIsActive(String name) {
        return jPACustomFieldRepository.existsByNameAndActive(name,true);
    }

    @Override
    public boolean existsByNameAndIsInactive(String name) {

        return jPACustomFieldRepository.existsByNameAndActive(name,false);
    }



    @Override
    public PageResult<CustomField> findByIsRequired(boolean isRequired, int page, int size) {
//        Pageable pageable = Pageable.ofSize(size).withPage(page);
//        Page<CustomFieldEntity> customFields = jPACustomFieldRepository.findByIsRequired(isRequired, pageable);
//
//        List<CustomFieldEntity> customFieldList = customFields.getContent();
//
//        return new PageResult<>(
//                customFieldList.stream()
//                        .map(this::getCustomField)
//                        .filter(Optional::isPresent)
//                        .map(Optional::get)
//                        .filter(CustomField::isActive)
//                        .toList(),
//                page,
//                size,
//                customFields.getTotalElements(),
//                customFields.getTotalPages()
//        );
        return null;
    }


    @NonNull
    private Optional<CustomField> getCustomField(CustomFieldEntity customField) {
        if (customField == null) {
            return Optional.empty();
        }

        Type type = new Type(
                customField.getType().getId(),
                customField.getType().getName(),
                customField.getType().isActive()
        );

        CustomField result = new CustomFieldBuilder()
                .setId(customField.getId())
                .setName(customField.getName())
                .setIsRequired(customField.isRequired())
                .setIsExclusive(customField.isExclusive())
                .setIsActive(customField.isActive())
                .setType(type)
                .getResult();

        return Optional.of(result);
    }

}

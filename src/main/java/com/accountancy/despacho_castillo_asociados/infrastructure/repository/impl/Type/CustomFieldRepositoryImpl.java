package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Type;


import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Type.JPACustomFieldRepository;
import org.jspecify.annotations.NonNull;
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

        if (existingCustomField != null) {
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

        return null;

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
    public List<CustomField> findAll() {

        return jPACustomFieldRepository.findAll().stream()
                .map(customFieldEntity -> getCustomField(customFieldEntity).orElse(null)).filter(Objects::nonNull)
                .filter(CustomField::isActive)
                .toList();

    }

    @Override
    public boolean existsByNameAndIsActive(String name) {
        return jPACustomFieldRepository.existsByNameAndActive(name,true);
    }

    @Override
    public boolean existsByNameAndIsInactive(String name) {

        return jPACustomFieldRepository.existsByNameAndActive(name,false);
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

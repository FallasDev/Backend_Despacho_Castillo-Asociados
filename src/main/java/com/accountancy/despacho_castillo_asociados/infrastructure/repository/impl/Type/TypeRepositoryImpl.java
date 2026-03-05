package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Type.JPATypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TypeRepositoryImpl implements TypeRepository {

    private final JPATypeRepository jpaTypeRepository;

    public TypeRepositoryImpl(JPATypeRepository jpaTypeRepository) {
        this.jpaTypeRepository = jpaTypeRepository;
    }

    @Override
    public Type create(TypeRequest request) {

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setName(request.getName());
        typeEntity.setActive(true);

        TypeEntity entity = jpaTypeRepository.save(typeEntity);


        return new Type(entity.getId(), entity.getName(), entity.isActive());

    }

    @Override
    public Type update(TypeRequest type, int id) {

        TypeEntity existingType = jpaTypeRepository.findById(id).orElse(null);
        if (existingType != null) {
            existingType.setName(type.getName());
            TypeEntity entity = jpaTypeRepository.save(existingType);
            return new Type(entity.getId(), entity.getName(), entity.isActive());
        }

        return null;

    }

    @Override
    public boolean deactivate(int id) {

        TypeEntity type = jpaTypeRepository.findById(id).orElse(null);
        if (type != null && type.isActive()) {
            type.setActive(false);
            jpaTypeRepository.save(type);
            return true;
        }

        return false;

    }

    @Override
    public void activate(int id) {

        TypeEntity type = jpaTypeRepository.findById(id).orElse(null);

        if (type == null) {
            return;
        }


        type.setActive(true);
        jpaTypeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(int id) {

        TypeEntity type = jpaTypeRepository.findById(id).orElse(null);

        if (type == null) {
            return Optional.empty();
        }

        Type result = new Type(type.getId(), type.getName(), type.isActive());

        return Optional.of(result);

    }

    @Override
    public Optional<Type> findByName(String name) {
        TypeEntity type = jpaTypeRepository.findByName(name);
        if (type == null) {
            return Optional.empty();
        }

        Type result = new Type(type.getId(), type.getName(), type.isActive());

        return Optional.of(result);
    }

    @Override
    public PageResult<Type> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<TypeEntity> typePage = jpaTypeRepository.findAll(pageable);

        List<TypeEntity> types = typePage.getContent();

        List<Type> typeList = types.stream().map(
                this::getTypeFromEntity
        ).filter(Type::isActive).toList();

        return new PageResult<>(
                typeList,
                page,
                size,
                typeList.size(),
                typePage.getTotalPages()
        );

    }

    @Override
    public boolean existsByNameAndIsActive(String name) {
        return jpaTypeRepository.existsByNameAndActive(name, true);
    }

    @Override
    public boolean existsByNameAndIsInactive(String name) {
        return jpaTypeRepository.existsByNameAndActive(name, false);
    }

    @Override
    public boolean hasActiveAssociations(int id) {
        return jpaTypeRepository.existsByIdAndCustomFieldsActiveTrue(id);
    }

    @Override
    public Optional<Type> findByNameAndIsActive(String name) {
        TypeEntity type = jpaTypeRepository.findByNameAndActiveIsTrue(name).orElse(null);
        if (type == null) {
            return Optional.empty();
        }

        Type result = new Type(type.getId(), type.getName(), type.isActive());

        return Optional.of(result);
    }

    @Override
    public Optional<Type> findByNameAndIsInactive(String name) {
        TypeEntity type = jpaTypeRepository.findByNameAndActiveIsFalse(name).orElse(null);
        if (type == null) {
            return Optional.empty();
        }

        Type result = new Type(type.getId(), type.getName(), type.isActive());

        return Optional.of(result);
    }

    @NonNull
    public Type getTypeFromEntity(@NonNull TypeEntity entity) {
        return new Type(
                entity.getId(),
                entity.getName(),
                entity.isActive()
        );
    }


}

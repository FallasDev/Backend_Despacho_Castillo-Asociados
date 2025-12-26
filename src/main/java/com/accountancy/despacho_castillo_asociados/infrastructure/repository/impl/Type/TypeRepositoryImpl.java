package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Type.JPATypeRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public Type create(Type type) {

        TypeEntity typeEntity = new TypeEntity(
                0,
                type.getName(),
                type.isActive()
        );

        jpaTypeRepository.save(typeEntity);
        type.setId(typeEntity.getId());
        return type;
    }

    @Override
    public Type update(Type type, int id) {

        TypeEntity existingType = jpaTypeRepository.findById(id).orElse(null);
        if (existingType != null) {
            existingType.setName(type.getName());
            jpaTypeRepository.save(existingType);
            type.setId(existingType.getId());
            return type;
        }

        return null;

    }

    @Override
    public boolean deactivate(int id) {

        TypeEntity type = jpaTypeRepository.findById(id).orElse(null);
        if (type != null && type.is_active()) {
            type.set_active(false);
            jpaTypeRepository.save(type);
            return true;
        }

        return false;

    }

    @Override
    public Optional<Type> findById(int id) {

        TypeEntity type = jpaTypeRepository.findById(id).orElse(null);

        if (type == null) {
            return Optional.empty();
        }

        Type result = new Type(type.getId(), type.getName(), type.is_active());

        return Optional.of(result);

    }

    @Override
    public Optional<Type> findByName(String name) {
        TypeEntity type = jpaTypeRepository.findByName(name);



        Type result = new Type(type.getId(), type.getName(), type.is_active());

        return Optional.of(result);
    }

    @Override
    public List<Type> findAll() {

        List<Type> types = jpaTypeRepository.findAll().stream().map(typeEntity ->
            new Type(
                    typeEntity.getId(),
                    typeEntity.getName(),
                    typeEntity.is_active()
            )

        ).filter(Type::isActive).toList();



        return types;
    }
}

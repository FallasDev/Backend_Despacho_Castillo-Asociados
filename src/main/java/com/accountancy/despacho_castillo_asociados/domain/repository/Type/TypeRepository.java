package com.accountancy.despacho_castillo_asociados.domain.repository.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;

import java.util.List;
import java.util.Optional;


public interface TypeRepository {

    Type create(TypeRequest type);
    Type update(TypeRequest type, int id);
    boolean deactivate(int id);
    void activate(int id);

    Optional<Type> findById(int id);
    Optional<Type> findByName(String name);
    Optional<Type> findByNameAndIsActive(String name);
    Optional<Type> findByNameAndIsInactive(String name);
    List<Type> findAll();

    boolean existsByNameAndIsActive(String name);
    boolean existsByNameAndIsInactive(String name);



}

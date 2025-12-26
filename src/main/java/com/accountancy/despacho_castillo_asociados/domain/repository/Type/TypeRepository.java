package com.accountancy.despacho_castillo_asociados.domain.repository.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TypeRepository {

    Type create(Type type);
    Type update(Type type, int id);
    boolean deactivate(int id);

    Optional<Type> findById(int id);
    Optional<Type> findByName(String name);
    List<Type> findAll();


}

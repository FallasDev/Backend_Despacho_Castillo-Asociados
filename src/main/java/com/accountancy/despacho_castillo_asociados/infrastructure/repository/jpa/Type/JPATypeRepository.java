package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPATypeRepository extends JpaRepository<TypeEntity, Integer> {

    TypeEntity findByName(String name);

}

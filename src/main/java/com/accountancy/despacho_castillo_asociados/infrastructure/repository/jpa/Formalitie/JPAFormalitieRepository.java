package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JPAFormalitieRepository extends JpaRepository<FormalitieEntity, Integer>, JpaSpecificationExecutor<FormalitieEntity> {

}

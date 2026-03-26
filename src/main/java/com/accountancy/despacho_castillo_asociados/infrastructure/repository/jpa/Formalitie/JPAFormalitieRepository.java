package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalityClientStats;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Stats;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface JPAFormalitieRepository extends JpaRepository<FormalitieEntity, Integer>, JpaSpecificationExecutor<FormalitieEntity> {

    @Query(
           "SELECT new com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Stats(" +
                   "COUNT(f), " +
                   "SUM(CASE WHEN f.state = 1 THEN 1 ELSE 0 END), " +
                   "SUM(CASE WHEN f.state = 3 THEN 1 ELSE 0 END)) " +
                   "FROM FormalitieEntity f WHERE f.client.id = :clientId"
    )
    Stats countFormalitiesByClientId(int clientId);
}

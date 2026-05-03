package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalityClientStats;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Stats;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceCountProjection;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

import java.util.List;

public interface JPAFormalitieRepository extends JpaRepository<FormalitieEntity, Integer>, JpaSpecificationExecutor<FormalitieEntity> {

    @Query(
           "SELECT new com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Stats(" +
                   "COUNT(f), " +
                   "SUM(CASE WHEN f.state = 1 THEN 1 ELSE 0 END), " +
                   "SUM(CASE WHEN f.state = 3 THEN 1 ELSE 0 END)) " +
                   "FROM FormalitieEntity f WHERE f.client.id = :clientId"
    )
    Stats countFormalitiesByClientId(int clientId);

    @Query(value = """
    SELECT 
        s.id as id,
        s.name as name,
        s.description as description,
        s.active as active,
        COUNT(f.id) as totalFormalities
    FROM formalities f
    INNER JOIN services s ON s.id = f.service_id
    GROUP BY s.id
    LIMIT 6
""", nativeQuery = true)
    List<ServiceCountProjection> findServiceCounts();

    // --- Dashboard queries ---

    long countByActiveTrue();

    long countByStateAndActiveTrue(int state);

    @Query("SELECT YEAR(f.createdAt), MONTH(f.createdAt), COUNT(f) " +
           "FROM FormalitieEntity f " +
           "WHERE f.active = true AND f.createdAt >= :since " +
           "GROUP BY YEAR(f.createdAt), MONTH(f.createdAt) " +
           "ORDER BY YEAR(f.createdAt), MONTH(f.createdAt)")
    List<Object[]> countByMonthSince(@Param("since") LocalDateTime since);

    @Query("SELECT f.service.name, COUNT(f) " +
           "FROM FormalitieEntity f " +
           "WHERE f.active = true " +
           "GROUP BY f.service.name " +
           "ORDER BY COUNT(f) DESC")
    List<Object[]> countByService();

    List<FormalitieEntity> findTop10ByActiveTrueOrderByCreatedAtDesc();

    List<FormalitieEntity> findByUserIsNullAndActiveTrueAndStateInOrderByCreatedAtDesc(List<Integer> states);
}


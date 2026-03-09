package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportField;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportField.ReportFieldEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAReportFieldRepository extends JpaRepository<ReportFieldEntity, Integer> {

    Optional<ReportFieldEntity> findByLabelAndActive(String label, boolean active);

    boolean existsById(int id);

    boolean existsByLabel(String label);
    boolean existsByLabelAndActive(String label, boolean active);

    Page<ReportFieldEntity> findAllByActive(boolean active, Pageable pageable);

    Page<ReportFieldEntity> findByReportCategoryIdAndActive(int reportCategoryId, boolean active, Pageable pageable);
}

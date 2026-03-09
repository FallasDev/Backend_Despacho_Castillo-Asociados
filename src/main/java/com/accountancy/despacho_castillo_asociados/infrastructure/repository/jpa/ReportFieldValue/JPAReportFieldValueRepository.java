package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportFieldValue.ReportFieldValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPAReportFieldValueRepository extends JpaRepository<ReportFieldValueEntity, Integer> {

    Page<ReportFieldValueEntity> findByActive(boolean active, Pageable pageable);
    
    Page<ReportFieldValueEntity> findByReportIdAndActive(int reportId, boolean active, Pageable pageable);

    List<ReportFieldValueEntity> findByReportFieldIdAndReportId(int reportFieldId, int reportId);
}

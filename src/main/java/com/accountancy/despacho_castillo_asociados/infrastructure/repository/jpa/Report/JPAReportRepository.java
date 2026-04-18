package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Report;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Report.ReportEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAReportRepository extends JpaRepository<ReportEntity, Integer> {

    Optional<ReportEntity> findByTitle(String title);

    boolean existsByTitleAndActive(String title, boolean active);

    Optional<ReportEntity> findByIdAndActiveIsTrue(int id);
    Optional<ReportEntity> findByTitleAndActiveIsTrue(String title);
    Optional<ReportEntity> findByTitleAndActiveIsFalse(String title);


    Page<ReportEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);


}

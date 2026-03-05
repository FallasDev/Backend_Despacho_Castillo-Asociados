package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportCategory;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAReportCategoryRepository extends JpaRepository<ReportCategoryEntity, Integer> {

    Optional<ReportCategoryEntity> findByName(String category);

    boolean existsByNameAndActive(String name, boolean active);

    Optional<ReportCategoryEntity> findByIdAndActiveIsTrue(int id);
    Optional<ReportCategoryEntity> findByNameAndActiveIsTrue(String category);
    Optional<ReportCategoryEntity> findByNameAndActiveIsFalse(String category);


    Page<ReportCategoryEntity> findByNameContainingIgnoreCase(String category, Pageable pageable);


}

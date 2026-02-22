package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportCategory.JPAReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportCategoryRepositoryImpl implements ReportCategoryRepository {

    private final JPAReportCategoryRepository jpaReportCategoryRepository;

    public ReportCategoryRepositoryImpl(JPAReportCategoryRepository jpaReportCategoryRepository) {
        this.jpaReportCategoryRepository = jpaReportCategoryRepository;
    }

    @Override
    public ReportCategory create(ReportCategoryRequest reportCategoryRequest) {

        ReportCategoryEntity reportCategoryEntity = new ReportCategoryEntity();
        reportCategoryEntity.setCategory(reportCategoryRequest.getCategory());
        reportCategoryEntity.setDate(reportCategoryRequest.getDate());
        reportCategoryEntity.setVisibility(1);
        reportCategoryEntity.setActive(true);

        ReportCategoryEntity savedEntity = jpaReportCategoryRepository.save(reportCategoryEntity);

        return mapToDomain(savedEntity);
    }

    @Override
    public ReportCategory update(ReportCategoryRequest reportCategoryRequest, int id) {

        ReportCategoryEntity existingEntity = jpaReportCategoryRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return null;
        }

        existingEntity.setCategory(reportCategoryRequest.getCategory());
        existingEntity.setDate(reportCategoryRequest.getDate());
        existingEntity.setVisibility(1);
        existingEntity.setActive(true);

        ReportCategoryEntity updatedEntity = jpaReportCategoryRepository.save(existingEntity);

        return mapToDomain(updatedEntity);
    }

    @Override
    public Report create(ReportRequest reportRequest) {
        return null;
    }

    @Override
    public Report update(ReportRequest reportRequest, int id) {
        return null;
    }

    @Override
    public boolean deactivate(int id) {

        ReportCategoryEntity existingEntity = jpaReportCategoryRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return false;
        }

        existingEntity.setActive(false);
        jpaReportCategoryRepository.save(existingEntity);

        return true;
    }

    @Override
    public void activate(int id) {

        ReportCategoryEntity existingEntity = jpaReportCategoryRepository.findById(id).orElse(null);

        if (existingEntity != null) {
            existingEntity.setActive(true);
            jpaReportCategoryRepository.save(existingEntity);
        }
    }

    @Override
    public Optional<ReportCategory> findById(int id) {
        return jpaReportCategoryRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public Optional<ReportCategory> findByCategoryAndIsActive(String category) {
        return Optional.empty();
    }

    @Override
    public Optional<ReportCategory> findByCategoryAndIsInactive(String category) {
        return Optional.empty();
    }

    @Override
    public Optional<ReportCategory> findByCategory(String category) {
        return jpaReportCategoryRepository.findByCategory(category)
                .map(this::mapToDomain);
    }

    @Override
    public PageResult<ReportCategory> findByContainsCategory(String category, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportCategoryEntity> reportCategoryPage =
                jpaReportCategoryRepository.findByCategoryContainingIgnoreCase(category, pageable);

        List<ReportCategoryEntity> entities = reportCategoryPage.getContent();

        return new PageResult<>(
                entities.stream()
                        .map(this::mapToDomain)
                        .filter(ReportCategory::isActive)
                        .toList(),
                page,
                size,
                reportCategoryPage.getTotalElements(),
                reportCategoryPage.getTotalPages()
        );
    }

    @Override
    public PageResult<ReportCategory> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportCategoryEntity> reportCategoryPage =
                jpaReportCategoryRepository.findAll(pageable);

        List<ReportCategoryEntity> entities = reportCategoryPage.getContent();

        return new PageResult<>(
                entities.stream()
                        .map(this::mapToDomain)
                        .filter(ReportCategory::isActive)
                        .toList(),
                page,
                size,
                reportCategoryPage.getTotalElements(),
                reportCategoryPage.getTotalPages()
        );
    }

    @Override
    public PageResult<ReportCategory> findByContainsCategoryLetterUseCase(String name, int page, int size) {
        return null;
    }

    @Override
    public Optional<ReportCategory> findByTitleAndIsActive(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<ReportCategory> findByTitleAndIsInactive(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<ReportCategory> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public boolean existsByCategoryAndIsActive(String category) {
        return jpaReportCategoryRepository.existsByCategoryAndActive(category, true);
    }

    @Override
    public boolean existsByCategoryAndIsInactive(String category) {
        return jpaReportCategoryRepository.existsByCategoryAndActive(category, false);
    }

    private ReportCategory mapToDomain(ReportCategoryEntity entity) {
        return new ReportCategory(
                entity.getId(),
                entity.getCategory(),
                entity.getDate(),
                entity.isActive(),
                entity.getVisibility()
        );
    }
}
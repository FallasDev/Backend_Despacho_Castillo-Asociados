package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportCategory.JPAReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
        reportCategoryEntity.setName(reportCategoryRequest.getName());
        reportCategoryEntity.setDate(new Date(System.currentTimeMillis()).toLocalDate());
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

        existingEntity.setName(reportCategoryRequest.getName());
        existingEntity.setVisibility(1);
        existingEntity.setActive(true);

        ReportCategoryEntity updatedEntity = jpaReportCategoryRepository.save(existingEntity);

        return mapToDomain(updatedEntity);
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
    public Optional<ReportCategory> findByCategory(String name) {
        return jpaReportCategoryRepository.findByName(name)
                .map(this::mapToDomain);
    }

    @Override
    public PageResult<ReportCategory> findByContainsCategory(String name, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportCategoryEntity> reportCategoryPage =
                jpaReportCategoryRepository.findByNameContainingIgnoreCase(name, pageable);

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
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportCategoryEntity> categoryPage =
                jpaReportCategoryRepository.findByNameContainingIgnoreCase(name, pageable);

        return getCategoryPageResult(page, size, categoryPage);
    }

    private PageResult<ReportCategory> getCategoryPageResult(int page, int size, Page<ReportCategoryEntity> categoryPage) {
        List<ReportCategoryEntity> entities = categoryPage.getContent();

        return new PageResult<>(
                entities.stream()
                        .map(this::mapToDomain)
                        .filter(ReportCategory::isActive)
                        .toList(),
                page,
                size,
                categoryPage.getTotalElements(),
                categoryPage.getTotalPages()
        );
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
    public boolean existsByCategoryAndIsActive(String name) {
        return jpaReportCategoryRepository.existsByNameAndActive(name, true);
    }

    @Override
    public boolean existsByCategoryAndIsInactive(String name) {
        return jpaReportCategoryRepository.existsByNameAndActive(name, false);
    }

    private ReportCategory mapToDomain(ReportCategoryEntity entity) {
        return new ReportCategory(
                entity.getId(),
                entity.getName(),
                entity.getDate(),
                entity.isActive(),
                entity.getVisibility()
        );
    }
}
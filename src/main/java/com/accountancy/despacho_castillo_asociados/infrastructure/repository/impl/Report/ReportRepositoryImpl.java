package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Report;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Report.ReportEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Report.JPAReportRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JPAReportRepository jpaReportRepository;

    public ReportRepositoryImpl(JPAReportRepository jpaReportRepository) {
        this.jpaReportRepository = jpaReportRepository;
    }

    @Override
    public Report create(ReportRequest reportRequest, ReportCategory category) {
        ReportEntity reportEntity = new ReportEntity();

        System.out.println(category);

        setEntities(reportRequest, category, reportEntity);
        reportEntity.setDate(LocalDateTime.now().toLocalDate());
        reportEntity.setActive(true);

        ReportEntity savedEntity = jpaReportRepository.save(reportEntity);

        return mapToDomain(savedEntity);
    }

    @Override
    public Report update(ReportRequest reportRequest, int id, ReportCategory category) {
        ReportEntity existingEntity = jpaReportRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return null;
        }

        setEntities(reportRequest, category, existingEntity);


        ReportEntity updatedEntity = jpaReportRepository.save(existingEntity);

        return mapToDomain(updatedEntity);
    }

    private void    setEntities(ReportRequest reportRequest, ReportCategory category, ReportEntity existingEntity) {
        existingEntity.setTitle(reportRequest.getTitle());
        existingEntity.setDescription(reportRequest.getDescription());

        if (reportRequest.getImage() != null) {
            existingEntity.setImage(reportRequest.getImage().toString());
        }

        existingEntity.setCategory(
                new ReportCategoryEntity(
                        category.getId(),
                        category.getName(),
                        category.getDate(),
                        category.isActive(),
                        category.getVisibility()
                )
        );

        existingEntity.setCategoryId(category.getId());

        System.out.println(existingEntity);
    }

    @Override
    public boolean deactivate(int id) {
        ReportEntity existingEntity = jpaReportRepository.findById(id).orElse(null);
        if (existingEntity == null) return false;
        existingEntity.setActive(false);
        jpaReportRepository.save(existingEntity);
        return true;
    }

    @Override
    public void activate(int id) {
        ReportEntity existingEntity = jpaReportRepository.findById(id).orElse(null);
        if (existingEntity != null) {
            existingEntity.setActive(true);
            jpaReportRepository.save(existingEntity);
        }
    }

    @Override
    public Optional<Report> findById(int id) {
        return jpaReportRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<Report> findByTitleAndIsActive(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Report> findByTitleAndIsInactive(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Report> findByTitle(String title) {
        return jpaReportRepository.findByTitle(title).map(this::mapToDomain);
    }

    @Override
    public PageResult<Report> findByContainsTitle(String title, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ReportEntity> reportPage = jpaReportRepository.findByTitleContainingIgnoreCase(title, pageable);
        return getReportPageResult(page, size, reportPage);
    }

    @Override
    public PageResult<Report> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ReportEntity> reportPage = jpaReportRepository.findAll(pageable);
        return getReportPageResult(page, size, reportPage);
    }

    private PageResult<Report> getReportPageResult(int page, int size, Page<ReportEntity> reportPage) {
        List<ReportEntity> entities = reportPage.getContent();
        return new PageResult<>(
                entities.stream()
                        .map(this::mapToDomain)
                        .filter(Report::isActive)
                        .toList(),
                page, size, reportPage.getTotalElements(), reportPage.getTotalPages()
        );
    }

    @Override
    public PageResult<Report> findByContainsTitleLetterUseCase(String name, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportEntity> reportPage =
                jpaReportRepository.findByTitleContainingIgnoreCase(name, pageable);

        return getReportPageResult(page, size, reportPage);
    }

    @Override
    public boolean existsByTitleAndIsActive(String title) {
        return jpaReportRepository.existsByTitleAndActive(title, true);
    }

    @Override
    public boolean existsByTitleAndIsInactive(String title) {
        return jpaReportRepository.existsByTitleAndActive(title, false);
    }

    private Report mapToDomain(ReportEntity entity) {
        URL urlImage = null;
        try {
            if (entity.getImage() != null && !entity.getImage().isEmpty()) {
                urlImage = URI.create(entity.getImage()).toURL();
            }
        } catch (Exception e) {
            throw new BadRequestException("Error converting image URL: " + e.getMessage());
        }

        return new Report(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                urlImage,
                new ReportCategory(
                        entity.getCategory().getId(),
                        entity.getCategory().getName(),
                        entity.getCategory().getDate(),
                        entity.getCategory().isActive(),
                        entity.getCategory().getVisibility()
                ),
                entity.getDate(),
                entity.isActive()
        );
    }
}
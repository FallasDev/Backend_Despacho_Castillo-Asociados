package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Report;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Report.ReportEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Report.JPAReportRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JPAReportRepository jpaReportRepository;

    public ReportRepositoryImpl(JPAReportRepository jpaReportRepository) {
        this.jpaReportRepository = jpaReportRepository;
    }

    @Override
    public Report create(ReportRequest reportRequest) {

        ReportEntity reportEntity = new ReportEntity();

        reportEntity.setTitle(reportRequest.getTitle());
        reportEntity.setDescription(reportRequest.getDescription());
        reportEntity.setImage(reportRequest.getImage());
        reportEntity.setCategory(reportRequest.getCategory());
        reportEntity.setDate(reportRequest.getDate());
        reportEntity.setActive(true);

        ReportEntity savedEntity = jpaReportRepository.save(reportEntity);

        return mapToDomain(savedEntity);
    }

    @Override
    public Report update(ReportRequest reportRequest, int id) {

        ReportEntity existingEntity = jpaReportRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return null;
        }

        existingEntity.setTitle(reportRequest.getTitle());
        existingEntity.setDescription(reportRequest.getDescription());
        existingEntity.setImage(reportRequest.getImage());
        existingEntity.setCategory(reportRequest.getCategory());
        existingEntity.setDate(reportRequest.getDate());

        ReportEntity updatedEntity = jpaReportRepository.save(existingEntity);

        return mapToDomain(updatedEntity);
    }

    @Override
    public boolean deactivate(int id) {

        ReportEntity existingEntity = jpaReportRepository.findById(id).orElse(null);

        if (existingEntity == null) {
            return false;
        }

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

        return jpaReportRepository.findById(id)
                .map(this::mapToDomain);
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

        return jpaReportRepository.findByTitle(title)
                .map(this::mapToDomain);
    }

    @Override
    public PageResult<Report> findByContainsTitle(String title, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportEntity> reportPage =
                jpaReportRepository.findByTitleContainingIgnoreCase(title, pageable);

        List<ReportEntity> entities = reportPage.getContent();

        return new PageResult<>(

                entities.stream()
                        .map(this::mapToDomain)
                        .filter(Report::isActive)
                        .toList(),

                page,
                size,
                reportPage.getTotalElements(),
                reportPage.getTotalPages()
        );
    }

    @Override
    public PageResult<Report> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportEntity> reportPage = jpaReportRepository.findAll(pageable);

        List<ReportEntity> entities = reportPage.getContent();

        return new PageResult<>(

                entities.stream()
                        .map(this::mapToDomain)
                        .filter(Report::isActive)
                        .toList(),

                page,
                size,
                reportPage.getTotalElements(),
                reportPage.getTotalPages()
        );
    }

    @Override
    public PageResult<Report> findByContainsTitleLetterUseCase(String name, int page, int size) {
        return null;
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

        return new Report(

                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage(),
                entity.getCategory(),
                entity.getDate(),
                entity.isActive()

        );
    }

}

package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValueRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportFieldValue.ReportFieldValueEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Report.JPAReportRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportField.JPAReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportFieldValue.JPAReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Repository
public class ReportFieldValueRepositoryImpl implements ReportFieldValueRepository {

    private final JPAReportFieldValueRepository jpaReportFieldValueRepository;
    private final JPAReportFieldRepository jpaReportFieldRepository;
    private final JPAReportRepository jpaReportRepository;

    public ReportFieldValueRepositoryImpl(JPAReportFieldValueRepository jpaReportFieldValueRepository, JPAReportFieldRepository jpaReportFieldRepository, JPAReportRepository jpaReportRepository) {
        this.jpaReportFieldValueRepository = jpaReportFieldValueRepository;
        this.jpaReportFieldRepository = jpaReportFieldRepository;
        this.jpaReportRepository = jpaReportRepository;
    }


    @Override
    public ReportFieldValue create(ReportFieldValueRequest request) {

        ReportFieldValueEntity reportFieldValueEntity = new ReportFieldValueEntity();
        reportFieldValueEntity.setReportId(request.getReportId());
        reportFieldValueEntity.setReportFieldId(request.getReportFieldId());
        reportFieldValueEntity.setValue(request.getValue());
        reportFieldValueEntity.setActive(true);

        ReportFieldValueEntity savedEntity = jpaReportFieldValueRepository.save(reportFieldValueEntity);

        return getReportFieldValue(savedEntity);

    }

    @Override
    public ReportFieldValue update(ReportFieldValueRequest request, int id) {

        ReportFieldValueEntity reportFieldValueEntity = jpaReportFieldValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReportFieldValue with id " + id + " not found"));

        reportFieldValueEntity.setValue(request.getValue());

        ReportFieldValueEntity updatedEntity = jpaReportFieldValueRepository.save(reportFieldValueEntity);
        return getReportFieldValue(updatedEntity);
    }

    @Override
    public boolean deactive(int id) {
        ReportFieldValueEntity reportFieldValueEntity = jpaReportFieldValueRepository.findById(id).orElse(null);

        if (reportFieldValueEntity == null) {
            return false;
        }

        reportFieldValueEntity.setActive(false);
        jpaReportFieldValueRepository.save(reportFieldValueEntity);
        return true;
    }

    @Override
    public void activate(int id) {

        ReportFieldValueEntity reportFieldValueEntity = jpaReportFieldValueRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ReportFieldValue with id " + id + " not found"));

        if (reportFieldValueEntity.isActive()) {
            return;
        }

        reportFieldValueEntity.setActive(true);
        jpaReportFieldValueRepository.save(reportFieldValueEntity);

    }

    @Override
    public PageResult<ReportFieldValue> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportFieldValueEntity> entityPage = jpaReportFieldValueRepository.findByActive(true, pageable);

        List<ReportFieldValue> reportFieldValues = entityPage.getContent().stream()
                .map(this::getReportFieldValue)
                .toList();

        return new PageResult<>(
                reportFieldValues,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @Override
    public Optional<ReportFieldValue> findById(int id) {
        return Optional.empty();
    }

    @Override
    public PageResult<ReportFieldValue> findByReport(int reportId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<ReportFieldValueEntity> entityPage = jpaReportFieldValueRepository.findByReportIdAndActive(reportId, true, pageable);

        List<ReportFieldValue> reportFieldValues = entityPage.getContent().stream()
                .map(this::getReportFieldValue)
                .toList();

        return new PageResult<>(
                reportFieldValues,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @Override
    public Optional<ReportFieldValue> findByReportAndField(int reportId, int fieldId) {

        List<ReportFieldValueEntity> entities = jpaReportFieldValueRepository.findByReportFieldIdAndReportId(fieldId, reportId);

        if (entities.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(getReportFieldValue(entities.getFirst()));

    }

    @Override
    public PageResult<ReportFieldValue> findByReportAndIsActive(int reportId) {
        return null;
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public boolean existsByReport(int reportId) {
        return false;
    }

    @Override
    public boolean existsByReportAndIsActive(int reportId) {
        return false;
    }

    @Override
    public void updateFilePath(int  id, String filePath) {

        ReportFieldValueEntity reportFieldValueEntity = jpaReportFieldValueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReportFieldValue with id " + id + " not found"));

        reportFieldValueEntity.setValue(filePath);

        ReportFieldValueEntity updatedEntity = jpaReportFieldValueRepository.save(reportFieldValueEntity);

        if (!updatedEntity.getValue().equals(filePath)) {
            throw new BadRequestException("Error updating file path for ReportFieldValue with id " + id);
        }

    }

    @NonNull
    private ReportFieldValue getReportFieldValue(ReportFieldValueEntity entity) {
        Report report = jpaReportRepository.findById(entity.getReportId())
                .map(r -> new Report(r.getId(), r.getTitle(), r.getDescription(), getUrl(r.getImage()),
                        new ReportCategory(
                                r.getCategoryId(),
                                r.getCategory().getName(),
                                r.getCategory().getDate(),
                                r.getCategory().isActive(),
                                r.getCategory().getVisibility()
                        )
                        , r.getDate(), r.isActive()))
                .orElseThrow(() -> new BadRequestException("Report with id " + entity.getReportId() + " not found"));

        ReportFieldBuilder reportFieldBuilder = new ReportFieldBuilder();

        ReportField reportField = jpaReportFieldRepository.findById(entity.getReportFieldId())
                .map(rf -> reportFieldBuilder
                        .setId(rf.getId())
                        .setLabel(rf.getLabel())
                        .setOrder(rf.getField_order())
                        .setPlaceholder(rf.getPlaceholder())
                        .setHelpText(rf.getHelpText())
                        .setDefaultValue(rf.getDefaultValue())
                        .setMultiple(rf.isMultiple())
                        .setActive(rf.isActive())
                        .setType(
                                new Type(
                                        rf.getTypeId(),
                                        rf.getType().getName(),
                                        rf.getType().isActive()
                                )
                        )
                        .setReportCategory(
                                new ReportCategory(
                                        rf.getReportCategoryId(),
                                        rf.getReportCategory().getName(),
                                        rf.getReportCategory().getDate(),
                                        rf.getReportCategory().isActive(),
                                        rf.getReportCategory().getVisibility()
                                )
                        )
                        .getResult())
                .orElseThrow(() -> new BadRequestException("ReportField with id " + entity.getReportFieldId() + " not found"));



        return new ReportFieldValue(
                entity.getId(),
                entity.getValue(),
                reportField,
                report,
                entity.getDate(),
                entity.isActive()
        );
    }


    private @Nullable URL getUrl(String url) {
        try {
            if (url != null && !url.isEmpty()) {
                return URI.create(url).toURL();
            }
        } catch (Exception e) {
            throw new BadRequestException("Error converting image URL: " + e.getMessage());
        }

        return null;
    }
}

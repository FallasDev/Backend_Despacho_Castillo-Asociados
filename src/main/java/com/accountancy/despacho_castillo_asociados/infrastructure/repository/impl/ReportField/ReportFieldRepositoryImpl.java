package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportField.ReportFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ReportField.JPAReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportFieldRepositoryImpl implements ReportFieldRepository {

    private final JPAReportFieldRepository jpaReportFieldRepository;

    public ReportFieldRepositoryImpl(JPAReportFieldRepository jpaReportFieldRepository) {
        this.jpaReportFieldRepository = jpaReportFieldRepository;
    }

    @Override
    public ReportField create(ReportFieldRequest request, Type type, ReportCategory reportCategory) {

        ReportFieldEntity reportFieldEntity = new ReportFieldEntity();
        setEntities(request, reportFieldEntity, type, reportCategory);
        reportFieldEntity.setActive(true);

        ReportFieldEntity savedReportFieldEntity = jpaReportFieldRepository.save(reportFieldEntity);

        Optional<ReportField> createdReportField = getReportFieldBuilder(savedReportFieldEntity);

        return createdReportField.orElse(null);
    }

    private void setEntities(ReportFieldRequest request, ReportFieldEntity reportFieldEntity, Type type, ReportCategory reportCategory) {
        reportFieldEntity.setLabel(request.getLabel());
        reportFieldEntity.setField_order(request.getOrder());
        reportFieldEntity.setPlaceholder(request.getPlaceholder());
        reportFieldEntity.setHelpText(request.getHelpText());
        reportFieldEntity.setDefaultValue(request.getDefaultValue());
        reportFieldEntity.setMultiple(request.isMultiple());
        reportFieldEntity.setTypeId(request.getTypeId());
        reportFieldEntity.setReportCategoryId(request.getReportCategoryId());
        reportFieldEntity.setType(
                new TypeEntity(
                        type.getId(),
                        type.getName(),
                        type.isActive()
                )
        );
        reportFieldEntity.setReportCategory(
                new ReportCategoryEntity(
                        reportCategory.getId(),
                        reportCategory.getName(),
                        reportCategory.getDate(),
                        reportCategory.isActive(),
                        reportCategory.getVisibility()
                )
        );

    }

    @Override
    public ReportField update(ReportFieldRequest request, int id, Type type, ReportCategory reportCategory) {

        ReportFieldEntity existingReportFieldEntity = jpaReportFieldRepository.findById(id).orElse(null);

        if (existingReportFieldEntity == null || !existingReportFieldEntity.isActive()) {
            return null;
        }

        setEntities(request, existingReportFieldEntity, type, reportCategory);

        ReportFieldEntity updatedReportFieldEntity = jpaReportFieldRepository.save(existingReportFieldEntity);

        Optional<ReportField> updatedReportField = getReportFieldBuilder(updatedReportFieldEntity);

        return updatedReportField.orElse(null);
    }

    @Override
    public boolean deactive(int id) {

        ReportFieldEntity existingReportFieldEntity = jpaReportFieldRepository.findById(id).orElse(null);

        if (existingReportFieldEntity == null || !existingReportFieldEntity.isActive()) {
            return false;
        }

        existingReportFieldEntity.setActive(false);
        jpaReportFieldRepository.save(existingReportFieldEntity);
        return true;
    }

    @Override
    public void activate(int id) {
        ReportFieldEntity existingReportFieldEntity = jpaReportFieldRepository.findById(id).orElse(null);

        if (existingReportFieldEntity == null) {
            return;
        }

        existingReportFieldEntity.setActive(true);
        System.out.println("Activating ReportField with id: " + id);
        jpaReportFieldRepository.save(existingReportFieldEntity);
    }

    @Override
    public PageResult<ReportField> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportFieldEntity> reportFieldEntityPage = jpaReportFieldRepository.findAllByActive(true,pageable);

        return getPageResult(reportFieldEntityPage);
    }

    @Override
    public Optional<ReportField> findById(int id) {

        ReportFieldEntity reportFieldEntity = jpaReportFieldRepository.findById(id).orElse(null);

        if (reportFieldEntity == null || !reportFieldEntity.isActive()) {
            return Optional.empty();
        }

        return getReportFieldBuilder(reportFieldEntity);

    }

    @Override
    public Optional<ReportField> findByLabelAndIsInactive(String label) {

        Optional<ReportFieldEntity> reportFieldEntities = jpaReportFieldRepository.findByLabelAndActive(label, false);

        return reportFieldEntities.flatMap(this::getReportFieldBuilder);

    }

    @Override
    public PageResult<ReportField> findByReportCategory(int page, int size,int reportCategoryId) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<ReportFieldEntity> reportFieldEntityPage = jpaReportFieldRepository.findByReportCategoryIdAndActive(reportCategoryId, true, pageable);

        return getPageResult(reportFieldEntityPage);

    }


    @Override
    public boolean existsById(int id) {
        return jpaReportFieldRepository.existsById(id);
    }

    @Override
    public boolean existsByLabel(String label) {
        return jpaReportFieldRepository.existsByLabel(label);
    }

    @Override
    public boolean existsByLabelAndIsActive(String label) {
        return jpaReportFieldRepository.existsByLabelAndActive(label, true);
    }

    private Optional<ReportField> getReportFieldBuilder(ReportFieldEntity reportFieldEntity) {
        ReportFieldBuilder reportFieldBuilder = new ReportFieldBuilder();
        reportFieldBuilder.setId(reportFieldEntity.getId());
        reportFieldBuilder.setLabel(reportFieldEntity.getLabel());
        reportFieldBuilder.setOrder(reportFieldEntity.getField_order());
        reportFieldBuilder.setPlaceholder(reportFieldEntity.getPlaceholder());
        reportFieldBuilder.setHelpText(reportFieldEntity.getHelpText());
        reportFieldBuilder.setDefaultValue(reportFieldEntity.getDefaultValue());
        reportFieldBuilder.setMultiple(reportFieldEntity.isMultiple());
        reportFieldBuilder.setActive(reportFieldEntity.isActive());
        reportFieldBuilder.setType(
                new Type(reportFieldEntity.getTypeId(),
                        reportFieldEntity.getType().getName(),
                        reportFieldEntity.getType().isActive())
        );
        reportFieldBuilder.setReportCategory(
                new ReportCategory(
                        reportFieldEntity.getReportCategoryId(),
                        reportFieldEntity.getReportCategory().getName(),
                        reportFieldEntity.getReportCategory().getDate(),
                        reportFieldEntity.getReportCategory().isActive(),
                        reportFieldEntity.getReportCategory().getVisibility()
                )
        );
        return Optional.of(reportFieldBuilder.getResult());
    }

    @NonNull
    private PageResult<ReportField> getPageResult(Page<ReportFieldEntity> reportFieldEntityPage) {

        List<ReportField> reportFields = reportFieldEntityPage.getContent().stream()
                .map(this::getReportFieldBuilder)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return new PageResult<>(
                reportFields,
                reportFieldEntityPage.getNumber(),
                reportFieldEntityPage.getSize(),
                reportFieldEntityPage.getTotalElements(),
                reportFieldEntityPage.getTotalPages()
        );
    }
}

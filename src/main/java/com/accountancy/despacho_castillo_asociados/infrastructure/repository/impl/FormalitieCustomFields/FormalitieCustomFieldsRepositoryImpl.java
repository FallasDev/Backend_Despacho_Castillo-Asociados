package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.FormalitieCustomFields.FormalitieCustomFieldsEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.CustomField.JPACustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie.JPAFormalitieRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.FormalitieCustomFields.JPAFormalitieCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FormalitieCustomFieldsRepositoryImpl implements FormalitieCustomFieldRepository {

    private final JPAFormalitieCustomFieldsRepository jpaRepository;
    private final JPACustomFieldRepository jpaCustomFieldRepository;
    private final JPAFormalitieRepository jpaFormalitieRepository;

    public FormalitieCustomFieldsRepositoryImpl(JPAFormalitieCustomFieldsRepository jpaRepository, JPACustomFieldRepository jpaCustomFieldRepository, JPAFormalitieRepository jpaFormalitieRepository) {
        this.jpaRepository = jpaRepository;
        this.jpaCustomFieldRepository = jpaCustomFieldRepository;
        this.jpaFormalitieRepository = jpaFormalitieRepository;
    }

    @Override
    public FormalitieCustomField create(FormalitieCustomFieldRequest request) {

        FormalitieCustomFieldsEntity entity = new FormalitieCustomFieldsEntity();
        entity.setFormalitieId(request.getFormalitieId());
        entity.setCustomFieldId(request.getCustomFieldId());
        entity.setValue(request.getValue());
        entity.setActive(true);
        FormalitieCustomFieldsEntity savedEntity = jpaRepository.save(entity);

        return getFormalitieCustomField(savedEntity);
    }

    @Override
    public FormalitieCustomField update(FormalitieCustomFieldRequest request, int id) {

        FormalitieCustomFieldsEntity entity     = jpaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("FormalitieCustomField with id " + id + " not found"));

        entity.setValue(request.getValue());

        FormalitieCustomFieldsEntity updatedEntity = jpaRepository.save(entity);
        return getFormalitieCustomField(updatedEntity);

    }

    @Override
    public boolean deactivate(int id) {

        FormalitieCustomFieldsEntity entity = jpaRepository.findById(id).orElse(null);

        if (entity == null) {
            return false;
        }

        entity.setActive(false);
        jpaRepository.save(entity);
        return true;

    }

    @Override
    @Transactional
    public boolean deactivateByCustomFieldId(int customFieldId) {
        int updatedRows = jpaRepository.deactivateByCustomField(customFieldId);
        return updatedRows > 0;
    }

    @Override
    public void activate(int id) {

        FormalitieCustomFieldsEntity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("FormalitieCustomField with id " + id + " not found"));

        if (entity.isActive()) {
            return;
        }

        entity.setActive(true);
        jpaRepository.save(entity);

    }

    @Override
    public Optional<FormalitieCustomField> findById(int id) {
        return jpaRepository.findById(id).map(this::getFormalitieCustomField);
    }

    @Override
    public PageResult<FormalitieCustomField> findAll(int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<FormalitieCustomFieldsEntity> entityPage = jpaRepository.findByActive(true, pageable);

        List<FormalitieCustomFieldsEntity> entities = entityPage.getContent();

        return new PageResult<>(
                entities.stream().map(this::getFormalitieCustomField).toList(),
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );

    }

    @Override
    public PageResult<FormalitieCustomField> findByFormalitieId(int formalitieId, int page, int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<FormalitieCustomFieldsEntity> entityPage = jpaRepository.findByFormalitieId(formalitieId, pageable);

        List<FormalitieCustomFieldsEntity> entities = entityPage.getContent();

        List<FormalitieCustomField> formalitieCustomFields = entities.stream().map(this::getFormalitieCustomField)
                .filter(FormalitieCustomField::isActive).toList();

        return new PageResult<>(
                formalitieCustomFields,
                entityPage.getNumber(),
                entityPage.getSize(),
                formalitieCustomFields.size(),
                entityPage.getTotalPages()
        );

    }

    @Override
    public Optional<FormalitieCustomField> findByFormalitieIdAndCustomFieldId(int formalitieId, int customFieldId) {

        Optional<FormalitieCustomFieldsEntity> entity = jpaRepository.findByFormalitieIdAndCustomFieldIdAndActive(formalitieId, customFieldId, true);

        return entity.map(this::getFormalitieCustomField);

    }

    @Override
    public Optional<FormalitieCustomField> findByFormalitieIdAndCustomFieldIdAndIsInactive(int formalitieId, int customFieldId) {
        Optional<FormalitieCustomFieldsEntity> entity = jpaRepository.findByFormalitieIdAndCustomFieldIdAndActive(formalitieId, customFieldId, false);

        return entity.map(this::getFormalitieCustomField);
    }


    @NonNull
    private FormalitieCustomField getFormalitieCustomField(@NonNull FormalitieCustomFieldsEntity entity) {

        Formalitie formalitie = jpaFormalitieRepository.findById(entity.getFormalitieId())
                .map(f -> new Formalitie(
                        f.getId(),
                        new DomainService(
                                f.getService().getId(),
                                f.getService().getName(),
                                f.getService().getDescription(),
                                f.getService().isActive()
                        ),
                        null,
                        null,
                        FormalitiesState.fromId(f.getState()),
                        f.getCreatedAt()
                ))
                .orElseThrow(() -> new BadRequestException("Formalitie with id " + entity.getFormalitieId() + " not found"));

        CustomFieldBuilder customFieldBuilder = new CustomFieldBuilder();

        CustomField customField = jpaCustomFieldRepository.findById(entity.getCustomFieldId())
                .map((val) -> customFieldBuilder
                        .setId(entity.getCustomFieldId())
                        .setName(val.getName())
                        .setIsRequired(val.isRequired())
                        .setIsActive(val.isActive())
                        .setIsExclusive(val.isExclusive())
                        .getResult(
                ))
                .orElseThrow(() -> new BadRequestException("CustomField with id " + entity.getCustomFieldId() + " not found"));

        return new FormalitieCustomField(
                entity.getId(),
                formalitie,
                customField,
                entity.getValue(),
                entity.isActive()
        );
    }
}

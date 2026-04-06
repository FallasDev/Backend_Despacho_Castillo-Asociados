package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.FormalitieCustomFields.FormalitieCustomFieldsEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField.ServiceCustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
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

        return null;
    }

    @Override
    public List<FormalitieCustomField> createBatch(List<FormalitieCustomField> formalitieCustomFields) {

        List<FormalitieCustomFieldsEntity> entities = formalitieCustomFields.stream().map(fcf -> {

            CustomFieldEntity customFieldEntity = jpaCustomFieldRepository.findById(fcf.getCustomField().getId())
                    .orElseThrow(() -> new BadRequestException("CustomField with id " + fcf.getCustomField().getId() + " not found"));

            FormalitieEntity formalitieEntity = jpaFormalitieRepository.findById(fcf.getFormalitie().getId())
                    .orElseThrow(() -> new BadRequestException("Formalitie with id " + fcf.getFormalitie().getId() + " not found"));

            return new FormalitieCustomFieldsEntity(
                    customFieldEntity,
                    formalitieEntity,
                    true,
                    fcf.getValue()
                );
                }
        ).toList();

        return jpaRepository.saveAll(entities).stream().map(this::getFormalitieCustomField).toList();
    }

    @Override
    public List<FormalitieCustomField> updateBatch(List<FormalitieCustomField> formalitieCustomFields) {

        List<FormalitieCustomFieldsEntity> entities = formalitieCustomFields.stream().map(fcf -> {

            System.out.println(fcf);

            FormalitieCustomFieldsEntity entity = jpaRepository.findById(fcf.getId())
                    .orElseThrow(() -> new BadRequestException("FormalitieCustomField with id " + fcf.getId() + " not found"));

            entity.setValue(fcf.getValue());

            return entity;
        }).toList();

        return jpaRepository.saveAll(entities).stream().map(this::getFormalitieCustomField).toList();
    }

    @Override
    public FormalitieCustomField update(FormalitieCustomFieldRequest request, int id) {

        FormalitieCustomFieldsEntity entity     = jpaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("FormalitieCustomField with id " + id + " not found"));

//        entity.setValue(request.getValue());

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
    public Optional<FormalitieCustomField> findByCustomFieldIdAndIsInactive(int customFieldId) {
        return Optional.empty();
    }




    @Override
    public void updateFilePath(int id, String publicId) {
        FormalitieCustomFieldsEntity entity     = jpaRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("FormalitieCustomField with id " + id + " not found"));

        entity.setValue(publicId);

        FormalitieCustomFieldsEntity updatedEntity = jpaRepository.save(entity);

        if (!updatedEntity.getValue().equals(publicId)) {
            throw new BadRequestException("Failed to update file path for FormalitieCustomField with id " + id);
        }
    }


    @NonNull
    private FormalitieCustomField getFormalitieCustomField(@NonNull FormalitieCustomFieldsEntity entity) {

        Formalitie formalitie = jpaFormalitieRepository.findById(entity.getFormalitie().getId())
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
                        f.getCreatedAt(),
                        f.getTemplateId()
                ))
                .orElseThrow(() -> new BadRequestException("Formalitie with id " + entity.getId() + " not found"));

        CustomFieldBuilder customFieldBuilder = new CustomFieldBuilder();

        CustomField customField = jpaCustomFieldRepository.findById(entity.getCustomField().getId())
                .map((val) -> customFieldBuilder
                        .setId(entity.getCustomField().getId())
                        .setName(val.getName())
                        .setIsRequired(val.isRequired())
                        .setIsActive(val.isActive())
                        .setIsExclusive(val.isExclusive())
                        .setDefaultValue(val.getDefaultValue())
                        .setHelpText(val.getHelpText())
                        .setPlaceholder(val.getPlaceholder())
                        .setType(
                                new Type(
                                        val.getType().getId(),
                                        val.getType().getName(),
                                        val.getType().isActive()
                                )
                        )
                        .getResult(
                ))
                .orElseThrow(() -> new BadRequestException("CustomField with id " + entity.getCustomField().getId() + " not found"));

        return new FormalitieCustomField(
                entity.getId(),
                customField,
                formalitie,
                entity.getValue(),
                entity.isActive()
        );
    }
}

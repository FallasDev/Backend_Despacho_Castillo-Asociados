package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;
    private final CustomFieldRepository customFieldRepository;
    private final FormalitieRepository formalitieRepository;

    public CreateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldRepository,
                                               CustomFieldRepository customFieldRepository,
                                               FormalitieRepository formalitieRepository) {
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
        this.customFieldRepository = customFieldRepository;
        this.formalitieRepository = formalitieRepository;
    }

    public FormalitieCustomField execute(FormalitieCustomFieldRequest request) {

        if (request == null) {
            throw new BadRequestException("Formalitie Custom Field request cannot be null");
        }

        Optional<FormalitieCustomField> existingRelation = formalitieCustomFieldRepository
                .findByFormalitieIdAndCustomFieldId(request.getFormalitieId(), request.getCustomFieldId());

        Optional<Formalitie> formalitie = formalitieRepository.findById(request.getFormalitieId());
        Optional<CustomField> customField = customFieldRepository.findById(request.getCustomFieldId());

        if (existingRelation.isPresent() && existingRelation.get().isActive()) {
            throw new BadRequestException("Formalitie Custom Field relation already exists for " +
                    "formalitie ID " + request.getFormalitieId() + " and custom field ID " + request.getCustomFieldId());
        }

        if (formalitie.isEmpty()) {
            throw new BadRequestException("Formalitie with ID " + request.getFormalitieId() + " does not exist");
        }

        if (customField.isEmpty()) {
            throw new BadRequestException("Custom Field with ID " + request.getCustomFieldId() + " does not exist");
        }

        if (formalitie.get().getState().equals(FormalitiesState.REFUSED) || formalitie.get().getState().equals(FormalitiesState.COMPLETED)) {
            throw new BadRequestException("Formalitie with ID " + request.getFormalitieId() + " is not active");
        }

        if (!customField.get().isActive()) {
            throw new BadRequestException("Custom Field with ID " + request.getCustomFieldId() + " is not active");
        }

        Optional<FormalitieCustomField> inactive = formalitieCustomFieldRepository
                .findByFormalitieIdAndCustomFieldIdAndIsInactive(request.getFormalitieId(), request.getCustomFieldId());

        if (inactive.isPresent()) {
            FormalitieCustomField reactivated = inactive.get();
            reactivated.setActive(true);
            formalitieCustomFieldRepository.activate(reactivated.getId());
            return reactivated;
        }

        return formalitieCustomFieldRepository.create(request);

    }

}

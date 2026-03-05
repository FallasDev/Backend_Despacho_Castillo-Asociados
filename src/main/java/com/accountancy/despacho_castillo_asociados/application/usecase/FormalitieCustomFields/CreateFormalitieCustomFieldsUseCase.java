package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;
    private final CustomFieldRepository customFieldRepository;
    private final FormalitieRepository formalitieRepository;
    private final Messages messages;

    public CreateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldRepository,
                                               CustomFieldRepository customFieldRepository,
                                               FormalitieRepository formalitieRepository,
                                               Messages messages) {
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
        this.customFieldRepository = customFieldRepository;
        this.formalitieRepository = formalitieRepository;
        this.messages = messages;
    }

    public FormalitieCustomField execute(FormalitieCustomFieldRequest request) {

        if (request == null) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.cannot_be_null"));
        }

        System.out.println("test 1");

        Optional<FormalitieCustomField> existingRelation = formalitieCustomFieldRepository
                .findByFormalitieIdAndCustomFieldId(request.getFormalitieId(), request.getCustomFieldId());

        System.out.println("test 2");

        Optional<Formalitie> formalitie = formalitieRepository.findById(request.getFormalitieId());
        Optional<CustomField> customField = customFieldRepository.findById(request.getCustomFieldId());

        if (existingRelation.isPresent() && existingRelation.get().isActive()) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.name.relation.already.exists"));
        }

        System.out.println("test 3");

        if (formalitie.isEmpty()) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.formality.not_found",
                    new Object[]{request.getFormalitieId()}));
        }

        if (customField.isEmpty()) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.not_found",
                    new Object[]{request.getCustomFieldId()}));
        }

        if (formalitie.get().getState().equals(FormalitiesState.REFUSED) || formalitie.get().getState().equals(FormalitiesState.COMPLETED)) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.invalidstatus",
                    new Object[]{request.getFormalitieId(), formalitie.get().getState()}));
        }

        if (!customField.get().isActive()) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.not_found",
                    new Object[]{request.getCustomFieldId()}));
        }

        Optional<FormalitieCustomField> inactive = formalitieCustomFieldRepository
                .findByFormalitieIdAndCustomFieldIdAndIsInactive(request.getFormalitieId(), request.getCustomFieldId());

        if (inactive.isPresent()) {
            FormalitieCustomField reactivated = inactive.get();
            reactivated.setActive(true);
            formalitieCustomFieldRepository.activate(reactivated.getId());
            return reactivated;
        }

        System.out.println("Creating new FormalitieCustomField with request in usecase: " + request);

        FormalitieCustomField created = formalitieCustomFieldRepository.create(request);

        if (created == null) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.failed"));
        }

        return created;

    }

}

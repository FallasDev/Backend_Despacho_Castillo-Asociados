package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository formalitieCustomFieldsRepository;

    public UpdateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldsRepository) {
        this.formalitieCustomFieldsRepository = formalitieCustomFieldsRepository;
    }

    public FormalitieCustomField execute(FormalitieCustomFieldRequest request, int id) {

        if (request == null) {
            throw new BadRequestException("Formalitie Custom Field cannot be null");
        }

        Optional<FormalitieCustomField> existingFormalitie = formalitieCustomFieldsRepository.findByFormalitieIdAndCustomFieldId(
                request.getFormalitieId(),
                request.getCustomFieldId());

        if (existingFormalitie.isEmpty()) {
            throw new BadRequestException("Formalitie Custom Field relation does not exist for formalitie ID"
                    + request.getFormalitieId() + " and custom field ID " + request.getCustomFieldId());
        }

        if (!existingFormalitie.get().isActive()) {
            throw new BadRequestException("Formalitie Custom Field relation for formalitie ID "
                    + request.getFormalitieId() + " and custom field ID " + request.getCustomFieldId() + " is not active");
        }

        FormalitieCustomField updated = formalitieCustomFieldsRepository.update(request,id);

        if (updated == null) {
            throw new BadRequestException("Formalitie Custom Field relation to update with id " + id);
        }

        return updated;

    }


}

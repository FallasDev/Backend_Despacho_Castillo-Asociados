package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository formalitieCustomFieldsRepository;
    private final Messages messages;

    public UpdateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldsRepository, Messages messages) {
        this.formalitieCustomFieldsRepository = formalitieCustomFieldsRepository;
        this.messages = messages;
    }

    public FormalitieCustomField execute(FormalitieCustomFieldRequest request, int id) {

//        if (request == null) {
//            throw new BadRequestException(messages.get("formalitycustomfield.exception.update.cannot_be_null"));
//        }
//
//        Optional<FormalitieCustomField> existingFormalitie = formalitieCustomFieldsRepository.findByFormalitieIdAndCustomFieldId(
//                request.getFormalitieId(),
//                request.getCustomFieldId());
//
//        if (existingFormalitie.isEmpty()) {
//            throw new BadRequestException(messages.get("formalitycustomfield.exception.update.notfound",
//                    new Object[]{request.getFormalitieId(), request.getCustomFieldId()}));
//        }
//
//        if (!existingFormalitie.get().isActive()) {
//            throw new BadRequestException(messages.get("formalitycustomfield.exception.update.notfound",
//                    new Object[]{request.getFormalitieId(), request.getCustomFieldId()}));
//        }
//
//        FormalitieCustomField updated = formalitieCustomFieldsRepository.update(request,id);
//
//
//        if (updated == null) {
//            throw new BadRequestException(messages.get("formalitycustomfield.exception.update.failed"));
//        }

        return null;

    }


}

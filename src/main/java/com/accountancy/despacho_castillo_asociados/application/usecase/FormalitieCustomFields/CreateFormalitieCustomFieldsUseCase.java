package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldWithValue;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.List;
import java.util.Optional;

public class CreateFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;
    private final CustomFieldRepository customFieldRepository;
    private final Messages messages;

    public CreateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldRepository,
                                               CustomFieldRepository customFieldRepository,
                                               Messages messages) {
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
        this.customFieldRepository = customFieldRepository;
        this.messages = messages;
    }

    public FormalitieCustomField execute(FormalitieCustomFieldRequest request) {

//        if (request == null) {
//            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.cannot_be_null"));
//        }
//
//        for (FormalitieCustomFieldWithValue customFieldWithValue : request.getCustomFields()) {
//
//            Optional<CustomField> customField = customFieldRepository.findById(customFieldWithValue.getCustomFieldId());
//
//            if (customField.isEmpty()) {
//                throw new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.not_found",
//                        new Object[]{customFieldWithValue.getCustomFieldId()}));
//            }
//
//            if (!customField.get().isActive()) {
//                throw new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.not_found",
//                        new Object[]{customFieldWithValue.getCustomFieldId()}));
//            }
//
//            if (!customField.get().isActive()) {
//                throw new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.not_found",
//                        new Object[]{customFieldWithValue.getCustomFieldId()}));
//            }
//
//            Optional<FormalitieCustomField> inactive = formalitieCustomFieldRepository
//                    .findByCustomFieldIdAndIsInactive(customFieldWithValue.getCustomFieldId());
//
//            if (inactive.isPresent()) {
//                FormalitieCustomField reactivatedFormalitieCustomField = inactive.get();
//                reactivatedFormalitieCustomField.setActive(true);
//                formalitieCustomFieldRepository.activate(reactivatedFormalitieCustomField.getId());
//                return reactivatedFormalitieCustomField;
//            }
//
//        }
//
//
//        List<FormalitieCustomField> created = formalitieCustomFieldRepository.createBatch(request);
//
//        if (created == null) {
//            throw new BadRequestException(messages.get("formalitycustomfield.exception.create.failed"));
//        }

        return null;

    }

}

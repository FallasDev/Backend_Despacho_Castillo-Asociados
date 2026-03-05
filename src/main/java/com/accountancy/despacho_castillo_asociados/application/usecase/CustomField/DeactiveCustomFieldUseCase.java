package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.FormalitieCustomFields.FormalitieCustomFieldsEntity;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class DeactiveCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final ServiceCustomFieldsRepository serviceCustomFieldsRepository;
    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;

    private final Messages messages;

    public DeactiveCustomFieldUseCase(CustomFieldRepository customFieldRepository,
                                         ServiceCustomFieldsRepository serviceCustomFieldsRepository,
                                         FormalitieCustomFieldRepository formalitieCustomFieldRepository
                                        ,Messages messages) {
        this.customFieldRepository = customFieldRepository;
        this.serviceCustomFieldsRepository = serviceCustomFieldsRepository;
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean result = customFieldRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException(messages.get("customfield.exception.deactive", new Object[]{id} ) );
        }

        boolean serviceCustomFieldsResult = serviceCustomFieldsRepository.deactivateByCustomFieldId(id);

        if (!serviceCustomFieldsResult) {
            throw new BadRequestException(messages.get("customfield.exception.deactive.service_custom_fields", new Object[]{id} ) );
        }

        boolean formalitieCustomFieldsResult = formalitieCustomFieldRepository.deactivateByCustomFieldId(id);

        if (!formalitieCustomFieldsResult) {
            throw new BadRequestException(messages.get("customfield.exception.deactive.formality_custom_fields", new Object[]{id} ) );
        }
    }

}

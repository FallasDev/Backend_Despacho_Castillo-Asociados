package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindFormalitiesCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository repository;

    public FindFormalitiesCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldRepository) {
        this.repository = formalitieCustomFieldRepository;
    }

    public PageResult<FormalitieCustomField> execute(int formalitieId, int page, int size) {

        if (formalitieId > 0) {

            PageResult<FormalitieCustomField> formalitieCustomFieldPageResult = repository.findByFormalitieId(formalitieId, page, size);

            if (formalitieCustomFieldPageResult.content().isEmpty()) {
                throw new EmptyListException("No custom fields found for formalitie ID: " + formalitieId);
            }

            return formalitieCustomFieldPageResult;

        }

        PageResult<FormalitieCustomField> formalitieCustomFields = repository.findAll(page, size);
        if (formalitieCustomFields.content().isEmpty()) {
            throw new EmptyListException("No custom fields found");
        }
        return formalitieCustomFields;

    }


}

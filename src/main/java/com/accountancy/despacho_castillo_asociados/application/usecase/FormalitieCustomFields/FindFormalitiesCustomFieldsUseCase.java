package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindFormalitiesCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository repository;
    private final Messages messages;

    public FindFormalitiesCustomFieldsUseCase(FormalitieCustomFieldRepository formalitieCustomFieldRepository, Messages messages) {
        this.repository = formalitieCustomFieldRepository;
        this.messages = messages;
    }

    public PageResult<FormalitieCustomField> execute(int formalitieId, int page, int size) {

        if (formalitieId > 0) {

            PageResult<FormalitieCustomField> formalitieCustomFieldPageResult = repository.findByFormalitieId(formalitieId, page, size);

            if (formalitieCustomFieldPageResult.content().isEmpty()) {
                throw new EmptyListException(messages.get("formalitycustomfield.exception.fetch.by_formality_id.notfound", new Object[]{formalitieId}));
            }

            return formalitieCustomFieldPageResult;

        }

        PageResult<FormalitieCustomField> formalitieCustomFields = repository.findAll(page, size);
        if (formalitieCustomFields.content().isEmpty()) {
            throw new EmptyListException(messages.get("formalitycustomfield.exception.fetch.all.none"));
        }
        return formalitieCustomFields;

    }


}

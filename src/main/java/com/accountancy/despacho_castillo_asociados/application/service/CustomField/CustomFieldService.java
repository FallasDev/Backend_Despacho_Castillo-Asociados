package com.accountancy.despacho_castillo_asociados.application.service.CustomField;

import com.accountancy.despacho_castillo_asociados.application.usecase.CustomField.*;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomFieldService {

    private final CreateCustomFieldUseCase createCustomFieldUseCase;
    private final FindAllCustomFieldUseCase findAllCustomFieldUseCase;
    private final DeactiveCustomFieldUseCase deactiveCustomFieldUseCase;
    private final UpdateCustomFieldUseCase updateCustomFieldUseCase;
    private final FindByIdCustomFieldUseCase findByIdCustomFieldUseCase;

    public CustomFieldService(
            CreateCustomFieldUseCase createCustomFieldUseCase,
            FindAllCustomFieldUseCase findAllCustomFieldUseCase,
            DeactiveCustomFieldUseCase deactiveCustomFieldUseCase,
            UpdateCustomFieldUseCase updateCustomFieldUseCase,
            FindByIdCustomFieldUseCase findByIdCustomFieldUseCase
    ) {
        this.createCustomFieldUseCase = createCustomFieldUseCase;
        this.findAllCustomFieldUseCase = findAllCustomFieldUseCase;
        this.deactiveCustomFieldUseCase = deactiveCustomFieldUseCase;
        this.updateCustomFieldUseCase = updateCustomFieldUseCase;
        this.findByIdCustomFieldUseCase = findByIdCustomFieldUseCase;
    }

    public CustomField createCustomField(CustomFieldRequest customFieldRequest) {
        return createCustomFieldUseCase.execute(customFieldRequest);
    }

    public CustomField updateCustomField(CustomFieldRequest customFieldRequest, int id) {
        return updateCustomFieldUseCase.execute(customFieldRequest, id);
    }

    public void deactiveCustomField(int id) {
        deactiveCustomFieldUseCase.execute(id);
    }

    public List<CustomField> findAllCustomFields() {
        return findAllCustomFieldUseCase.execute();
    }

    public CustomField findByIdCustomField(int id) {
        return findByIdCustomFieldUseCase.execute(id);
    }

}

package com.accountancy.despacho_castillo_asociados.application.service.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.CreateFormalitieCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.DeactiveFormalitieCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.FindFormalitiesCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.UpdateFormalitieCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class FormalitieCustomFieldsService {

    private CreateFormalitieCustomFieldsUseCase createFormalitieCustomFieldsUseCase;
    private UpdateFormalitieCustomFieldsUseCase updateFormalitieCustomFieldsUseCase;
    private FindFormalitiesCustomFieldsUseCase findFormalitiesCustomFieldsUseCase;
    private DeactiveFormalitieCustomFieldsUseCase deactiveFormalitieCustomFieldsUseCase;

    public FormalitieCustomFieldsService(
        CreateFormalitieCustomFieldsUseCase createFormalitieCustomFieldsUseCase,
        UpdateFormalitieCustomFieldsUseCase updateFormalitieCustomFieldsUseCase,
        FindFormalitiesCustomFieldsUseCase findFormalitiesCustomFieldsUseCase,
        DeactiveFormalitieCustomFieldsUseCase deactiveFormalitieCustomFieldsUseCase
    ) {
        this.createFormalitieCustomFieldsUseCase = createFormalitieCustomFieldsUseCase;
        this.updateFormalitieCustomFieldsUseCase = updateFormalitieCustomFieldsUseCase;
        this.findFormalitiesCustomFieldsUseCase = findFormalitiesCustomFieldsUseCase;
        this.deactiveFormalitieCustomFieldsUseCase = deactiveFormalitieCustomFieldsUseCase;
    }

    public FormalitieCustomField create(FormalitieCustomFieldRequest request) {
        return createFormalitieCustomFieldsUseCase.execute(request);
    }

    public FormalitieCustomField update(int id, FormalitieCustomFieldRequest request) {
        return updateFormalitieCustomFieldsUseCase.execute(request, id);
    }

    public void deactivate(int id) {
        deactiveFormalitieCustomFieldsUseCase.execute(id);
    }

    public PageResult<FormalitieCustomField> find(int formalitieId, int page, int size) {
        return findFormalitiesCustomFieldsUseCase.execute(formalitieId, page, size);
    }

}

package com.accountancy.despacho_castillo_asociados.application.service.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.*;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FormalitieCustomFieldsService {

    private CreateFormalitieCustomFieldsUseCase createFormalitieCustomFieldsUseCase;
    private UpdateFormalitieCustomFieldsUseCase updateFormalitieCustomFieldsUseCase;
    private FindFormalitiesCustomFieldsUseCase findFormalitiesCustomFieldsUseCase;
    private DeactiveFormalitieCustomFieldsUseCase deactiveFormalitieCustomFieldsUseCase;
    private UploadFileFormalitieCustomFieldUseCase uploadFileFormalitieCustomFieldUseCase;

    public FormalitieCustomFieldsService(
        CreateFormalitieCustomFieldsUseCase createFormalitieCustomFieldsUseCase,
        UpdateFormalitieCustomFieldsUseCase updateFormalitieCustomFieldsUseCase,
        FindFormalitiesCustomFieldsUseCase findFormalitiesCustomFieldsUseCase,
        DeactiveFormalitieCustomFieldsUseCase deactiveFormalitieCustomFieldsUseCase,
        UploadFileFormalitieCustomFieldUseCase uploadFileFormalitieCustomFieldUseCase
    ) {
        this.createFormalitieCustomFieldsUseCase = createFormalitieCustomFieldsUseCase;
        this.updateFormalitieCustomFieldsUseCase = updateFormalitieCustomFieldsUseCase;
        this.findFormalitiesCustomFieldsUseCase = findFormalitiesCustomFieldsUseCase;
        this.deactiveFormalitieCustomFieldsUseCase = deactiveFormalitieCustomFieldsUseCase;
        this.uploadFileFormalitieCustomFieldUseCase = uploadFileFormalitieCustomFieldUseCase;
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

    public boolean uploadFile(int formalitieCustomFieldId, MultipartFile file, String filename) throws IOException {
        return uploadFileFormalitieCustomFieldUseCase.execute(formalitieCustomFieldId, file, filename);
    }
}

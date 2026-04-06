package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile.UploadFileRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

public class GetFileUrlUseCase {

    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;
    private final UploadFileRepository uploadFileRepository;

    public GetFileUrlUseCase(FormalitieCustomFieldRepository formalitieCustomFieldRepository, UploadFileRepository uploadFileRepository) {
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
        this.uploadFileRepository = uploadFileRepository;
    }

    @Transactional
    public String execute(int id) {

        FormalitieCustomField formalitieCustomField = formalitieCustomFieldRepository.findById(id).orElseThrow(() -> new BadRequestException("FormalitieCustomField with id " + id + " not found"));
        return uploadFileRepository.getFileUrl(formalitieCustomField.getValue());
    }
}

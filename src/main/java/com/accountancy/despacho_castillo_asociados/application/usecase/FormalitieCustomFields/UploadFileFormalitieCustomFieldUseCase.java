package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.UploadFile.UploadFile;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile.UploadFileRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UploadFileFormalitieCustomFieldUseCase {

    private final UploadFileRepository uploadFileRepository;
    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;

    public UploadFileFormalitieCustomFieldUseCase(UploadFileRepository uploadFileRepository, FormalitieCustomFieldRepository formalitieCustomFieldRepository) {
        this.uploadFileRepository = uploadFileRepository;
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
    }

    public boolean execute(int formalitieCustomFieldId, MultipartFile file, String filename) throws IOException {
            UploadFile result = uploadFileRepository.uploadFile(formalitieCustomFieldId, file, filename);

            formalitieCustomFieldRepository.updateFilePath(
                formalitieCustomFieldId,
                result.getUrl()
            );

            return true;

    }

}

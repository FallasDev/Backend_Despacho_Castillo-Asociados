package com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile;

import com.accountancy.despacho_castillo_asociados.domain.model.UploadFile.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileRepository {

    UploadFile uploadFile(int formalitieCustomFieldId, MultipartFile file, String filename) throws IOException;
    String getFileUrl(String publicId);


}

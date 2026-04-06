package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.UploadFile;

import com.accountancy.despacho_castillo_asociados.domain.model.UploadFile.UploadFile;
import com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile.UploadFileRepository;

import java.io.IOException;
import java.util.Map;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class UploadCloudinaryImpl implements UploadFileRepository {

    private  Cloudinary cloudinary;


    public UploadCloudinaryImpl() {

        this.cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", "du2mwvmda",
                        "api_key", "131254517967836",
                        "api_secret", "nNVTf5jZzor7o6gKPIYl7iTzXyQ",
                        "secure", true
                )
        );

    }

    @Override
    public UploadFile uploadFile(int formalitieCustomFieldId, MultipartFile file, String filename) throws IOException {



        Map params = ObjectUtils.asMap(
                "folder", "formalities_custom_fields",
                "type", "upload",
                "resource_type", "auto",
                "access_mode", "public",
                "use_filename", true,
                "unique_filename", true
        );

        Map uploadResult = this.cloudinary.uploader().upload(
            file.getBytes(),
            params
        );


        return mapToDomain(uploadResult);
    }

    @Override
    public String getFileUrl(String publicId) {

        return cloudinary.url()
                .resourceType("raw")
                .type("upload")
                .publicId(publicId)
                .signed(true)
                .generate();
    }

    public UploadFile mapToDomain(Map result) {

        UploadFile file = new UploadFile();

        file.setId((String) result.get("public_id"));
        file.setUrl((String) result.get("url"));
        file.setSecureUrl((String) result.get("secure_url"));
        file.setFormat((String) result.get("format"));
        file.setSize(((Number) result.get("bytes")).longValue());

        return file;
    }
}

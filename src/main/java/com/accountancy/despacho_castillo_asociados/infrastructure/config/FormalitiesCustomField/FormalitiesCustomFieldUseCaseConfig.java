package com.accountancy.despacho_castillo_asociados.infrastructure.config.FormalitiesCustomField;

import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile.UploadFileRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormalitiesCustomFieldUseCaseConfig {

    @Bean
    public CreateFormalitieCustomFieldsUseCase createFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository,
        CustomFieldRepository customFieldRepository, Messages messages
    ) {
        return new CreateFormalitieCustomFieldsUseCase(repository,customFieldRepository, messages);
    }

    @Bean
    public DeactiveFormalitieCustomFieldsUseCase deactiveFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository, Messages messages) {
        return new DeactiveFormalitieCustomFieldsUseCase(repository, messages);
    }

    @Bean
    public UpdateFormalitieCustomFieldsUseCase updateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository, Messages messages) {
        return new UpdateFormalitieCustomFieldsUseCase(repository, messages);
    }

    @Bean
    public FindFormalitiesCustomFieldsUseCase findFormalitiesCustomFieldsUseCase(FormalitieCustomFieldRepository repository, Messages messages) {
        return new FindFormalitiesCustomFieldsUseCase(repository, messages);
    }

    @Bean
    public UploadFileFormalitieCustomFieldUseCase uploadFileFormalitieCustomFieldUseCase(UploadFileRepository storageRepository, FormalitieCustomFieldRepository formalitieCustomFieldRepository) {
        return new UploadFileFormalitieCustomFieldUseCase(storageRepository, formalitieCustomFieldRepository);
    }


    @Bean
    public GetFileUrlUseCase downloadFileFormalitieCustomFieldUseCase(UploadFileRepository storageRepository, FormalitieCustomFieldRepository formalitieCustomFieldRepository) {
        return new GetFileUrlUseCase(formalitieCustomFieldRepository,storageRepository);
    }


}

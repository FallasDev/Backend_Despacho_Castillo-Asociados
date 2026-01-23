package com.accountancy.despacho_castillo_asociados.infrastructure.config.FormalitiesCustomField;

import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.CreateFormalitieUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.CreateFormalitieCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.DeactiveFormalitieCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.FindFormalitiesCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields.UpdateFormalitieCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormalitiesCustomFieldUseCaseConfig {

    @Bean
    public CreateFormalitieCustomFieldsUseCase createFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository,
        FormalitieRepository formalitieRepository, CustomFieldRepository customFieldRepository
    ) {
        return new CreateFormalitieCustomFieldsUseCase(repository,customFieldRepository, formalitieRepository);
    }

    @Bean
    public DeactiveFormalitieCustomFieldsUseCase deactiveFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository) {
        return new DeactiveFormalitieCustomFieldsUseCase(repository);
    }

    @Bean
    public UpdateFormalitieCustomFieldsUseCase updateFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository) {
        return new UpdateFormalitieCustomFieldsUseCase(repository);
    }

    @Bean
    public FindFormalitiesCustomFieldsUseCase findFormalitiesCustomFieldsUseCase(FormalitieCustomFieldRepository repository) {
        return new FindFormalitiesCustomFieldsUseCase(repository);
    }





}

package com.accountancy.despacho_castillo_asociados.infrastructure.config.ServiceCustomField;

import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.CreateServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.DeactiveServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.FindByIdServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.FindServicesCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceCustomFieldUseCaseConfig {

    @Bean
    public CreateServiceCustomFieldsUseCase createServiceCustomFieldUseCase(ServiceCustomFieldsRepository repository,
                                                                            ServiceRepository serviceRepository,
                                                                            CustomFieldRepository customFieldRepository) {
        return new CreateServiceCustomFieldsUseCase(repository, serviceRepository, customFieldRepository);
    }

    @Bean
    public FindServicesCustomFieldsUseCase findServicesCustomFieldsUseCase(ServiceCustomFieldsRepository repository
) {
        return new FindServicesCustomFieldsUseCase(repository);
    }

    @Bean
    public DeactiveServiceCustomFieldsUseCase deactiveServiceCustomFieldUseCase(ServiceCustomFieldsRepository repository) {
        return new DeactiveServiceCustomFieldsUseCase(repository);
    }

    @Bean
    public FindByIdServiceCustomFieldsUseCase findByIdServiceCustomFieldUseCase(ServiceCustomFieldsRepository repository) {
        return new FindByIdServiceCustomFieldsUseCase();
    }

}

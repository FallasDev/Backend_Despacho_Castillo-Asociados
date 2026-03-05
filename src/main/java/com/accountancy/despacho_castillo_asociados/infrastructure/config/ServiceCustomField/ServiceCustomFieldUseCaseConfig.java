package com.accountancy.despacho_castillo_asociados.infrastructure.config.ServiceCustomField;

import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.CreateServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.DeactiveServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.FindByIdServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.FindServicesCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceCustomFieldUseCaseConfig {

    @Bean
    public CreateServiceCustomFieldsUseCase createServiceCustomFieldUseCase(ServiceCustomFieldsRepository repository,
                                                                            ServiceRepository serviceRepository,
                                                                            CustomFieldRepository customFieldRepository,
                                                                            Messages messages) {
        return new CreateServiceCustomFieldsUseCase(repository, serviceRepository, customFieldRepository, messages);
    }

    @Bean
    public FindServicesCustomFieldsUseCase findServicesCustomFieldsUseCase(ServiceCustomFieldsRepository repository
                                                                           , Messages messages
) {
        return new FindServicesCustomFieldsUseCase(repository, messages);
    }

    @Bean
    public DeactiveServiceCustomFieldsUseCase deactiveServiceCustomFieldUseCase(ServiceCustomFieldsRepository repository, Messages messages) {
        return new DeactiveServiceCustomFieldsUseCase(repository, messages);
    }

    @Bean
    public FindByIdServiceCustomFieldsUseCase findByIdServiceCustomFieldUseCase(ServiceCustomFieldsRepository repository) {
        return new FindByIdServiceCustomFieldsUseCase();
    }

}

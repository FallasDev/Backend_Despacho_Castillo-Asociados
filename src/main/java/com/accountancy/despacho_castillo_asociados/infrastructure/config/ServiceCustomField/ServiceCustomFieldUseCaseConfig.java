package com.accountancy.despacho_castillo_asociados.infrastructure.config.ServiceCustomField;

import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.*;
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
        return new FindByIdServiceCustomFieldsUseCase(repository);
    }

    @Bean
    public UpdateServiceCustomFieldsUseCase updateServiceCustomFieldUseCase(ServiceCustomFieldsRepository serviceCustomFieldsRepository, CustomFieldRepository customFieldRepository, ServiceRepository serviceRepository, Messages messages) {
        return new UpdateServiceCustomFieldsUseCase(serviceCustomFieldsRepository, customFieldRepository, serviceRepository, messages);
    }

    @Bean
    public FindAllServiceCustomFields findAllServiceCustomFields(ServiceCustomFieldsRepository repository) {
        return new FindAllServiceCustomFields(repository);
    }

    @Bean
    public FindAllServiceCustomFieldsDto findAllServiceCustomFieldsDto(com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField.JPAServiceCustomFieldRepository jpaRepository) {
        return new FindAllServiceCustomFieldsDto(jpaRepository);
    }

    @Bean
    public FindAllServiceCustomFieldsDtoPaginated findAllServiceCustomFieldsDtoPaginated(com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField.JPAServiceCustomFieldRepository jpaRepository) {
        return new FindAllServiceCustomFieldsDtoPaginated(jpaRepository);
    }
}

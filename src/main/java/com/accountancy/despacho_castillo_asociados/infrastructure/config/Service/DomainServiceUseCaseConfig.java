package com.accountancy.despacho_castillo_asociados.infrastructure.config.Service;
import com.accountancy.despacho_castillo_asociados.application.usecase.Service.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceUseCaseConfig {

    @Bean
    public CreateServiceUseCase createServiceUseCase(ServiceRepository serviceRepository, Messages messages) {
        return new CreateServiceUseCase(serviceRepository, messages);
    }

    @Bean
    public FindServicesUseCase findServicesUseCase(ServiceRepository serviceRepository, Messages messages) {
        return new FindServicesUseCase(serviceRepository, messages);
    }

    @Bean
    public DeactiveServiceUseCase deactiveServiceUseCase(ServiceRepository serviceRepository, Messages messages,
                                                         ServiceCustomFieldsRepository serviceCustomFieldsRepository) {
        return new DeactiveServiceUseCase(serviceRepository, messages, serviceCustomFieldsRepository);
    }

    @Bean
    public UpdateServiceUseCase updateServiceUseCase(ServiceRepository serviceRepository, Messages messages) {
        return new UpdateServiceUseCase(serviceRepository, messages);
    }

    @Bean
    public FindByIdServiceUseCase findByIdServiceUseCase(ServiceRepository serviceRepository, Messages messages) {
        return new FindByIdServiceUseCase(serviceRepository, messages);
    }

    @Bean
    public FindByContainsNameLetterUseCase findByContainsNameLetterServiceUseCase(ServiceRepository serviceRepository, Messages messages) {
        return new FindByContainsNameLetterUseCase(serviceRepository, messages);
    }

    @Bean
    public FindAllServicesUseCase findAllServicesUseCase(ServiceRepository serviceRepository, Messages messages) {
        return new FindAllServicesUseCase(serviceRepository, messages);
    }

    @Bean FindMostPopularServicesUseCase findMostPopularServicesUseCase(FormalitieRepository serviceRepository) {
        return new FindMostPopularServicesUseCase(serviceRepository);
    }

}

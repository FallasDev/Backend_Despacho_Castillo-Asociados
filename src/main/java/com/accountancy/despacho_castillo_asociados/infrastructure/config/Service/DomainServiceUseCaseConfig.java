package com.accountancy.despacho_castillo_asociados.infrastructure.config.Service;
import com.accountancy.despacho_castillo_asociados.application.usecase.Service.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainServiceUseCaseConfig {

    @Bean
    public CreateServiceUseCase createServiceUseCase(ServiceRepository serviceRepository) {
        return new CreateServiceUseCase(serviceRepository);
    }

    @Bean
    public FindServicesUseCase findAllServicesUseCase(ServiceRepository serviceRepository) {
        return new FindServicesUseCase(serviceRepository);
    }

    @Bean
    public DeactiveServiceUseCase deactiveServiceUseCase(ServiceRepository serviceRepository) {
        return new DeactiveServiceUseCase(serviceRepository);
    }

    @Bean
    public UpdateServiceUseCase updateServiceUseCase(ServiceRepository serviceRepository) {
        return new UpdateServiceUseCase(serviceRepository);
    }

    @Bean
    public FindByIdServiceUseCase findByIdServiceUseCase(ServiceRepository serviceRepository) {
        return new FindByIdServiceUseCase(serviceRepository);
    }

    @Bean
    public FindByContainsNameLetterUseCase findByContainsNameLetterServiceUseCase(ServiceRepository serviceRepository) {
        return new FindByContainsNameLetterUseCase(serviceRepository);
    }

}

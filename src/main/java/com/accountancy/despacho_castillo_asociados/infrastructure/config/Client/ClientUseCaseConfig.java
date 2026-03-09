package com.accountancy.despacho_castillo_asociados.infrastructure.config.Client;

import com.accountancy.despacho_castillo_asociados.application.usecase.Client.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientUseCaseConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepository clientRepository) {
        return new CreateClientUseCase(clientRepository);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepository clientRepository) {
        return new UpdateClientUseCase(clientRepository);
    }

    @Bean
    public DeactivateClientUseCase deactivateClientUseCase(ClientRepository clientRepository) {
        return new DeactivateClientUseCase(clientRepository);
    }

    @Bean
    public FindAllClientUseCase findAllClientUseCase(ClientRepository clientRepository) {
        return new FindAllClientUseCase(clientRepository);
    }

    @Bean
    public FindByIdClientUseCase findByIdClientUseCase(ClientRepository clientRepository) {
        return new FindByIdClientUseCase(clientRepository);
    }

    @Bean
    public FindByNameClientUseCase findByNameClientUseCase(ClientRepository clientRepository) {
        return new FindByNameClientUseCase(clientRepository);
    }

    @Bean
    public FindBySurnameClientUseCase findBySurnameClientUseCase(ClientRepository clientRepository) {
        return new FindBySurnameClientUseCase(clientRepository);
    }

}


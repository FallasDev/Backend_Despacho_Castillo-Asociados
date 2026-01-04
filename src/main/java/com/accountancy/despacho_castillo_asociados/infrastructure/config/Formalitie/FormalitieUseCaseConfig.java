package com.accountancy.despacho_castillo_asociados.infrastructure.config.Formalitie;


import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.*;
import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.FindFormalitiesUseCase;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormalitieUseCaseConfig {


    @Bean
    public CreateFormalitieUseCase createFormalitieUseCase(FormalitieRepository formalitieRepository, ServiceRepository serviceRepository) {
        return new CreateFormalitieUseCase(formalitieRepository, serviceRepository);

    }

    @Bean
    public ChangeFormalitieStateUseCase changeFormalitieStateUseCase(FormalitieRepository formalitieRepository) {
        return new ChangeFormalitieStateUseCase(formalitieRepository);
    }

    @Bean
    public FindFormalitiesUseCase findFormalitiesUseCase(FormalitieRepository formalitieRepository) {
        return new FindFormalitiesUseCase(formalitieRepository);
    }

    @Bean
    public FindByIdFormalitieUseCase findByIdFormalitieUseCase(FormalitieRepository formalitieRepository) {
        return new FindByIdFormalitieUseCase(formalitieRepository);
    }

}

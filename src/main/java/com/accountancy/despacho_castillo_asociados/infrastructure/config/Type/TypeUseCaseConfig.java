package com.accountancy.despacho_castillo_asociados.infrastructure.config.Type;


import com.accountancy.despacho_castillo_asociados.application.usecase.Type.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeUseCaseConfig {

    @Bean
    public CreateTypeUseCase createTypeUseCase(TypeRepository typeRepository) {
        return new CreateTypeUseCase(typeRepository);
    }

    @Bean
    public FindAllTypeUseCase findAllTypeUseCase(TypeRepository typeRepository) {
        return new FindAllTypeUseCase(typeRepository);
    }

    @Bean
    public DeactiveTypeUseCase deactiveTypeUseCase(TypeRepository typeRepository) {
        return new DeactiveTypeUseCase(typeRepository);
    }

    @Bean
    public UpdateTypeUseCase updateTypeUseCase(TypeRepository typeRepository) {
        return new UpdateTypeUseCase(typeRepository);
    }

    @Bean
    public FindByIdTypeUseCase findByIdTypeUseCase(TypeRepository typeRepository) {
        return new FindByIdTypeUseCase(typeRepository);
    }

    @Bean
    public FindByNameTypeUseCase findByNameTypeUseCase(TypeRepository typeRepository)
    {
        return new FindByNameTypeUseCase(typeRepository);
    }

}

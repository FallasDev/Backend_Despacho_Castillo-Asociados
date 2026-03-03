package com.accountancy.despacho_castillo_asociados.infrastructure.config.Type;


import com.accountancy.despacho_castillo_asociados.application.usecase.Type.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeUseCaseConfig {

    @Bean
    public CreateTypeUseCase createTypeUseCase(TypeRepository typeRepository, Messages messages) {
        return new CreateTypeUseCase(typeRepository, messages);
    }

    @Bean
    public FindAllTypeUseCase findAllTypeUseCase(TypeRepository typeRepository, Messages messages) {
        return new FindAllTypeUseCase(typeRepository, messages);
    }

    @Bean
    public DeactiveTypeUseCase deactiveTypeUseCase(TypeRepository typeRepository, Messages messages) {
        return new DeactiveTypeUseCase(typeRepository, messages);
    }

    @Bean
    public UpdateTypeUseCase updateTypeUseCase(TypeRepository typeRepository, Messages messages) {
        return new UpdateTypeUseCase(typeRepository, messages);
    }

    @Bean
    public FindByIdTypeUseCase findByIdTypeUseCase(TypeRepository typeRepository, Messages messages) {
        return new FindByIdTypeUseCase(typeRepository, messages);
    }

    @Bean
    public FindByNameTypeUseCase findByNameTypeUseCase(TypeRepository typeRepository, Messages messages)
    {
        return new FindByNameTypeUseCase(typeRepository, messages   );
    }

}

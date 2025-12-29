package com.accountancy.despacho_castillo_asociados.infrastructure.config.CustomField;


import com.accountancy.despacho_castillo_asociados.application.usecase.CustomField.*;
import com.accountancy.despacho_castillo_asociados.application.usecase.Type.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFieldUseCaseConfig {

    @Bean
    public CreateCustomFieldUseCase createCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository) {
        return new CreateCustomFieldUseCase(customFieldRepository, typeRepository);
    }

    @Bean
    public FindAllCustomFieldUseCase findAllCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository) {
        return new FindAllCustomFieldUseCase(customFieldRepository);
    }

    @Bean
    public DeactiveCustomFieldUseCase deactiveCustomFieldUseCase(CustomFieldRepository customFieldRepository) {
        return new DeactiveCustomFieldUseCase(customFieldRepository);
    }

    @Bean
    public UpdateCustomFieldUseCase updateCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository) {
        return new UpdateCustomFieldUseCase(customFieldRepository, typeRepository);
    }

    @Bean
    public FindByIdCustomFieldUseCase findByIdCustomFieldUseCase(CustomFieldRepository customFieldRepository) {
        return new FindByIdCustomFieldUseCase(customFieldRepository);
    }

//    @Bean
//    public FindByName findByNameTypeUseCase(CustomFieldRepository customFieldRepository)
//    {
//        return new FindByNameTypeUseCase(customFieldRepository);
//    }

}

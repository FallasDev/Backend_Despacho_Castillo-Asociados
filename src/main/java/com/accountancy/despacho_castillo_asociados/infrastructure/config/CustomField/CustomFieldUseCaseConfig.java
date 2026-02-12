package com.accountancy.despacho_castillo_asociados.infrastructure.config.CustomField;


import com.accountancy.despacho_castillo_asociados.application.usecase.CustomField.*;
import com.accountancy.despacho_castillo_asociados.application.usecase.Type.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFieldUseCaseConfig {

    @Bean
    public CreateCustomFieldUseCase createCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository, Messages messages) {
        return new CreateCustomFieldUseCase(customFieldRepository, typeRepository, messages);
    }

    @Bean
    public FindAllCustomFieldUseCase findAllCustomFieldUseCase(CustomFieldRepository customFieldRepository, Messages messages) {
        return new FindAllCustomFieldUseCase(customFieldRepository, messages);
    }

    @Bean
    public DeactiveCustomFieldUseCase deactiveCustomFieldUseCase(CustomFieldRepository customFieldRepository, Messages messages) {
        return new DeactiveCustomFieldUseCase(customFieldRepository, messages);
    }

    @Bean
    public UpdateCustomFieldUseCase updateCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository, Messages messages) {
        return new UpdateCustomFieldUseCase(customFieldRepository, typeRepository, messages);
    }

    @Bean
    public FindByIdCustomFieldUseCase findByIdCustomFieldUseCase(CustomFieldRepository customFieldRepository, Messages messages) {
        return new FindByIdCustomFieldUseCase(customFieldRepository, messages);
    }

//    @Bean
//    public FindByName findByNameTypeUseCase(CustomFieldRepository customFieldRepository)
//    {
//        return new FindByNameTypeUseCase(customFieldRepository);
//    }

}

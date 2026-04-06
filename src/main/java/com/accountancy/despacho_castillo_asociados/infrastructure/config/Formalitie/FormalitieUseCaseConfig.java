package com.accountancy.despacho_castillo_asociados.infrastructure.config.Formalitie;


import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.*;
import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.FindFormalitiesUseCase;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormalitieUseCaseConfig {


    @Bean
    public CreateFormalitieUseCase createFormalitieUseCase(FormalitieRepository formalitieRepository,
                                                           ServiceRepository serviceRepository,
                                                           ClientRepository clientRepository,
                                                           CustomFieldRepository customFieldRepository,
                                                           FormalitieCustomFieldRepository formalitieCustomFieldRepository,
                                                           UserRepository userRepository,
                                                           Messages messages) {
        return new CreateFormalitieUseCase(formalitieRepository,serviceRepository, customFieldRepository ,clientRepository, userRepository, formalitieCustomFieldRepository ,messages);

    }

    @Bean
    public ChangeFormalitieStateUseCase changeFormalitieStateUseCase(FormalitieRepository formalitieRepository, Messages messages, EmailService emailService) {
        return new ChangeFormalitieStateUseCase(formalitieRepository, messages, emailService);
    }

    @Bean
    public FindFormalitiesUseCase findFormalitiesUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        return new FindFormalitiesUseCase(formalitieRepository, messages);
    }

    @Bean
    public FindByIdFormalitieUseCase findByIdFormalitieUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        return new FindByIdFormalitieUseCase(formalitieRepository, messages);
    }

    @Bean
    public UpdateFormalitieUseCase updateFormalitieUseCase(FormalitieRepository formalitieRepository,
                                                           ClientRepository clientRepository,
                                                           CustomFieldRepository customFieldRepository,
                                                            FormalitieCustomFieldRepository formalitieCustomFieldRepository,
                                                              UserRepository userRepository,
            ServiceRepository serviceRepository
            ,Messages messages) {
        return new UpdateFormalitieUseCase(formalitieRepository, clientRepository, userRepository , serviceRepository,customFieldRepository, formalitieCustomFieldRepository ,messages);
    }

    @Bean
    public HandleFormalitieUseCase handleFormalitieUseCase(FormalitieRepository formalitieRepository
            , UserRepository userRepository, Messages messages) {
        return new HandleFormalitieUseCase(formalitieRepository, userRepository, messages);
    }

    @Bean
    public GetFormalitiesCountByClientUseCase getFormalitiesCountByClientUseCase(FormalitieRepository formalitieRepository, ClientRepository clientRepository) {
        return new GetFormalitiesCountByClientUseCase(formalitieRepository, clientRepository);
    }
}

package com.accountancy.despacho_castillo_asociados.infrastructure.config.User;

import com.accountancy.despacho_castillo_asociados.application.usecase.User.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        return new CreateUserUseCase(userRepository, roleRepository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        return new UpdateUserUseCase(userRepository, roleRepository);
    }

    @Bean
    public DeactivateUserUseCase deactivateUserUseCase(UserRepository userRepository) {
        return new DeactivateUserUseCase(userRepository);
    }

    @Bean
    public FindAllUserUseCase findAllUserUseCase(UserRepository userRepository) {
        return new FindAllUserUseCase(userRepository);
    }

    @Bean
    public FindByIdUserUseCase findByIdUserUseCase(UserRepository userRepository) {
        return new FindByIdUserUseCase(userRepository);
    }

    @Bean
    public FindByNameUserUseCase findByNameUserUseCase(UserRepository userRepository) {
        return new FindByNameUserUseCase(userRepository);
    }

    @Bean
    public FindBySurnameUserUseCase findBySurnameUserUseCase(UserRepository userRepository) {
        return new FindBySurnameUserUseCase(userRepository);
    }

}


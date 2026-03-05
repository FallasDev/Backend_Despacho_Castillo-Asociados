package com.accountancy.despacho_castillo_asociados.infrastructure.config.Role;

import com.accountancy.despacho_castillo_asociados.application.usecase.Role.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleUseCaseConfig {

    @Bean
    public CreateRoleUseCase createRoleUseCase(RoleRepository roleRepository) {
        return new CreateRoleUseCase(roleRepository);
    }

    @Bean
    public UpdateRoleUseCase updateRoleUseCase(RoleRepository roleRepository) {
        return new UpdateRoleUseCase(roleRepository);
    }

    @Bean
    public DeactivateRoleUseCase deactivateRoleUseCase(RoleRepository roleRepository) {
        return new DeactivateRoleUseCase(roleRepository);
    }

    @Bean
    public FindAllRoleUseCase findAllRoleUseCase(RoleRepository roleRepository) {
        return new FindAllRoleUseCase(roleRepository);
    }

    @Bean
    public FindByIdRoleUseCase findByIdRoleUseCase(RoleRepository roleRepository) {
        return new FindByIdRoleUseCase(roleRepository);
    }

    @Bean
    public FindByNameRoleUseCase findByNameRoleUseCase(RoleRepository roleRepository) {
        return new FindByNameRoleUseCase(roleRepository);
    }

}


package com.accountancy.despacho_castillo_asociados.infrastructure.config.PermissionRole;

import com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionRoleUseCaseConfig {

    @Bean
    public CreatePermissionRoleUseCase createPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        return new CreatePermissionRoleUseCase(permissionRoleRepository);
    }

    @Bean
    public DeletePermissionRoleUseCase deletePermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        return new DeletePermissionRoleUseCase(permissionRoleRepository);
    }

    @Bean
    public FindAllPermissionRoleUseCase findAllPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        return new FindAllPermissionRoleUseCase(permissionRoleRepository);
    }

    @Bean
    public FindByIdPermissionRoleUseCase findByIdPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        return new FindByIdPermissionRoleUseCase(permissionRoleRepository);
    }

    @Bean
    public FindByIdRolePermissionRoleUseCase findByIdRolePermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        return new FindByIdRolePermissionRoleUseCase(permissionRoleRepository);
    }

    @Bean
    public FindByPermissionIdPermissionRoleUseCase findByPermissionIdPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        return new FindByPermissionIdPermissionRoleUseCase(permissionRoleRepository);
    }

}


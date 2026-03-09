package com.accountancy.despacho_castillo_asociados.infrastructure.config.Permission;

import com.accountancy.despacho_castillo_asociados.application.usecase.Permission.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionUseCaseConfig {

    @Bean
    public FindAllPermissionUseCase findAllPermissionUseCase(PermissionRepository permissionRepository) {
        return new FindAllPermissionUseCase(permissionRepository);
    }

    @Bean
    public FindByIdPermissionUseCase findByIdPermissionUseCase(PermissionRepository permissionRepository) {
        return new FindByIdPermissionUseCase(permissionRepository);
    }

    @Bean
    public FindByNamePermissionUseCase findByNamePermissionUseCase(PermissionRepository permissionRepository) {
        return new FindByNamePermissionUseCase(permissionRepository);
    }

    @Bean
    public FindByDescriptionPermissionUseCase findByDescriptionPermissionUseCase(PermissionRepository permissionRepository) {
        return new FindByDescriptionPermissionUseCase(permissionRepository);
    }

    @Bean
    public UpdatePermissionUseCase updatePermissionUseCase(PermissionRepository permissionRepository) {
        return new UpdatePermissionUseCase(permissionRepository);
    }

}


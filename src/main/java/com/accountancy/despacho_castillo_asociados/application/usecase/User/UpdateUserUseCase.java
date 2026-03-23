package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import com.accountancy.despacho_castillo_asociados.shared.utils.UserValidationsHelper;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class UpdateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UpdateUserUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User execute(UserRequest userRequest, int id) {

        if (userRequest == null) {
            throw new BadRequestException("User cannot be null");
        }

        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new BadRequestException("User name cannot be null or empty");
        }

        UserValidationsHelper.validateEmail(userRequest.getEmail());
        UserValidationsHelper.validatePassword(userRequest.getPassword());

        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " does not exist");
        }

        if (!existingUser.get().isActive()) {
            throw new BadRequestException("User with id " + id + " is not active");
        }

        Optional<Role> role = roleRepository.findById(userRequest.getRoleId());

        if (role.isEmpty()) {
            throw new BadRequestException("Role with id " + userRequest.getRoleId() + " does not exist");
        }

        User updatedUser = userRepository.update(userRequest, id, role.get());

        if (updatedUser == null) {
            throw new BadRequestException("Failed to update User with id " + id);
        }

        return updatedUser;
    }

}


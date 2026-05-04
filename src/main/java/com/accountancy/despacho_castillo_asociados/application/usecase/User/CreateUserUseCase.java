package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import com.accountancy.despacho_castillo_asociados.shared.utils.UserValidationsHelper;
import jakarta.transaction.Transactional;

import java.util.Optional;


public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CreateUserUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User execute(UserRequest userRequest) {

        if (userRequest == null) {
            throw new BadRequestException("User cannot be null");
        }

        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new BadRequestException("User name cannot be null or empty");
        }

        UserValidationsHelper.validateEmail(userRequest.getEmail());
        UserValidationsHelper.validatePassword(userRequest.getPassword());

        boolean existingUser = userRepository.fintByEmailAndIsActive(userRequest.getName()).isPresent();

        if (existingUser) {
            throw new BadRequestException("User with email " + userRequest.getEmail() + " already exists");
        }

        Optional<User> inactiveUser = userRepository.fintByNameAndIsInactive(userRequest.getName());

        if (inactiveUser.isPresent()) {
            User reactivatedUser = inactiveUser.get();
            userRepository.activate(reactivatedUser.getId());
            return reactivatedUser;
        }

        Optional<Role> role = roleRepository.findById(userRequest.getRoleId());

        if (role.isEmpty()) {
            throw new BadRequestException("Role with id " + userRequest.getRoleId() + " does not exist");
        }


        User user = userRepository.create(userRequest, role.get());

        if (user == null) {
            throw new BadRequestException("Failed to create User with name " + userRequest.getName());
        }

        return user;
    }

}


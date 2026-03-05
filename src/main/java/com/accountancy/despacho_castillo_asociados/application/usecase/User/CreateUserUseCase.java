package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;


public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UserRequest userRequest) {

        if (userRequest == null) {
            throw new BadRequestException("User cannot be null");
        }

        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new BadRequestException("User name cannot be null or empty");
        }

        boolean existingUser = userRepository.fintByNameAndIsActive(userRequest.getName()).isPresent();

        if (existingUser) {
            throw new BadRequestException("User with name " + userRequest.getName() + " already exists");
        }

        Optional<User> inactiveUser = userRepository.fintByNameAndIsInactive(userRequest.getName());

        if (inactiveUser.isPresent()) {
            User reactivatedUser = inactiveUser.get();
            userRepository.activate(reactivatedUser.getId());
            return reactivatedUser;
        }

        return userRepository.create(userRequest);
    }

}


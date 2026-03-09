package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class UpdateUserUseCase {

    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UserRequest userRequest, int id) {

        if (userRequest == null) {
            throw new BadRequestException("User cannot be null");
        }

        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new BadRequestException("User name cannot be null or empty");
        }

        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " does not exist");
        }

        if (!existingUser.get().isActive()) {
            throw new BadRequestException("User with id " + id + " is not active");
        }

        User updatedUser = userRepository.update(userRequest, id);

        if (updatedUser == null) {
            throw new BadRequestException("Failed to update User with id " + id);
        }

        return updatedUser;
    }

}


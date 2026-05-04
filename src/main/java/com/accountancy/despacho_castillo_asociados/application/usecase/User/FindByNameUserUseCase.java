package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByNameUserUseCase {

    private final UserRepository userRepository;

    public FindByNameUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }

        Optional<User> user = userRepository.fintByEmailAndIsActive(name);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with name " + name + " not found");
        }

        return user.get();
    }

}


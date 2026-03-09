package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindBySurnameUserUseCase {

    private final UserRepository userRepository;

    public FindBySurnameUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String surname) {

        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("User surname cannot be null or empty");
        }

        Optional<User> user = userRepository.fintBySurnameAndIsActive(surname);

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with surname " + surname + " not found");
        }

        return user.get();
    }

}


package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class FindByIdUserUseCase {

    private final UserRepository userRepository;

    public FindByIdUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User execute(int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty() || !user.get().isActive()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }

        return user.get();
    }

}


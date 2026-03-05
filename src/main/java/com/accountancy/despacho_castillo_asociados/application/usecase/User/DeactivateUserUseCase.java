package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactivateUserUseCase {

    private final UserRepository userRepository;

    public DeactivateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(int id) {
        boolean result = userRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate User with id " + id);
        }
    }

}


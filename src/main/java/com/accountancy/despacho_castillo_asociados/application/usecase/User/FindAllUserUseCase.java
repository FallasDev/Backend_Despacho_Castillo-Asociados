package com.accountancy.despacho_castillo_asociados.application.usecase.User;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.transaction.Transactional;

public class FindAllUserUseCase {

    private final UserRepository userRepository;


    public FindAllUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public PageResult<User> execute(int page, int size) {

        PageResult<User> users = userRepository.findAll(page, size);

        if (users.content().isEmpty()) {
            throw new EmptyListException("No users found");
        }

        return users;
    }

}


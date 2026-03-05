package com.accountancy.despacho_castillo_asociados.application.service.User;

import com.accountancy.despacho_castillo_asociados.application.usecase.User.*;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeactivateUserUseCase deactivateUserUseCase;
    private final FindAllUserUseCase findAllUserUseCase;
    private final FindByIdUserUseCase findByIdUserUseCase;
    private final FindByNameUserUseCase findByNameUserUseCase;
    private final FindBySurnameUserUseCase findBySurnameUserUseCase;

    public UserService(
            CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeactivateUserUseCase deactivateUserUseCase,
            FindAllUserUseCase findAllUserUseCase,
            FindByIdUserUseCase findByIdUserUseCase,
            FindByNameUserUseCase findByNameUserUseCase,
            FindBySurnameUserUseCase findBySurnameUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deactivateUserUseCase = deactivateUserUseCase;
        this.findAllUserUseCase = findAllUserUseCase;
        this.findByIdUserUseCase = findByIdUserUseCase;
        this.findByNameUserUseCase = findByNameUserUseCase;
        this.findBySurnameUserUseCase = findBySurnameUserUseCase;
    }

    public User createUser(UserRequest userRequest) {
        return createUserUseCase.execute(userRequest);
    }

    public User updateUser(UserRequest userRequest, int id) {
        return updateUserUseCase.execute(userRequest, id);
    }

    public void deactivateUser(int id) {
        deactivateUserUseCase.execute(id);
    }

    public PageResult<User> findAllUsers(int page, int size) {
        return findAllUserUseCase.execute(page, size);
    }

    public User findByIdUser(int id) {
        return findByIdUserUseCase.execute(id);
    }

    public User findByNameUser(String name) {
        return findByNameUserUseCase.execute(name);
    }

    public User findBySurnameUser(String surname) {
        return findBySurnameUserUseCase.execute(surname);
    }

}


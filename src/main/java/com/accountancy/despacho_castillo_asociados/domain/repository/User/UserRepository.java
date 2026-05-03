package com.accountancy.despacho_castillo_asociados.domain.repository.User;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface UserRepository {


    User create(UserRequest user, Role role);
    User update(UserRequest user, int id, Role role);
    void activate(int id);
    boolean deactivate(int id);

    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
    Optional<User> fintByName(String name);
    Optional<User> fintByNameAndIsActive(String name);
    Optional<User> fintByNameAndIsInactive(String name);
    Optional<User> fintBySurname(String surname);
    Optional<User> fintBySurnameAndIsActive(String surname);
    Optional<User> fintBySurnameAndIsInactive(String surname);
    PageResult<User> findAll(int page, int size);


    Optional<User> findByEmailWithRole(String email);

}

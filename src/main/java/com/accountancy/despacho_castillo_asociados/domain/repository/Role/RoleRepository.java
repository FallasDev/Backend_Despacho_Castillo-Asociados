package com.accountancy.despacho_castillo_asociados.domain.repository.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.RoleRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface RoleRepository {

    Role create(RoleRequest role);
    Role Update(RoleRequest role, int id);
    boolean deactivate(int id);
    void activate(int id);

    Optional<Role> findById(int id);
    Optional<Role> findByName(String name);
    Optional<Role> findByNameAndIsActive(String name);
    Optional<Role> findByNameAndIsInactive(String name);
    PageResult<Role> findAll(int page, int size);


}

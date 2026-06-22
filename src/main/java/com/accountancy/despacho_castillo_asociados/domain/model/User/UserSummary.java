package com.accountancy.despacho_castillo_asociados.domain.model.User;

public class UserSummary {
    private int id;
    private String name;
    private String email;
    private String roleName;
    private int roleId;

    public UserSummary() {}

    public UserSummary(int id, String name, String email, String roleName, int roleId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}


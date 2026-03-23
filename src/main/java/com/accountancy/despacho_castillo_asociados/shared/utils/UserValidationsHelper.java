package com.accountancy.despacho_castillo_asociados.shared.utils;

import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class UserValidationsHelper {

    public static void validateEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new BadRequestException("Invalid email format");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new BadRequestException("Password must be at least 8 characters long");
        }
    }

}

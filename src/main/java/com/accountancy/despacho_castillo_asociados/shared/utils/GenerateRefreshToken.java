package com.accountancy.despacho_castillo_asociados.shared.utils;

import java.util.UUID;

public class GenerateRefreshToken {

    public static String execute() {
        return UUID.randomUUID().toString() + UUID.randomUUID();
    }

}

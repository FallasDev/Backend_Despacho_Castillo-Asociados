package com.accountancy.despacho_castillo_asociados.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

public class GenerateOtp {

    public static String execute() {
        // 6 dígitos numéricos (simple y común)
        Random random = new SecureRandom();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }

}

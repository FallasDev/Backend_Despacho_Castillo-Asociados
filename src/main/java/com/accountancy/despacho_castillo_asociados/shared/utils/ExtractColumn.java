package com.accountancy.despacho_castillo_asociados.shared.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ExtractColumn {

    public static String extractColumnName(String message) {

        if (message == null) return "desconocida";

        Pattern pattern = Pattern.compile("column '([^']+)'");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "desconocida";
    }

}

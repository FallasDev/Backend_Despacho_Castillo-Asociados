package com.accountancy.despacho_castillo_asociados.shared.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ExtractColumn {

    public static String extractDataTooLongColumn(String message) {
        if (message == null) return null;

        Pattern pattern = Pattern.compile("column '(.+?)'");
        Matcher matcher = pattern.matcher(message);

        return matcher.find() ? matcher.group(1) : null;
    }

    public static  String extractDuplicateColumn(String message) {
        if (message == null) return null;

        Pattern pattern = Pattern.compile("for key '(.+?)'");
        Matcher matcher = pattern.matcher(message);

        return matcher.find() ? matcher.group(1) : null;
    }

    public static  String extractDuplicateValue(String message) {
        if (message == null) return null;

        Pattern pattern = Pattern.compile("Duplicate entry '(.+?)'");
        Matcher matcher = pattern.matcher(message);

        return matcher.find() ? matcher.group(1) : null;
    }

    public static  String extractNotNullColumn(String message) {
        if (message == null) return null;

        Pattern pattern = Pattern.compile("Column '(.+?)'");
        Matcher matcher = pattern.matcher(message);

        return matcher.find() ? matcher.group(1) : null;
    }

}

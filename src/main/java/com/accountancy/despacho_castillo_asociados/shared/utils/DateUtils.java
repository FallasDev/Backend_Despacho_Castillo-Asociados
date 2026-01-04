package com.accountancy.despacho_castillo_asociados.shared.utils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public final class DateUtils {

    private DateUtils() {}

    // Valida una fecha (solo fecha) usando el patrón, p.ej. "yyyy-MM-dd"
    public static boolean isValidLocalDate(String date, String pattern) {
        if (date == null || pattern == null) return false;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(date, fmt);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    // Valida una fecha y hora usando el patrón, p.ej. "yyyy-MM-dd HH:mm:ss"
    public static boolean isValidLocalDateTime(String dateTime, String pattern) {
        if (dateTime == null || pattern == null) return false;
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime.parse(dateTime, fmt);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    // Parsea a LocalDate (lanza DateTimeParseException si no es válido)
    public static LocalDate toLocalDate(String date, String pattern) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern)
                .withResolverStyle(ResolverStyle.STRICT);
        return LocalDate.parse(date, fmt);
    }

    // Parsea a LocalDateTime (lanza DateTimeParseException si no es válido)
    public static LocalDateTime toLocalDateTime(String dateTime, String pattern) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern)
                .withResolverStyle(ResolverStyle.STRICT);
        return LocalDateTime.parse(dateTime, fmt);
    }
}
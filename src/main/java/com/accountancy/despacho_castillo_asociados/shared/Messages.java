package com.accountancy.despacho_castillo_asociados.shared;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class Messages {

    private final MessageSource messageSource;

    private static final Map<String, String> TABLE_KEYS = Map.ofEntries(
            Map.entry("clients", "table.clients"),
            Map.entry("custom_fields", "table.custom_fields"),
            Map.entry("formalities", "table.formalities"),
            Map.entry("formalities_custom_fields", "table.formalities_custom_fields"),
            Map.entry("permissions", "table.permissions"),
            Map.entry("permissions_roles", "table.permissions_roles"),
            Map.entry("report_categories", "table.report_categories"),
            Map.entry("report_field_values", "table.report_field_values"),
            Map.entry("report_fields", "table.report_fields"),
            Map.entry("reports", "table.reports"),
            Map.entry("roles", "table.roles"),
            Map.entry("service_custom_fields", "table.service_custom_fields"),
            Map.entry("services", "table.services"),
            Map.entry("types", "table.types"),
            Map.entry("users", "table.users")
    );

    public Messages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key) {
        return messageSource.getMessage(
                key,
                null,
                LocaleContextHolder.getLocale()
        );
    }


    public String get(String key, Object[] args) {
        return messageSource.getMessage(
                key,
                args,
                LocaleContextHolder.getLocale()
        );
    }

    public String getFriendlyName(String rawTableName, Locale locale) {

        if (rawTableName == null) {
            return null;
        }

        // 1️⃣ quitar constraint: clients.UKxxxx
        String table = rawTableName.split("\\.")[0];

        // 2️⃣ ignorar secuencias
        if (table.endsWith("_seq")) {
            return table;
        }

        // 3️⃣ buscar key de traducción
        String key = TABLE_KEYS.get(table);

        if (key == null) {
            return table;
        }

        // 4️⃣ devolver traducción según locale
        return messageSource.getMessage(key, null, locale);
    }

}

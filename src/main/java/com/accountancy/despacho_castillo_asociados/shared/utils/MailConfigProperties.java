package com.accountancy.despacho_castillo_asociados.shared.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.mail")
@Data   // o @Value si usas Lombok
public class MailConfigProperties {

    private String sender = "despachocastilloasociados2026@gmail.com";
    // Puedes agregar más: replyTo, defaultSubjectPrefix, etc.
}
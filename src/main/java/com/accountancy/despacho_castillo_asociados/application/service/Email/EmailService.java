package com.accountancy.despacho_castillo_asociados.application.service.Email;

import com.accountancy.despacho_castillo_asociados.shared.utils.MailConfigProperties;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final MailConfigProperties mailConfig;

    public EmailService(JavaMailSender mailSender, MailConfigProperties mailConfig) {
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailConfig.getSender());  // debe coincidir con el username
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    // Versión con HTML (más profesional)
    public void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage  message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(mailConfig.getSender());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);  // true = es HTML

        mailSender.send(message);
    }
}
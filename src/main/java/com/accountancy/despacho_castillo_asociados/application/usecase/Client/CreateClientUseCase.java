package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode.VerificationCodeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.RedirectionException;
import com.accountancy.despacho_castillo_asociados.shared.utils.GenerateOtp;
import com.accountancy.despacho_castillo_asociados.shared.utils.HtmlContent;
import com.accountancy.despacho_castillo_asociados.shared.utils.UserValidationsHelper;
import jakarta.mail.MessagingException;

import java.time.LocalDateTime;
import java.util.Optional;


public class CreateClientUseCase {

    private final ClientRepository clientRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;

    public CreateClientUseCase(ClientRepository clientRepository, VerificationCodeRepository verificationCodeRepository, EmailService emailService) {
        this.clientRepository = clientRepository;
        this.verificationCodeRepository = verificationCodeRepository;
        this.emailService = emailService;
    }

    public Client execute(ClientRequest clientRequest) throws MessagingException {

        if (clientRequest == null) {
            throw new BadRequestException("Client cannot be null");
        }

        if (clientRequest.getName() == null || clientRequest.getName().isEmpty()) {
            throw new BadRequestException("Client name cannot be null or empty");
        }

        UserValidationsHelper.validateEmail(clientRequest.getEmail());
        UserValidationsHelper.validatePassword(clientRequest.getPassword());

        Optional<Client> existingClient = clientRepository.findByEmailAndActive(clientRequest.getEmail());

        if (existingClient.isPresent() && !existingClient.get().isEnabled()) {

            String otp = GenerateOtp.execute();
            sendAndSaveEmailVerification(existingClient.get(), otp);
            throw new RedirectionException("Cliente con email " + clientRequest.getEmail() + " no se ha verificado. " +
                    "Se ha enviado un nuevo código de verificación a su correo electrónico.");
        }

        if (existingClient.isPresent()) {
            throw new BadRequestException("Client with email " + clientRequest.getEmail() + " already exists");
        }

        Optional<Client> inactiveClient = clientRepository.findByNameAndIsInactive(clientRequest.getName());

        if (inactiveClient.isPresent()) {
            Client reactivatedClient = inactiveClient.get();
            clientRepository.activate(reactivatedClient.getId());
            return reactivatedClient;
        }

        Client client = clientRepository.create(clientRequest);

        String otp = GenerateOtp.execute();

        sendAndSaveEmailVerification(client, otp);

        return client;
    }

    private void sendAndSaveEmailVerification(Client client, String code) throws MessagingException {

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(client.getEmail());
        verificationCode.setCode(code);
        verificationCode.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        verificationCodeRepository.save(verificationCode);

        String subject = "Codigo de verificación para tu cuenta";
        String body = new HtmlContent().generateVerificationEmail(client.getName(),code);

        emailService.sendHtmlEmail(
            client.getEmail(),
            subject,
            body
        );
    }

}


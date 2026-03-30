package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Auth.VerificationCode;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.verificationcode.VerificationCodeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
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

        boolean existingClient = clientRepository.findByEmailAndActive(clientRequest.getEmail()).isPresent();

        if (existingClient) {
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

        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(client.getEmail());
        verificationCode.setCode(otp);
        verificationCode.setExpiryDate(LocalDateTime.now().plusMinutes(15));

        VerificationCode codeSaved = verificationCodeRepository.save(verificationCode);

        String subject = "Codigo de verificación para tu cuenta";
        String body = new HtmlContent().generateVerificationEmail(client.getName(),codeSaved.getCode());

        emailService.sendHtmlEmail(
            client.getEmail(),
            subject,
            body
        );

        return client;
    }

}


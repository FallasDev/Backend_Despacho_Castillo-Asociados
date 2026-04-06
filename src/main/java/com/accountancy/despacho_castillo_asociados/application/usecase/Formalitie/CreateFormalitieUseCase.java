package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Builder;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieBuilder;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class CreateFormalitieUseCase {

    private final FormalitieRepository formalitieRepository;
    private final ServiceRepository serviceRepository;
    private final CustomFieldRepository customFieldRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final FormalitieCustomFieldRepository formalitieCustomFieldRepository;
    private final Messages messages;

    public CreateFormalitieUseCase(FormalitieRepository formalitieRepository, ServiceRepository serviceRepository, CustomFieldRepository customFieldRepository, ClientRepository clientRepository, UserRepository userRepository, FormalitieCustomFieldRepository formalitieCustomFieldRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.serviceRepository = serviceRepository;
        this.customFieldRepository = customFieldRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.formalitieCustomFieldRepository = formalitieCustomFieldRepository;
        this.messages = messages;
    }

    @Transactional
    public Formalitie execute(FormalitieRequest request) {


        if (request == null) {
            throw new BadRequestException(messages.get("formality.exception.create.cannot_be_null"));
        }

        if (request.getServiceId() <= 0) {
            throw new BadRequestException(messages.get("formality.exception.create.service.invalid", new Object[]{request.getServiceId()}));
        }

        if (request.getClientId() <= 0) {
            throw new BadRequestException(messages.get("formality.exception.create.client.invalid", new Object[]{request.getClientId()}));
        }

        Optional<DomainService> service = serviceRepository.findById(request.getServiceId());

        if (service.isEmpty()) {
            throw new BadRequestException(messages.get("formality.exception.create.service.not_found", new Object[]{request.getServiceId()}));
        }

        if (!service.get().isActive()) {
            throw new BadRequestException(messages.get("formality.exception.create.service.not_found", new Object[]{request.getServiceId()}));
        }

        // Additional validation for client existence can be added here if a ClientRepository is available
        Client client = clientRepository.findById(request.getClientId()).orElse(null);

        if (client == null || !client.isActive()) {
            throw new BadRequestException(messages.get("formality.exception.create.client.not_found", new Object[]{request.getClientId()}));
        }

        User user = userRepository.findById(request.getUserId()).orElse(null);

        Formalitie formalitie = new FormalitieBuilder()
                .setService(service.get())
                .setClient(client)
                .setUser(user)
                .setState(user != null ? FormalitiesState.IN_PROGRESS : FormalitiesState.PENDING)
                .setTemplateId(request.getTemplateId())
                .getResult();


        Formalitie createdFormalitie = formalitieRepository.create(formalitie, service.get(), client, user);

        if (createdFormalitie == null) {
            throw new BadRequestException(messages.get("formality.exception.create.failed"));
        }

        List<FormalitieCustomField> list = request.getCustomFields().stream().map(
                customField -> new FormalitieCustomField(customFieldRepository.findById(customField.getCustomFieldId()).orElseThrow(
                        () -> new BadRequestException(messages.get("formalitycustomfield.exception.create.customfield.not_found",
                                new Object[]{customField.getCustomFieldId()}))
                ),createdFormalitie ,customField.getValue())).toList();

        List<FormalitieCustomField> createdCustomFields = formalitieCustomFieldRepository.createBatch(list);

        createdFormalitie.setCustomFields(
                createdCustomFields
        );

        return createdFormalitie;

    }

}

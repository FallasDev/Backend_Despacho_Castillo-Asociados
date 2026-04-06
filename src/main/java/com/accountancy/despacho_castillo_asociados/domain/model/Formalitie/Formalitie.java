package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Formalitie {

    private int id;
    private DomainService service;
    private Client client;
    private User user;
    private FormalitiesState state;
    private LocalDateTime applicationDate;
    List<FormalitieCustomField> customFields;
    private int templateId;

    public Formalitie() {
    }

    public Formalitie(int id, DomainService service, User user, Client client, FormalitiesState state, LocalDateTime applicationDate, int templateId) {
        this.id = id;
        this.user = user;
        this.service = service;
        this.client = client;
        this.state = state;
        this.applicationDate = applicationDate;
        this.templateId = templateId;
    }

    public int getId() {
        return id;
    }

    public DomainService getService() {
        return service;
    }

    public User getUser() {
        return user;
    }

    public Client getClient() {
        return client;
    }

    public FormalitiesState getState() {
        return state;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setState(FormalitiesState state) {
        this.state = state;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setService(DomainService service) {
        this.service = service;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public List<FormalitieCustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<FormalitieCustomField> customFields) {
        this.customFields = customFields;
    }

        public int getTemplateId() {
            return templateId;
        }

        public void setTemplateId(int templateId) {
            this.templateId = templateId;
        }
}

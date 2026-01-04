package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;

import java.time.LocalDateTime;
import java.util.Date;

public class Formalitie {

    private int id;
    private DomainService service;
    private Object client;
    private Object user;
    private FormalitiesState state;
    private LocalDateTime applicationDate;


    public Formalitie(int id, DomainService service, Object user, Object client, FormalitiesState state, LocalDateTime applicationDate) {
        this.id = id;
        this.user = user;
        this.service = service;
        this.client = client;
        this.state = state;
        this.applicationDate = applicationDate;
    }

    public int getId() {
        return id;
    }

    public DomainService getService() {
        return service;
    }

    public Object getUser() {
        return user;
    }

    public Object getClient() {
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

    public void setUser(Object user) {
        this.user = user;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    public void setService(DomainService service) {
        this.service = service;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }
}

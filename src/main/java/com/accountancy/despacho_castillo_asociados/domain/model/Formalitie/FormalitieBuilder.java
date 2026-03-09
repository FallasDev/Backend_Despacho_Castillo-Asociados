package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;

import java.time.LocalDateTime;
import java.util.Date;

public class FormalitieBuilder implements Builder {

    private Formalitie formalitie;

    public FormalitieBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.formalitie = new Formalitie(0, null, null, null, null, null);
    }

    @Override
    public FormalitieBuilder setId(int id) {
        this.formalitie.setId(id);
        return this;
    }

    @Override
    public FormalitieBuilder setService(DomainService service) {
        this.formalitie.setService(service);
        return this;
    }

    @Override
    public FormalitieBuilder setUser(User user) {
        this.formalitie.setUser(user);
        return this;
    }

    @Override
    public FormalitieBuilder setClient(Client client) {
        this.formalitie.setClient(client);
        return this;
    }

    @Override
    public FormalitieBuilder setState(FormalitiesState state) {
        this.formalitie.setState(state);
        return this;
    }

    @Override
    public FormalitieBuilder setApplicationDate(LocalDateTime applicationDate) {
        this.formalitie.setApplicationDate(applicationDate);
        return this;
    }

    @Override
    public Formalitie getResult() {
        return this.formalitie;
    }
}

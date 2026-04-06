package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;

import java.time.LocalDateTime;
import java.util.Date;

public interface Builder {

    void reset();
    FormalitieBuilder setId(int id);
    FormalitieBuilder setService(DomainService service);
    FormalitieBuilder setUser(User user);
    FormalitieBuilder setClient(Client client);
    FormalitieBuilder setState(FormalitiesState state);
    FormalitieBuilder setApplicationDate(LocalDateTime applicationDate);
    FormalitieBuilder setTemplateId(int templateId);
    Formalitie getResult();

}

package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;

import java.time.LocalDateTime;
import java.util.Date;

public interface Builder {

    void reset();
    FormalitieBuilder setId(int id);
    FormalitieBuilder setService(DomainService service);
    FormalitieBuilder setUser(Object user);
    FormalitieBuilder setClient(Object client);
    FormalitieBuilder setState(FormalitiesState state);
    FormalitieBuilder setApplicationDate(LocalDateTime applicationDate);
    Formalitie getResult();

}

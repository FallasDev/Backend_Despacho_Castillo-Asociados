package com.accountancy.despacho_castillo_asociados.domain.model.Service;

public interface ServiceCountProjection {

    Integer getId();
    String getName();
    String getDescription();
    Boolean getActive();
    Long getTotalFormalities();
}
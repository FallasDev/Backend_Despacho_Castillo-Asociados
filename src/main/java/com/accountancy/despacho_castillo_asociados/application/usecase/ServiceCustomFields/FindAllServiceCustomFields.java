package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import jakarta.transaction.Transactional;

import java.util.List;

public class FindAllServiceCustomFields {

    private final ServiceCustomFieldsRepository repository;

    public FindAllServiceCustomFields(ServiceCustomFieldsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<ServiceCustomField> execute() {
        return repository.findAllWithoutPagination();
    }

}

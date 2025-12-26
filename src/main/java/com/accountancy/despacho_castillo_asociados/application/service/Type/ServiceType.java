package com.accountancy.despacho_castillo_asociados.application.service.Type;

import com.accountancy.despacho_castillo_asociados.application.usecase.Type.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceType {

    private final CreateTypeUseCase createTypeUseCase;
    private final FindByIdTypeUseCase findByIdTypeUseCase;
    private final DeactiveTypeUseCase deactiveTypeUseCase;
    private final UpdateTypeUseCase updateTypeUseCase;
    private final FindByNameTypeUseCase findByNameTypeUseCase;
    private final FindAllTypeUseCase findAllTypeUseCase;

    public ServiceType(
            CreateTypeUseCase createTypeUseCase,
            FindByIdTypeUseCase findByIdTypeUseCase,
            DeactiveTypeUseCase deactiveTypeUseCase,
            UpdateTypeUseCase updateTypeUseCase,
            FindByNameTypeUseCase findByNameTypeUseCase,
            FindAllTypeUseCase findAllTypeUseCase
    ) {
        this.createTypeUseCase = createTypeUseCase;
        this.findByIdTypeUseCase = findByIdTypeUseCase;
        this.deactiveTypeUseCase = deactiveTypeUseCase;
        this.updateTypeUseCase = updateTypeUseCase;
        this.findByNameTypeUseCase = findByNameTypeUseCase;
        this.findAllTypeUseCase = findAllTypeUseCase;
    }

    public Type createType(Type type) {
        return createTypeUseCase.execute(type);
    }

    public Type findByIdType(int id) {
        return findByIdTypeUseCase.execute(id);
    }

    public void deactiveType(int id) {
        deactiveTypeUseCase.execute(id);
    }
    public Type updateType(Type type, int id) {
        return updateTypeUseCase.execute(type, id);
    }
    public Type findByNameType(String name) {
        return findByNameTypeUseCase.execute(name);
    }
    public List<Type> findAllType() {
        return findAllTypeUseCase.execute();
    }

}

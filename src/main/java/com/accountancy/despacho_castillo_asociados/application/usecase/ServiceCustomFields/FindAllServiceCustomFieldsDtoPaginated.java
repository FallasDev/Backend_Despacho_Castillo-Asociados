package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield.CustomFieldDTO;
import com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield.ServiceCustomFieldDTO;
import com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield.ServiceDTO;
import com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield.TypeDTO;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField.ServiceCustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField.JPAServiceCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindAllServiceCustomFieldsDtoPaginated {

    private final JPAServiceCustomFieldRepository jpaRepository;

    public FindAllServiceCustomFieldsDtoPaginated(JPAServiceCustomFieldRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Transactional
    public PageResult<ServiceCustomFieldDTO> execute(String name, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<ServiceCustomFieldEntity> entityPage;
        if (name == null || name.isBlank()) {
            entityPage = jpaRepository.findByActiveTrue(pageable);
        } else {
            entityPage = jpaRepository.findByNameContainingIgnoreCaseAndActiveTrue(name, pageable);
        }

        List<Integer> ids = entityPage.getContent().stream().map(ServiceCustomFieldEntity::getId).collect(Collectors.toList());

        // Fetch full entities with customFields and types by ids to avoid N+1
        List<ServiceCustomFieldEntity> fullEntities = ids.isEmpty() ? List.of() : jpaRepository.findAllByIdInWithCustomFieldsAndType(ids);

        List<ServiceCustomFieldDTO> dtos = fullEntities.stream().map(entity -> {
            Map<Integer, CustomFieldEntity> unique = entity.getCustomFields().stream()
                    .filter(java.util.Objects::nonNull)
                    .collect(Collectors.toMap(CustomFieldEntity::getId, cf -> cf, (a, b) -> a));

            List<CustomFieldDTO> customFieldDTOS = unique.values().stream().map(cfEntity -> new CustomFieldDTO(
                    cfEntity.getId(),
                    cfEntity.getName(),
                    cfEntity.isRequired(),
                    cfEntity.isExclusive(),
                    cfEntity.isActive(),
                    cfEntity.getPlaceholder(),
                    cfEntity.getHelpText(),
                    cfEntity.getDefaultValue(),
                    new TypeDTO(
                            cfEntity.getType().getId(),
                            cfEntity.getType().getName(),
                            cfEntity.getType().isActive()
                    )
            )).collect(Collectors.toList());

            ServiceDTO serviceDTO = new ServiceDTO(
                    entity.getService().getId(),
                    entity.getService().getName(),
                    entity.getService().getDescription(),
                    entity.getService().isActive()
            );

            return new ServiceCustomFieldDTO(
                    entity.getId(),
                    entity.getName(),
                    entity.isActive(),
                    serviceDTO,
                    customFieldDTOS
            );
        }).collect(Collectors.toList());

        return new PageResult<>(dtos, page, size, (int) entityPage.getTotalElements(), entityPage.getTotalPages());
    }
}

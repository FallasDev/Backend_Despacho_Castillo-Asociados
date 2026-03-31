package com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "service_custom_fields")
public class ServiceCustomFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    @ManyToMany
    @JoinTable(
            name = "service_custom_field_rel",
            joinColumns = @JoinColumn(name = "service_custom_field_id"),
            inverseJoinColumns = @JoinColumn(name = "custom_field_id")
    )
    private List<CustomFieldEntity> customFields;

    public String toString() {
        return "ServiceCustomFieldEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", serviceId=" + (service != null ? service.getId() : "null") +
                ", customFieldIds=" + (customFields != null ? customFields.stream().map(CustomFieldEntity::getId).toList() : "null") +
                '}';
    }

}

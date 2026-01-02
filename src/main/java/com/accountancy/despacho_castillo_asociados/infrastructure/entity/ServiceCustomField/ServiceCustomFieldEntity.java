package com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "service_custom_fields")
public class ServiceCustomFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "service_id", nullable = false)
    private int serviceId;

    @Column(name = "custom_field_id", nullable = false)
    private int customFieldId;

    @Column(nullable = false)
    private boolean active;

}

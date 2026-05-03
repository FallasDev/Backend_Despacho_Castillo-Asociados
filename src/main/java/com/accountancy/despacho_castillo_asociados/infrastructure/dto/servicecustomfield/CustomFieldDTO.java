package com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield;

public class CustomFieldDTO {

    private TypeDTO type;
    private String defaultValue;
    private String helpText;
    private String placeholder;
    private boolean active;
    private boolean exclusive;
    private boolean required;
    private String name;
    private int id;

    public CustomFieldDTO() {}


    public CustomFieldDTO(int id, String name, boolean required, boolean exclusive, boolean active, String placeholder, String helpText, String defaultValue, TypeDTO typeDTO) {
        this.type = typeDTO;
        this.defaultValue = defaultValue;
        this.helpText = helpText;
        this.placeholder = placeholder;
        this.active = active;
        this.exclusive = exclusive;
        this.required = required;
        this.name = name;
        this.id = id;

    }


    public TypeDTO getType(){ return type; }
    public String getDefaultValue(){ return defaultValue; }
    public String getHelpText(){ return helpText; }
    public String getPlaceholder(){ return placeholder; }
    public boolean isActive(){ return active; }
    public boolean isExclusive(){ return exclusive; }
    public boolean isRequired(){ return required; }
    public String getName(){ return name; }
    public int getId(){ return id; }
}

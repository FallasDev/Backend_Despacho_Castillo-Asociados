package com.accountancy.despacho_castillo_asociados.domain.model.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;

public interface Builder {

    void reset();
    ReportFieldBuilder setId(int id);
    ReportFieldBuilder setLabel(String label);
    ReportFieldBuilder setOrder(int order);
    ReportFieldBuilder setPlaceholder(String placeholder);
    ReportFieldBuilder setHelpText(String helpText);
    ReportFieldBuilder setDefaultValue(String defaultValue);
    ReportFieldBuilder setMultiple(boolean multiple);
    ReportFieldBuilder setActive(boolean active);
    ReportFieldBuilder setType(Type type);
    ReportFieldBuilder setReportCategory(ReportCategory reportCategory);
    ReportField getResult();

}

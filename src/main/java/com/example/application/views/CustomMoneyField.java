

package com.example.application.views;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.shared.HasTooltip;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationStatusChangeEvent;
import com.vaadin.flow.data.binder.ValidationStatusChangeListener;
import com.vaadin.flow.shared.Registration;
import org.apache.commons.lang3.StringUtils;

public class CustomMoneyField extends CustomField<Long> implements HasValidator<Long> {

    private final TextField content = new TextField();

    public CustomMoneyField(String label) {
        super(null);
        add(content);
        getContent().addThemeName("align-right");
        setLabel(label);
    }
    @Override
    protected void setPresentationValue(Long newPresentationValue) {
        getContent().setValue(newPresentationValue + "");
    }


    @Override
    protected Long generateModelValue() {
        return StringUtils.isNotEmpty(getContent().getValue()) ? Long.parseLong(getContent().getValue()) : null;
    }
    public TextField getContent() {
        return content;
    }

    @Override
    public Registration addValidationStatusChangeListener(ValidationStatusChangeListener<Long> listener) {
        return this.getContent().addClientValidatedEventListener((event) -> {
            listener.validationStatusChanged(new ValidationStatusChangeEvent(this, !this.isInvalid()));
        });
    }
}
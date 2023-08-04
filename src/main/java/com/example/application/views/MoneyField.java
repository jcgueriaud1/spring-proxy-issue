

package com.example.application.views;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.shared.HasTooltip;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.binder.HasValidator;
import com.vaadin.flow.data.binder.ValidationStatusChangeEvent;
import com.vaadin.flow.data.binder.ValidationStatusChangeListener;
import com.vaadin.flow.shared.Registration;
import org.apache.commons.lang3.StringUtils;

public class MoneyField extends AbstractCompositeField<TextField, MoneyField, Long>  implements HasStyle, HasValidation, HasSize, Focusable<MoneyField>, HasLabel, HasTooltip, HasValidator<Long> {

    private final int scale;
    private final boolean negativeValueAllowed;

    public MoneyField() {
        this(true);
    }

    public MoneyField(boolean negativeValueAllowed) {
        this("", negativeValueAllowed, 0);
    }

    public MoneyField(String label) {
        this(label, true, 0);
    }

    public MoneyField(String label, boolean negativeValueAllowed) {
        this(label, negativeValueAllowed, 0);
    }
    public MoneyField(String label, boolean negativeValueAllowed, int scale) {
        super(null);
        this.negativeValueAllowed = negativeValueAllowed;
        this.scale = scale;
        getContent().addThemeName("align-right");
        getContent().setLabel(label);
        getContent().addValueChangeListener(event -> {
            if (event.isFromClient()) {
                setModelValue(StringUtils.isNotEmpty(event.getValue()) ? Long.parseLong(event.getValue()) : null, event.isFromClient());
            }
        });
    }
    @Override
    public Tooltip setTooltipText(String text) {
        return getContent().setTooltipText(text);
    }

    @Override
    public Tooltip getTooltip() {
        return getContent().getTooltip();
    }

    @Override
    protected void setPresentationValue(Long newPresentationValue) {
        getContent().setValue(newPresentationValue + "");
    }

    public void addThemeVariants(TextFieldVariant... variants) {
        getContent().addThemeVariants(variants);
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        getContent().setErrorMessage(errorMessage);
    }

    @Override
    public String getErrorMessage() {
        return getContent().getErrorMessage();
    }

    @Override
    public void setInvalid(boolean invalid) {
        getContent().setInvalid(invalid);
    }

    @Override
    public boolean isInvalid() {
        return getContent().isInvalid();
    }

    @Override
    public void focus() {
        getContent().focus();
    }

    @Override
    public void setLabel(String label) {
        getContent().setLabel(label);
    }

    public Registration addValidationStatusChangeListener(ValidationStatusChangeListener<Long> listener) {
        return ((TextField)this.getContent()).addClientValidatedEventListener((event) -> {
            if (event.isValid() == isInvalid()) {
                listener.validationStatusChanged(new ValidationStatusChangeEvent(this, !this.isInvalid()));
            } else  {
                // validation ignored
                System.out.println("Validation status unchanged");
            }
        });
    }
}
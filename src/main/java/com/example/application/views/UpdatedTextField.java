package com.example.application.views;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationStatusChangeEvent;
import com.vaadin.flow.data.binder.ValidationStatusChangeListener;
import com.vaadin.flow.shared.Registration;

/**
 * @author jcgueriaud
 */
public class UpdatedTextField extends TextField {

    public UpdatedTextField(String label) {
        super(label);
    }

    public Registration addValidationStatusChangeListener(ValidationStatusChangeListener<String> listener) {
        return this.addClientValidatedEventListener((event) -> {
            if (event.isValid() == isInvalid()) {
                listener.validationStatusChanged(new ValidationStatusChangeEvent(this, !this.isInvalid()));
            } else  {
                // validation ignored
                System.out.println("Validation status unchanged");
            }
        });
    }
}

package com.example.application.views.helloworld;


import com.example.application.views.*;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;

import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.Objects;

@PageTitle("Hello World Vaadin 24")
@Route(value = "")
@AnonymousAllowed
public class HelloWorldView extends HorizontalLayout {

    private Binder<FakeBean> binder = new Binder<>();

    private TextField name = new TextField("Name");
    private TextField name2 = new TextField("Name2");

    private Span errorMessage = new Span();

    public HelloWorldView() {
        FakeBean fakeBean = new FakeBean();
        binder.forField(name).asRequired("name is required")
                .bind(FakeBean::getName, FakeBean::setName);
        binder.forField(name2).asRequired("name2 is required")
                .bind(FakeBean::getName2, FakeBean::setName2);
        binder.setBean(fakeBean);
        binder.withValidator(bean ->
                        Objects.equals(bean.getName(), "test") || Objects.equals(bean.getName2(), "test"),
                "At least one field should be test"
        );
        binder.setValidationStatusHandler(status -> {
            System.out.println("Errors " + status.getValidationErrors().size());
            if (!status.getBeanValidationErrors().isEmpty()) {
                errorMessage.setText(status.getBeanValidationErrors().get(0).getErrorMessage());
                errorMessage.setVisible(true);
            } else {
                errorMessage.setVisible(false);
            }
        });
        add(name, name2, errorMessage);
    }

}

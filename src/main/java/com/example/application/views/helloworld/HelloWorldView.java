package com.example.application.views.helloworld;


import com.example.application.views.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;

import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Hello World Vaadin 24")
@Route(value = "")
@AnonymousAllowed
public class HelloWorldView extends HorizontalLayout {

    private Binder<FakeBean> binder = new Binder<>();

    private TextField name = new TextField("Name");
    private TextField name2 = new TextField("Name2");

    public HelloWorldView() {
        FakeBean fakeBean = new FakeBean();
        binder.forField(name).asRequired("name is required")
                .withValidator(value -> {
                    System.out.println("Validate " + value);
                    return (value.length() > 2);
                }, "Length should be > 2")
                .bind(FakeBean::getName, FakeBean::setName);
        binder.forField(name2).asRequired("name2 is required")
                .withValidator(value -> {
                    System.out.println("Validate name2 " + value);
                    return (value.length() > 2);
                }, "Length should be > 2")
                .bind(FakeBean::getName2, FakeBean::setName2);
        binder.setBean(fakeBean);
        add(name, name2);
    }

}

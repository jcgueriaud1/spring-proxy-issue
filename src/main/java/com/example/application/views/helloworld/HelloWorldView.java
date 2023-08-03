package com.example.application.views.helloworld;


import com.example.application.views.FakeBean;
import com.example.application.views.MainLayout;
import com.example.application.views.MoneyField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.*;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Hello World Vaadin 24")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
public class HelloWorldView extends HorizontalLayout {

    private Binder<FakeBean> binder = new Binder<>();

    private TextField name = new TextField("Name");
    private MoneyField id = new MoneyField("Id");

    public HelloWorldView() {
        FakeBean fakeBean = new FakeBean();
        binder.forField(name).asRequired("name is required")
                .withValidator(value -> {
                    System.out.println("Validate " + value);
                    return (value.length() > 2);
                }, "Length should be > 0")
                .bind(FakeBean::getName, FakeBean::setName);
        binder.setBean(fakeBean);
        add(id, name);
    }

}

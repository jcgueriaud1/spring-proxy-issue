package com.example.application.views.helloworld;


import com.example.application.views.*;
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

    private TextField name = new UpdatedTextField("Name");
    private TextField name2 = new UpdatedTextField("Name2");
    private MoneyField id = new MoneyField("Id");
    private CustomMoneyField id2 = new CustomMoneyField("Id2");

    public HelloWorldView() {
        FakeBean fakeBean = new FakeBean();
        binder.forField(id).asRequired("id is required")
                .withValidator(value -> {
                    System.out.println("Validate id: " + value);
                    return (value > 2);
                }, "Value should be > 2")
                .bind(FakeBean::getId, FakeBean::setId);
        binder.forField(id2).asRequired("id2 is required")
                .withValidator(value -> {
                    System.out.println("Validate id 2: " + value);
                    return (value > 2);
                }, "Value should be > 2")
                .bind(FakeBean::getId2, FakeBean::setId2);
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
        add(id, id2, name, name2);
    }

}

package com.example.application.views;


import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Hello World Vaadin 24")
@Route(value = "custom")
@AnonymousAllowed
public class CustomFieldView extends HorizontalLayout {
    private Binder<FakeBean> binder = new Binder<>();
    private TextField name = new TextField("Name");
    private CustomMoneyField id2 = new CustomMoneyField("Id2");

    public CustomFieldView() {
        FakeBean fakeBean = new FakeBean();
        binder.forField(id2).asRequired("id2 is required")
                .withValidator(value -> {
                    System.out.println("Validate id 2: " + value);
                    return (value > 2);
                }, "Value should be > 2")
                .bind(FakeBean::getId2, FakeBean::setId2);
        binder.forField(name).asRequired("name is required")
                .withValidator(value -> {
                    System.out.println("Validate name: " + value);
                    return (value.length() > 2);
                }, "Length should be > 2")
                .bind(FakeBean::getName, FakeBean::setName);
        binder.setBean(fakeBean);
        add(id2, name);
    }
}

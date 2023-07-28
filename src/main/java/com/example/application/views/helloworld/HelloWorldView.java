package com.example.application.views.helloworld;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.*;

import jakarta.annotation.security.RolesAllowed;

@PageTitle("Hello World Vaadin 23")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@RolesAllowed("USER")
public class HelloWorldView extends HorizontalLayout {

    private Button navigateButton;

    public HelloWorldView() {
        navigateButton = new Button("Navigate by button");
        navigateButton.addClickListener(e -> {
            UI.getCurrent().navigate(HelloWorldView.class);
        });
        navigateButton.addClickShortcut(Key.ENTER);

        setMargin(true);

        add(navigateButton);

        add(new RouterLink( "test", HelloWorldView.class));
        System.out.println("HelloWorldView constructor");
    }

}

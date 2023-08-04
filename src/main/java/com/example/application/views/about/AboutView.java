package com.example.application.views.about;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Bean;
import org.vaadin.tatu.TwinColSelect;
import org.vaadin.tatu.TwinColSelectListDataView;

import java.util.Set;
import java.util.stream.Collectors;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
@RolesAllowed("USER")
public class AboutView extends VerticalLayout {

    TwinColSelectListDataView<String> dataView = null;
    public AboutView() {
        Binder<Bean> binder = new Binder<>();

// Setup TwinColSelect
        TwinColSelect<String> select = new TwinColSelect<>();
        select.setLabel("Select Two and Four");
        dataView = select.setItems("One", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten");

        select.setHeight("350px");
        select.setWidth("500px");
        select.setTooltipGenerator(item -> "This is item " + item);

        select.setI18n(TwinColSelect.TwinColSelectI18n.getDefault());

// Bind TwinColSelect to bean property
        binder.forField(select).asRequired("Empty selection not allowed")
                .withValidator(
                        sel -> {
                            System.out.println("Validation called");
                            return sel.contains("Two") && sel.contains("Four");
                        },
                        "Selection needs to contain two and four")
                .bind(Bean::getSelection, Bean::setSelection);

// Populate form
        Bean bean = new Bean();
        Set<String> selection = dataView.getItems()
                .filter(item -> item.contains("w")).collect(Collectors.toSet());
        bean.setSelection(selection);
        binder.setBean(bean);
        add(select);
    }

    public class Bean {
        private Set<String> selection;

        public Set<String> getSelection() {
            return selection;
        }

        public void setSelection(Set<String> selection) {
            this.selection = selection;
        }
    };

}

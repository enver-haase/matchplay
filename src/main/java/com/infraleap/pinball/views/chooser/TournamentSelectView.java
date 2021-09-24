package com.infraleap.pinball.views.chooser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infraleap.pinball.data.config.Config;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;

@PageTitle("Tournament Selector")
@Route(value = "tournament-select")
@RouteAlias(value = "")
public class TournamentSelectView extends HorizontalLayout {

    @Value("classpath:data/config.json")
    Resource resourceFile;

    private TextField name;
    private Button sayHello;

    public TournamentSelectView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                Config config = mapper.readValue(resourceFile.getFile(), Config.class);
                System.out.println(config.getTournamentSets().get(0));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Notification.show("Hello " + name.getValue());
        });
    }

}

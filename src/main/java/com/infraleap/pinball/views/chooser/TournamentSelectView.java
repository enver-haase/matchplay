package com.infraleap.pinball.views.chooser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.config.Config;
import com.infraleap.pinball.event.TimerEvent;
import com.infraleap.pinball.layout.MainLayout;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;

@PageTitle("Tournament Selector")
@Route(value = "tournament-select", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TournamentSelectView extends VerticalLayout {

    @Value("classpath:data/config.json")
    Resource resourceFile;

    private TextField name;
    private Button sayHello;

    public TournamentSelectView() {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");

        VerticalAutoScroller vas = new VerticalAutoScroller();
        vas.setHeight("60%");
        add(name, sayHello, vas);
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

package com.infraleap.pinball.views.about;

import com.infraleap.pinball.data.Standing;
import com.infraleap.pinball.data.Tournament;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

@PageTitle("About")
@Route(value = "about")
public class AboutView extends Div implements HasUrlParameter<String> {

    private final MatchPlayService matchPlayService;

    private final Div wrapper;
    private Tournament tournament;
    private Standing[] standings;

    public AboutView(MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassNames("about-view", "flex", "flex-col", "h-full", "items-center", "justify-center", "p-l",
                "text-center", "box-border");

        wrapper = new Div();
        wrapper.addClassNames("box-border");
        wrapper.setWidth("176px");
        wrapper.setHeight("176px");
        Style wrapperStyle = wrapper.getStyle();
        wrapperStyle.set("padding-top", "34px");
        wrapperStyle.set("border-radius", "100px");
        wrapperStyle.set("background", "var(--lumo-shade-10pct)");

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("150px");
        wrapper.add(img);
    }

    private void update(){

        removeAll();
        add(wrapper);

        if (tournament != null) {
            add(new H2("Welcome To: " + tournament.getName()));
        }
        if (standings != null){
            add(new H3("Lead: "+standings[0].getName()));
        }
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        tournament = matchPlayService.getTournament(s);
        standings = matchPlayService.getStandings(s);

        update();
    }
}

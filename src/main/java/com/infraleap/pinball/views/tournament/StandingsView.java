package com.infraleap.pinball.views.tournament;

import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.matchplay.Standing;
import com.infraleap.pinball.data.matchplay.Tournament;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.Comparator;

@PageTitle("Standings")
@Route(value = "standings", layout = MainLayout.class)
public class StandingsView extends VerticalLayout implements HasUrlParameter<String> {

    private final MatchPlayService matchPlayService;

    private Tournament tournament;
    private Standing[] standings;

    public StandingsView(MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassNames("standings-view");
        addClassName("items-center");

        this.setSpacing(false);
        this.setPadding(false);

        //setHeightFull();

        //addClassNames("about-view", "flex", "flex-col", "h-full", "items-center", "justify-center", "p-l",
        //        "text-center", "box-border");

    }

    private void update(){

        removeAll();

        if (tournament != null) {
            VerticalLayout vl = new VerticalLayout();
            HorizontalLayout hl = new HorizontalLayout(new H1(tournament.getName()));
            hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            hl.setWidthFull();
            vl.add(new H4("Welcome To: "), hl);
            vl.getStyle().set("background", "#33cccc");
            add(vl);
            vl.getElement().getStyle().set("position", "sticky");
            vl.getElement().getStyle().set("top", "0");
        }

        if (standings != null){
            Arrays.sort(standings, Comparator.comparingInt(Standing::getPosition));

            VerticalAutoScroller vas = new VerticalAutoScroller();
            add(vas);

            for (Standing standing : this.standings){
                VerticalLayout verticalLayout = new VerticalLayout();
                verticalLayout.addClassName("bordered");

                HorizontalLayout standingLayout = new HorizontalLayout();
                standingLayout.setWidthFull();
                standingLayout.add(new H3(standing.getPosition().toString()+": "+standing.getName()));
                FlexLayout wrapper = new FlexLayout(new H4("Points: "+standing.getPoints()));
                wrapper.setJustifyContentMode(JustifyContentMode.END);
                standingLayout.add(wrapper);
                standingLayout.expand(wrapper);

                standingLayout.setAlignItems(FlexComponent.Alignment.BASELINE);


                verticalLayout.add(standingLayout);
                vas.add(verticalLayout);
            }

        }

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        tournament = matchPlayService.getTournament(s);
        standings = matchPlayService.getStandings(s);

        update();
    }
}

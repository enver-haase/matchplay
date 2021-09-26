package com.infraleap.pinball.views.tournament;

import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.matchplay.Standing;
import com.infraleap.pinball.data.matchplay.Tournament;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Arrays;
import java.util.Comparator;

@PageTitle("Standings")
@Route(value = "standings", layout = MainLayout.class)
public class StandingsView extends Div implements HasUrlParameter<String> {

    private final MatchPlayService matchPlayService;

    private Tournament tournament;
    private Standing[] standings;

    public StandingsView(MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassNames("standings-view");
        setHeightFull();

        addClassNames("about-view", "flex", "flex-col", "h-full", "items-center", "justify-center", "p-l",
                "text-center", "box-border");

    }

    private void update(){

        removeAll();

        if (tournament != null) {
            add(new H4("Welcome To: "));
            add(new H2(tournament.getName()));
        }


        if (standings != null){
            Arrays.sort(standings, Comparator.comparingInt(Standing::getPosition));

            VerticalAutoScroller vas = new VerticalAutoScroller();
            vas.addClassName("content");
            for (Standing standing : this.standings){
                VerticalLayout standingLayout = new VerticalLayout();
                standingLayout.add(new H3(standing.getPosition().toString()+": "+standing.getName()));
                standingLayout.add(new H4("Points: "+standing.getPoints()));
                vas.add(standingLayout);
            }

            add(vas);
        }

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        tournament = matchPlayService.getTournament(s);
        standings = matchPlayService.getStandings(s);

        update();
    }
}

package com.infraleap.pinball.views.tournament;

import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.matchplay.*;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Standings")
@Route(value = "match", layout = MainLayout.class)
public class MatchView extends VerticalLayout implements HasUrlParameter<String> {

    private final MatchPlayService matchPlayService;

    private Tournament tournament;
    private Standing[] standings;
    private Result[] results;

    public MatchView(MatchPlayService matchPlayService) {
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
            hl.setJustifyContentMode(JustifyContentMode.CENTER);
            hl.setWidthFull();
            vl.add(new H4("Welcome To: "), hl);
            vl.getStyle().set("background", "#33cccc");
            add(vl);
            vl.getElement().getStyle().set("position", "sticky");
            vl.getElement().getStyle().set("top", "0");
        }

        if (results != null){
            for (Result result : results){
                for (Game game : result.getGames()){

                    Player[] players = game.getPlayers().toArray(new Player[0]);
                    //Arrays.sort(players, Comparator.comparingInt( player -> player.getGame() ));
                    for (Player player : players){
                        System.out.println(player);
                    }
                }
            }







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

                standingLayout.setAlignItems(Alignment.BASELINE);


                verticalLayout.add(standingLayout);
                vas.add(verticalLayout);
            }

        }

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        tournament = matchPlayService.getTournament(s);
        standings = matchPlayService.getStandings(s);
        results = matchPlayService.getResults(s);

        update();
    }
}

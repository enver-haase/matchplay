package com.infraleap.pinball.views.chooser;

import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.config.Configuration;
import com.infraleap.pinball.data.config.TournamentSet;
import com.infraleap.pinball.data.matchplay.Result;
import com.infraleap.pinball.data.matchplay.Tournament;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.ConfigurationService;
import com.infraleap.pinball.service.MatchPlayService;
import com.infraleap.pinball.views.tournament.MatchView;
import com.infraleap.pinball.views.tournament.StandingsView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;

import java.util.List;

@PageTitle("Tournament Selector")
@Route(value = "tournament-select", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
public class TournamentSelectView extends VerticalLayout {


    public TournamentSelectView(ConfigurationService configurationService, MatchPlayService matchPlayService) {

        addClassName("tournament-select-view");
        addClassName("items-center");
        setPadding(false);
        setSpacing(false);
        setMargin(false);

        Configuration config = configurationService.getConfiguration();
        List<TournamentSet> tourneySets = config.getTournamentSets();

        VerticalAutoScroller vas = new VerticalAutoScroller();
        vas.setWidthFull();
        add(vas);

        for (TournamentSet tourneySet : tourneySets){
            VerticalLayout verticalLayout = new VerticalLayout();
            verticalLayout.addClassName("bordered-silver");

            verticalLayout.add(new H1(tourneySet.getIfpaName()));
            for (String tourney : tourneySet.getIds()){
                Tournament tournament = matchPlayService.getTournament(tourney);

                HorizontalLayout hl = new HorizontalLayout();
                hl.add(new Span(tournament.getName()));
                hl.add(new Anchor("https://app.matchplay.events/tournaments/"+tournament.getUrlLabel(), "(MatchPlay)"));
                hl.add(new RouterLink("(Standings)", StandingsView.class, getUrlLabelOrId(tournament)));

                String lastOrCurrent = (tournament.getStatus().equalsIgnoreCase("completed") ? "(last)" : "(current)");
                hl.add(new RouterLink(lastOrCurrent, MatchView.class, getUrlLabelOrId(tournament)));
                verticalLayout.add(hl);


                Result[] results = matchPlayService.getResults(tourney);
                HorizontalLayout rounds = new HorizontalLayout();
                rounds.setWidthFull();
                rounds.setJustifyContentMode(JustifyContentMode.END);
                rounds.add(new Span("Round"));
                for (int i=0; i<results.length; i++){
                    int round_no = i+1;
                    rounds.add(new RouterLink("("+ round_no +")", MatchView.class, getUrlLabelOrId(tournament)+"/"+round_no));
                }
                verticalLayout.add(rounds);
            }
            vas.addRow(verticalLayout);
        }
    }

    /**
     * Work around a bug in MatchPlay API where UrlLabel does not work in case it is numeric-only.
     *
     * @param tournament the tournament to retrieve the id or label from
     * @return the id or label, as accepted by the MatchPlay API.
     */
    private static String getUrlLabelOrId(Tournament tournament){
        try {
            return Integer.toString(tournament.getTournamentId());
        }
        catch (NumberFormatException ignored){
            return tournament.getUrlLabel();
        }
    }
}

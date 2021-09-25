package com.infraleap.pinball.views.chooser;

import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.config.Configuration;
import com.infraleap.pinball.data.config.TournamentSet;
import com.infraleap.pinball.data.matchplay.Tournament;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.ConfigurationService;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.util.List;

@PageTitle("Tournament Selector")
@Route(value = "tournament-select", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TournamentSelectView extends VerticalLayout {



    private final MatchPlayService matchPlayService;

    public TournamentSelectView(ConfigurationService configurationService, MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassName("tournament-select-view");

        Configuration config = configurationService.getConfiguration();
        List<TournamentSet> tourneySets = config.getTournamentSets();

        VerticalAutoScroller vas = new VerticalAutoScroller();
        add(vas);

        for (TournamentSet tourneySet : tourneySets){
            VerticalLayout vl = new VerticalLayout();
            vl.addClassName("bordered");
            vl.add(new H1(tourneySet.getIfpaName()));
            for (String tourney : tourneySet.getIds()){
                Tournament tournament = matchPlayService.getTournament(tourney);
                vl.add(new Span(tournament.getName()));
            }
            vas.add(vl);
        }
    }
}

package com.infraleap.pinball.views.tournament;

import com.infraleap.animatecss.Animated;
import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.matchplay.Standing;
import com.infraleap.pinball.data.matchplay.Tournament;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
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
import com.vaadin.flow.shared.Registration;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.Comparator;

@PageTitle("Standings")
@Route(value = "standings", layout = MainLayout.class)
public class StandingsView extends VerticalLayout implements HasUrlParameter<String> {

    private final static Logger log = Logger.getLogger(StandingsView.class);

    private final MatchPlayService matchPlayService;

    private Tournament tournament;
    private Standing[] standings;

    public StandingsView(MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassNames("standings-view");
        addClassName("items-center");

        this.setSpacing(false);
        this.setPadding(false);
        this.setMargin(false);
    }

    private void update(){

        removeAll();

        VerticalAutoScroller vas = new VerticalAutoScroller();
        vas.setWidthFull();
        add(vas);

        boolean completed = false;

        if (tournament != null) {
            completed = tournament.getStatus().equals("completed");

            VerticalLayout vl = new VerticalLayout();
            HorizontalLayout hl = new HorizontalLayout(new H1(completed? "Final Standings:":"Current Standings:"));
            hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
            hl.setWidthFull();
            vl.add(new H4(tournament.getName()), hl);
            vl.addClassName("bordered-gold");
            vas.addRow(vl);

            Animated.animate(vl, Animated.Animation.BOUNCE_IN_LEFT);
        }

        if (standings != null){
            Arrays.sort(standings, Comparator.comparingInt(Standing::getPosition));

            for (Standing standing : this.standings){
                VerticalLayout verticalLayout = new VerticalLayout();
                verticalLayout.addClassName(completed? "bordered-green":"bordered-silver");

                HorizontalLayout standingLayout = new HorizontalLayout();
                standingLayout.setWidthFull();
                standingLayout.add(new H3(standing.getPosition().toString()+": "+standing.getName()));
                FlexLayout wrapper = new FlexLayout(new H4("Points: "+standing.getPoints()));
                wrapper.setJustifyContentMode(JustifyContentMode.END);
                standingLayout.add(wrapper);
                standingLayout.expand(wrapper);

                standingLayout.setAlignItems(FlexComponent.Alignment.BASELINE);


                verticalLayout.add(standingLayout);
                vas.addRow(verticalLayout);

                Animated.animate(verticalLayout, Animated.Animation.BOUNCE_IN_RIGHT);
            }

        }

        Registration[] reg = new Registration[1];
        reg[0] = ComponentUtil.addListener(vas, VerticalAutoScroller.LoopEvent.class, e -> {
            log.info("RESTART SCROLL "+System.identityHashCode(StandingsView.this));
            UI.getCurrent().accessSynchronously( () -> {
                reg[0].remove();
                UI.getCurrent().getPage().reload();
            } );
        });
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        tournament = matchPlayService.getTournament(s);
        standings = matchPlayService.getStandings(s);

        update();
    }
}

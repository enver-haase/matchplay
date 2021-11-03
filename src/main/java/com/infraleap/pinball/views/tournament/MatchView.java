package com.infraleap.pinball.views.tournament;

import com.infraleap.animatecss.Animated;
import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.matchplay.*;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.infraleap.pinball.views.chooser.TournamentSelectView;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.shared.Registration;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

@PageTitle("Match")
@Route(value = "match", layout = MainLayout.class)
public class MatchView extends VerticalLayout implements HasUrlParameter<String>, BeforeEnterObserver {

    private final static Logger log = Logger.getLogger(MatchView.class);

    private final MatchPlayService matchPlayService;

    private Tournament tournament;
    private Result[] results;
    private String round_id = null;

    public MatchView(MatchPlayService matchPlayService) {
        this.matchPlayService = matchPlayService;

        addClassNames("match-view");
        addClassName("items-center");

        this.setSpacing(false);
        this.setPadding(false);
        this.setMargin(false);
    }

    private void update(){
        removeAll();

        Result round = null;
        int roundNo = 0;
        if (results != null) {
            // TODO: sort by starting UTC time, do not trust server order

            if (this.round_id == null) {
                round = results[results.length - 1];
                roundNo = results.length;
            } else {
                for (int i=0; i<results.length; i++) {
                    Result result = results[i];
                    if (result.getRoundId().equals(Integer.parseInt(round_id))) {
                        round = result;
                        roundNo = i+1;
                        break;
                    }
                }
            }
        }


        VerticalAutoScroller vas = new VerticalAutoScroller();
        vas.setWidthFull();
        add(vas);

        if (tournament != null) {
            VerticalLayout vl = new VerticalLayout();
            HorizontalLayout tourneyNameLayout = new HorizontalLayout(new H1(tournament.getName()));
            tourneyNameLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            tourneyNameLayout.setWidthFull();
            HorizontalLayout roundLayout = new HorizontalLayout(new H2("Round "+roundNo));
            roundLayout.setJustifyContentMode(JustifyContentMode.CENTER);
            roundLayout.setWidthFull();
            vl.add(new H4("Welcome To: "), tourneyNameLayout, roundLayout);
            vl.addClassName("bordered-gold");
            vas.addRow(vl);

            Animated.animate(vl, Animated.Animation.BOUNCE_IN_LEFT);
        }


        if (round != null){
            for (Game game : round.getGames()) {
                // Game -- arena with players
                Integer arenaId = game.getArenaId();
                Arena arena = arenaId == null ? null : matchPlayService.getArenaWithId(tournament.getTournamentId(), arenaId);
                String arenaHeader = (/*"Arena: " + */(arena == null ? "(NO ARENA)" : arena.getName()));

                VerticalLayout gameLayout = new VerticalLayout();
                gameLayout.add(new H3(arenaHeader));
                VerticalLayout playersLayout = new VerticalLayout();
                playersLayout.setWidthFull();
                gameLayout.add(playersLayout);

                Result[] results = game.getResults().toArray(new Result[0]);
                //Arrays.sort(results, Comparator.comparingDouble(result -> Double.parseDouble((String) result.getAdditionalProperties().get("points"))));

                Player[] players = game.getPlayers().toArray(new Player[0]);
                Arrays.sort(players, Comparator.comparingInt(player -> (int) player.getGame().getAdditionalProperties().get("index")));

                log.info("Game status: "+game.getStatus());
                boolean running = !game.getStatus().equalsIgnoreCase("completed");
                log.info("Game is running: "+running);
                for (Player player : players) {
                    if (!running) {
                        String points = "\u00a0: "; // non-breaking space before the colon
                        for (Result result : results) {
                            if (result.getAdditionalProperties() != null) {
                                if (player.getPlayerId().equals(result.getAdditionalProperties().get("player_id"))) {
                                    points += result.getAdditionalProperties().get("points");
                                    points += "pts";
                                    break;
                                }
                            }
                        }
                        playersLayout.add(new H2(player.getName() + points));
                        gameLayout.addClassName("bordered-green");
                    }
                    else{
                        playersLayout.add(new H2(player.getName()));
                        gameLayout.addClassName("bordered-silver");
                    }
                }

                vas.add(gameLayout);
                Animated.animate(gameLayout, Animated.Animation.BOUNCE_IN_RIGHT);
            }
        }

        Registration[] reg = new Registration[1];
        reg[0] = ComponentUtil.addListener(vas, VerticalAutoScroller.LoopEvent.class, e -> {
            log.info("RESTART SCROLL "+System.identityHashCode(MatchView.this));
            UI.getCurrent().accessSynchronously( () -> {
                reg[0].remove();
                UI.getCurrent().getPage().reload();
            } );
        });

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @WildcardParameter String s) {

        StringTokenizer st = new StringTokenizer(s, "/");

        String tourney = null;

        if (st.hasMoreTokens()){
            tourney = st.nextToken();
        }

        if (tourney != null) {
            tournament = matchPlayService.getTournament(tourney);
            results = matchPlayService.getResults(tourney);
        }

        if (st.hasMoreTokens()){
            String round = st.nextToken();
            try {
                int iRound = Integer.parseInt(round);
                if (iRound < 100){
                    round_id = results[iRound-1].getRoundId().toString();
                }
            }
            catch (NumberFormatException | ArrayIndexOutOfBoundsException ignored){
                round_id = round;
            }
        }

        update();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (tournament == null){ // empty parameter string?
            beforeEnterEvent.forwardTo(TournamentSelectView.class);
        }
    }
}

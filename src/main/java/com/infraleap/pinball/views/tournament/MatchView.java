package com.infraleap.pinball.views.tournament;

import com.infraleap.pinball.components.VerticalAutoScroller;
import com.infraleap.pinball.data.matchplay.*;
import com.infraleap.pinball.layout.MainLayout;
import com.infraleap.pinball.service.MatchPlayService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

@PageTitle("Standings")
@Route(value = "match", layout = MainLayout.class)
public class MatchView extends VerticalLayout implements HasUrlParameter<String> {

    private final MatchPlayService matchPlayService;

    private Tournament tournament;
    private Standing[] standings;
    private Result[] results;
    private String round_id = null;

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

        VerticalAutoScroller vas = new VerticalAutoScroller();
        add(vas);


        if (results != null){
            // TODO: sort by starting UTC time, do not trust server order
            Result round = null;
            if (this.round_id == null){
                round = results[results.length-1];
            }
            else{
                for (Result result : results){
                    if (result.getRoundId().equals(Integer.parseInt(round_id))){
                        round = result;
                        break;
                    }
                }
            }


            if (round != null){
                for (Game game : round.getGames()) {
                    // Game -- arena with players
                    Integer arenaId = game.getArenaId();
                    Arena arena = arenaId == null ? null : matchPlayService.getArenaWithId(tournament.getTournamentId(), arenaId);
                    String arenaHeader = ("Arena: " + (arena == null ? "(none)" : arena.getName()));

                    VerticalLayout gameLayout = new VerticalLayout();
                    gameLayout.addClassName("bordered");
                    gameLayout.add(new H3(arenaHeader));
                    VerticalLayout playersLayout = new VerticalLayout();
                    playersLayout.setWidthFull();
                    gameLayout.add(playersLayout);

                    Result[] results = game.getResults().toArray(new Result[0]);
                    //Arrays.sort(results, Comparator.comparingDouble(result -> Double.parseDouble((String) result.getAdditionalProperties().get("points"))));

                    Player[] players = game.getPlayers().toArray(new Player[0]);
                    Arrays.sort(players, Comparator.comparingInt(player -> (int) player.getGame().getAdditionalProperties().get("index")));

                    for (Player player : players) {
                        String points = " : ";
                        for (Result result : results){
                            if (result.getAdditionalProperties() != null) {
                                if (player.getPlayerId().equals(result.getAdditionalProperties().get("player_id"))) {
                                    points += result.getAdditionalProperties().get("points");
                                    points += "pts";
                                    break;
                                }
                            }
                        }

                        playersLayout.add(new H2(player.getName()+points));
                    }

                    vas.add(gameLayout);
                }
            }










        }

    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @WildcardParameter String s) {

        StringTokenizer st = new StringTokenizer(s, "/");

        String tourney = null;

        if (st.hasMoreTokens()){
            tourney = st.nextToken();
        }
        if (st.hasMoreTokens()){
            round_id = st.nextToken();
        }

        if (tourney != null) {
            tournament = matchPlayService.getTournament(tourney);
            standings = matchPlayService.getStandings(tourney);
            results = matchPlayService.getResults(tourney);
        }

        update();
    }
}

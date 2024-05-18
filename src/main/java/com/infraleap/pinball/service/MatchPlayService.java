package com.infraleap.pinball.service;

import com.infraleap.pinball.data.matchplay.Arena;
import com.infraleap.pinball.data.matchplay.Result;
import com.infraleap.pinball.data.matchplay.Standing;
import com.infraleap.pinball.data.matchplay.Tournament;
import org.jboss.logging.Logger;
import org.openapitools.client.model.GetTournament200Response;
import org.openapitools.client.model.GetTournamentList200Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.openapitools.client.api.TournamentsApi;

@Service
public class MatchPlayService {

    @Value("${bearer.token}")
    private String bearerToken;
    private final Logger log = Logger.getLogger(MatchPlayService.class);



    public MatchPlayService() {



        // TODO: This is a test to see if the API works. Remove this later.
        TournamentsApi api = new TournamentsApi();
        api.getApiClient().setBearerToken(bearerToken);
        try {
            GetTournamentList200Response response = api.getTournamentList(null, null, null, null, null);
            log.info("Got response: "+response);

            GetTournament200Response tournament = api.getTournament(21072, 0, null, null, null, null, null, null, null, null, null, null);
            log.info("Got tournament: "+tournament);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Arena getArenaWithId(int tournament_id, int id){
        log.info("Getting Arena with id "+id+", tournament id is "+tournament_id);
        List<Arena> arenaList = getTournament(Integer.toString(tournament_id)).getArenas();
        Optional<Arena> arenaOptional = arenaList.stream().filter(arena -> arena.getArenaId() == id ).findFirst();
        return arenaOptional.orElse(null);
    }

    public synchronized Result[] getResults(String tournament_id) {
        return new Result[0];
    }

    public synchronized Standing[] getStandings(String tournament_id) {
        return new Standing[0];
    }

    public synchronized Tournament getTournament(String tournament_id) {
        return new Tournament();
    }

    private static URI getBaseURI() {
        return URI.create("https://matchplay.events/api/v1");
    }
}

package com.infraleap.pinball.service;

import com.infraleap.pinball.data.matchplay.Arena;
import com.infraleap.pinball.data.matchplay.Result;
import com.infraleap.pinball.data.matchplay.Standing;
import com.infraleap.pinball.data.matchplay.Tournament;
import org.jboss.logging.Logger;
import org.openapitools.client.api.ArenasApi;
import org.openapitools.client.model.GetArenas401Response;
import org.openapitools.client.model.GetTournament200Response;
import org.openapitools.client.model.GetTournamentList200Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openapitools.client.api.TournamentsApi;

@Service
public class MatchPlayService {

    @Value("${bearer.token}")
    private String bearerToken;
    private final Logger log = Logger.getLogger(MatchPlayService.class);

    private final TournamentsApi tournamentsApi;
    private final ArenasApi arenasApi;

    public MatchPlayService() {
        tournamentsApi = new TournamentsApi();
        tournamentsApi.getApiClient().setBearerToken(bearerToken);

        arenasApi = new ArenasApi();
        arenasApi.getApiClient().setBearerToken(bearerToken);

        // TODO: This is a test to see if the API works. Remove this later.
        try {
            GetTournamentList200Response response = tournamentsApi.getTournamentList(374, 19113, null, null, null);
            log.info("Got response: "+response);
            ArrayList arrayList = (ArrayList) response.getData();
            log.info("Got "+arrayList.getClass().getCanonicalName()+" class");
            log.info("Got "+arrayList.size()+" tournaments");
            log.info("Got "+arrayList.get(0).getClass().getCanonicalName()+" class");


            GetTournament200Response tournament = tournamentsApi.getTournament(21072, 0, null, null, null, null, null, null, null, null, null, null);
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

    // TODO: there is not even a 200 yet. This is a placeholder. Match Play API v2 is broken until further notice.
    public synchronized GetArenas401Response getArenas(String tournament_id) {
        return new GetArenas401Response();
    }
}

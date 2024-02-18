package com.infraleap.pinball.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infraleap.pinball.data.matchplay.Arena;
import com.infraleap.pinball.data.matchplay.Result;
import com.infraleap.pinball.data.matchplay.Standing;
import com.infraleap.pinball.data.matchplay.Tournament;
import org.jboss.logging.Logger;
import org.openapitools.client.model.GetTournamentList200Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.openapitools.client.api.TournamentsApi;

@Service
public class MatchPlayService {

    @Value("${bearer.token}")
    private String bearerToken;
    private final Logger log = Logger.getLogger(MatchPlayService.class);

    private final WebTarget target;

    public MatchPlayService() {
        Client client = ClientBuilder.newClient();
        target = client.target(getBaseURI());


        // TODO: This is a test to see if the API works. Remove this later.
        TournamentsApi api = new TournamentsApi();
        api.getApiClient().setBearerToken(bearerToken);
        try {
            GetTournamentList200Response response = api.getTournamentList(null, null, null, null, null);
            log.info("Got response: "+response);
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
        // Get JSON for application
        String jsonResponse = target.path("tournaments").path(tournament_id).path("results").request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonResponse, Result[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Result[0];
    }

    public synchronized Standing[] getStandings(String tournament_id) {
        // Get JSON for application
        String jsonResponse = target.path("tournaments").path(tournament_id).path("standings").request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonResponse, Standing[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new Standing[0];
    }

    public synchronized Tournament getTournament(String tournament_id) {
        try {
            // Get JSON for application
            String jsonResponse = target.path("tournaments").path(tournament_id).request()
                    .accept(MediaType.APPLICATION_JSON).get(String.class);

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonResponse, Tournament.class);
        } catch (JsonProcessingException | NotFoundException e) {
            e.printStackTrace();
        }
        return new Tournament();
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri(
                "https://matchplay.events/data").build();
    }
}

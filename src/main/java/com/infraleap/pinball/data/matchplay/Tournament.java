package com.infraleap.pinball.data.matchplay;

import org.openapitools.client.model.GetTournament200Response;

import java.util.List;

public class Tournament extends GetTournament200Response {
    public String getName() {
        return this.getData().getName();
    }

    public String getUrlLabel() {
        return this.getData().getTournamentId().toString();
    }

    public String getStatus() {
        return this.getData().getStatus();
    }

    public List<Arena> getArenas() {
        return null; // TODO: use ArenasApi?  return this.getData().getArenas();
    }

    public int getTournamentId() {
        return this.getData().getTournamentId();
    }
}

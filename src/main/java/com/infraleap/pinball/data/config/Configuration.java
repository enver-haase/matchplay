
package com.infraleap.pinball.data.config;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tournament_sets"
})
@Generated("jsonschema2pojo")
public class Configuration {

    @JsonProperty("tournament_sets")
    private List<TournamentSet> tournamentSets = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tournament_sets")
    public List<TournamentSet> getTournamentSets() {
        return tournamentSets;
    }

    @JsonProperty("tournament_sets")
    public void setTournamentSets(List<TournamentSet> tournamentSets) {
        this.tournamentSets = tournamentSets;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}


package com.infraleap.pinball.data.matchplay;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "arena_id",
    "name",
    "status",
    "opdb_id",
    "tournament"
})
@Generated("jsonschema2pojo")
public class Arena {

    @JsonProperty("arena_id")
    private Integer arenaId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("opdb_id")
    private String opdbId;
    @JsonProperty("tournament")
    private Tournament tournament;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("arena_id")
    public Integer getArenaId() {
        return arenaId;
    }

    @JsonProperty("arena_id")
    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("opdb_id")
    public String getOpdbId() {
        return opdbId;
    }

    @JsonProperty("opdb_id")
    public void setOpdbId(String opdbId) {
        this.opdbId = opdbId;
    }

    @JsonProperty("tournament")
    public Tournament getTournament() {
        return tournament;
    }

    @JsonProperty("tournament")
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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

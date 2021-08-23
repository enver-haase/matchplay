
package com.infraleap.pinball.data;

import java.util.HashMap;
import java.util.List;
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
    "game_id",
    "arena_id",
    "bank_id",
    "status",
    "players",
    "results"
})
@Generated("jsonschema2pojo")
public class Game {

    @JsonProperty("game_id")
    private Integer gameId;
    @JsonProperty("arena_id")
    private Integer arenaId;
    @JsonProperty("bank_id")
    private Object bankId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("players")
    private List<Player> players = null;
    @JsonProperty("results")
    private List<Result> results = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("game_id")
    public Integer getGameId() {
        return gameId;
    }

    @JsonProperty("game_id")
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @JsonProperty("arena_id")
    public Integer getArenaId() {
        return arenaId;
    }

    @JsonProperty("arena_id")
    public void setArenaId(Integer arenaId) {
        this.arenaId = arenaId;
    }

    @JsonProperty("bank_id")
    public Object getBankId() {
        return bankId;
    }

    @JsonProperty("bank_id")
    public void setBankId(Object bankId) {
        this.bankId = bankId;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("players")
    public List<Player> getPlayers() {
        return players;
    }

    @JsonProperty("players")
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @JsonProperty("results")
    public List<Result> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Result> results) {
        this.results = results;
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

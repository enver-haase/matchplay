
package com.infraleap.pinball.data.matchplay;

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
    "tournament_id",
    "name",
    "type",
    "status",
    "url_label",
    "start",
    "points_map",
    "test",
    "linked_tournament_id",
    "games_per_round",
    "players",
    "arenas"
})
@Generated("jsonschema2pojo")
public class Tournament {

    @JsonProperty("tournament_id")
    private Integer tournamentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private String status;
    @JsonProperty("url_label")
    private String urlLabel;
    @JsonProperty("start")
    private String start;
    @JsonProperty("points_map")
    private Object pointsMap;
    @JsonProperty("test")
    private Integer test;
    @JsonProperty("linked_tournament_id")
    private Object linkedTournamentId;
    @JsonProperty("games_per_round")
    private Integer gamesPerRound;
    @JsonProperty("players")
    private List<Player> players = null;
    @JsonProperty("arenas")
    private List<Arena> arenas = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("tournament_id")
    public Integer getTournamentId() {
        return tournamentId;
    }

    @JsonProperty("tournament_id")
    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("url_label")
    public String getUrlLabel() {
        return urlLabel;
    }

    @JsonProperty("url_label")
    public void setUrlLabel(String urlLabel) {
        this.urlLabel = urlLabel;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    @JsonProperty("start")
    public void setStart(String start) {
        this.start = start;
    }

    @JsonProperty("points_map")
    public Object getPointsMap() {
        return pointsMap;
    }

    @JsonProperty("points_map")
    public void setPointsMap(Object pointsMap) {
        this.pointsMap = pointsMap;
    }

    @JsonProperty("test")
    public Integer getTest() {
        return test;
    }

    @JsonProperty("test")
    public void setTest(Integer test) {
        this.test = test;
    }

    @JsonProperty("linked_tournament_id")
    public Object getLinkedTournamentId() {
        return linkedTournamentId;
    }

    @JsonProperty("linked_tournament_id")
    public void setLinkedTournamentId(Object linkedTournamentId) {
        this.linkedTournamentId = linkedTournamentId;
    }

    @JsonProperty("games_per_round")
    public Integer getGamesPerRound() {
        return gamesPerRound;
    }

    @JsonProperty("games_per_round")
    public void setGamesPerRound(Integer gamesPerRound) {
        this.gamesPerRound = gamesPerRound;
    }

    @JsonProperty("players")
    public List<Player> getPlayers() {
        return players;
    }

    @JsonProperty("players")
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @JsonProperty("arenas")
    public List<Arena> getArenas() {
        return arenas;
    }

    @JsonProperty("arenas")
    public void setArenas(List<Arena> arenas) {
        this.arenas = arenas;
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


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
    "player_id",
    "position",
    "byes",
    "points",
    "custom_count",
    "comparator",
    "points_list",
    "losses",
    "games",
    "name",
    "claimed_by"
})
@Generated("jsonschema2pojo")
public class Standing {

    @JsonProperty("player_id")
    private Integer playerId;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("byes")
    private String byes;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("custom_count")
    private Object customCount;
    @JsonProperty("comparator")
    private String comparator;
    @JsonProperty("points_list")
    private Object pointsList;
    @JsonProperty("losses")
    private Object losses;
    @JsonProperty("games")
    private Object games;
    @JsonProperty("name")
    private String name;
    @JsonProperty("claimed_by")
    private Integer claimedBy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("player_id")
    public Integer getPlayerId() {
        return playerId;
    }

    @JsonProperty("player_id")
    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    @JsonProperty("position")
    public Integer getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(Integer position) {
        this.position = position;
    }

    @JsonProperty("byes")
    public String getByes() {
        return byes;
    }

    @JsonProperty("byes")
    public void setByes(String byes) {
        this.byes = byes;
    }

    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    @JsonProperty("custom_count")
    public Object getCustomCount() {
        return customCount;
    }

    @JsonProperty("custom_count")
    public void setCustomCount(Object customCount) {
        this.customCount = customCount;
    }

    @JsonProperty("comparator")
    public String getComparator() {
        return comparator;
    }

    @JsonProperty("comparator")
    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    @JsonProperty("points_list")
    public Object getPointsList() {
        return pointsList;
    }

    @JsonProperty("points_list")
    public void setPointsList(Object pointsList) {
        this.pointsList = pointsList;
    }

    @JsonProperty("losses")
    public Object getLosses() {
        return losses;
    }

    @JsonProperty("losses")
    public void setLosses(Object losses) {
        this.losses = losses;
    }

    @JsonProperty("games")
    public Object getGames() {
        return games;
    }

    @JsonProperty("games")
    public void setGames(Object games) {
        this.games = games;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("claimed_by")
    public Integer getClaimedBy() {
        return claimedBy;
    }

    @JsonProperty("claimed_by")
    public void setClaimedBy(Integer claimedBy) {
        this.claimedBy = claimedBy;
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

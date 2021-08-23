
package com.infraleap.pinball.data;

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
    "user_id",
    "claimed_by",
    "ifpa_id",
    "name",
    "status",
    "game"
})
@Generated("jsonschema2pojo")
public class Player {

    @JsonProperty("player_id")
    private Integer playerId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("claimed_by")
    private Integer claimedBy;
    @JsonProperty("ifpa_id")
    private Integer ifpaId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("game")
    private Game game;
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

    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("claimed_by")
    public Integer getClaimedBy() {
        return claimedBy;
    }

    @JsonProperty("claimed_by")
    public void setClaimedBy(Integer claimedBy) {
        this.claimedBy = claimedBy;
    }

    @JsonProperty("ifpa_id")
    public Integer getIfpaId() {
        return ifpaId;
    }

    @JsonProperty("ifpa_id")
    public void setIfpaId(Integer ifpaId) {
        this.ifpaId = ifpaId;
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

    @JsonProperty("game")
    public Game getGame() {
        return game;
    }

    @JsonProperty("game")
    public void setGame(Game game) {
        this.game = game;
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

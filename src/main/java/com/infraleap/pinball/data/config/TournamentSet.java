
package com.infraleap.pinball.data.config;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ifpaName",
    "ids"
})
@Generated("jsonschema2pojo")
public class TournamentSet {

    @JsonProperty("ifpaName")
    private String ifpaName;
    @JsonProperty("ids")
    private List<String> ids = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ifpaName")
    public String getIfpaName() {
        return ifpaName;
    }

    @JsonProperty("ifpaName")
    public void setIfpaName(String ifpaName) {
        this.ifpaName = ifpaName;
    }

    @JsonProperty("ids")
    public List<String> getIds() {
        return ids;
    }

    @JsonProperty("ids")
    public void setIds(List<String> ids) {
        this.ids = ids;
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

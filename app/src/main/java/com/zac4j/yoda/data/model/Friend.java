package com.zac4j.yoda.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model for User Friends
 * Created by zac on 4/13/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "users", "next_cursor", "previous_cursor", "total_number"
}) public class Friend {

    @JsonProperty("users")
    private List<User> users = null;
    @JsonProperty("next_cursor")
    private Integer nextCursor;
    @JsonProperty("previous_cursor")
    private Integer previousCursor;
    @JsonProperty("total_number")
    private Integer totalNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonProperty("next_cursor")
    public Integer getNextCursor() {
        return nextCursor;
    }

    @JsonProperty("next_cursor")
    public void setNextCursor(Integer nextCursor) {
        this.nextCursor = nextCursor;
    }

    @JsonProperty("previous_cursor")
    public Integer getPreviousCursor() {
        return previousCursor;
    }

    @JsonProperty("previous_cursor")
    public void setPreviousCursor(Integer previousCursor) {
        this.previousCursor = previousCursor;
    }

    @JsonProperty("total_number")
    public Integer getTotalNumber() {
        return totalNumber;
    }

    @JsonProperty("total_number")
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
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

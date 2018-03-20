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

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "comments", "hasvisible", "previous_cursor", "next_cursor", "total_number", "interval"
}) public class Notification {

    @JsonProperty("comments")
    private List<Comment> comments = null;
    @JsonProperty("hasvisible")
    private Boolean hasvisible;
    @JsonProperty("previous_cursor")
    private Long previousCursor;
    @JsonProperty("next_cursor")
    private Long nextCursor;
    @JsonProperty("total_number")
    private Long totalNumber;
    @JsonProperty("interval")
    private Long interval;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("hasvisible")
    public Boolean getHasvisible() {
        return hasvisible;
    }

    @JsonProperty("hasvisible")
    public void setHasvisible(Boolean hasvisible) {
        this.hasvisible = hasvisible;
    }

    @JsonProperty("previous_cursor")
    public Long getPreviousCursor() {
        return previousCursor;
    }

    @JsonProperty("previous_cursor")
    public void setPreviousCursor(Long previousCursor) {
        this.previousCursor = previousCursor;
    }

    @JsonProperty("next_cursor")
    public Long getNextCursor() {
        return nextCursor;
    }

    @JsonProperty("next_cursor")
    public void setNextCursor(Long nextCursor) {
        this.nextCursor = nextCursor;
    }

    @JsonProperty("total_number")
    public Long getTotalNumber() {
        return totalNumber;
    }

    @JsonProperty("total_number")
    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    @JsonProperty("interval")
    public Long getInterval() {
        return interval;
    }

    @JsonProperty("interval")
    public void setInterval(Long interval) {
        this.interval = interval;
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

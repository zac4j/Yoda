package com.zac4j.yoda.data.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Weibo Timeline Model
 * Created by zac on 3/21/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "statuses", "advertises", "ad", "hasvisible", "previous_cursor", "next_cursor", "total_number",
    "interval", "uve_blank", "since_id", "max_id", "has_unread"
})

public class Timeline {

  @JsonProperty("statuses") private List<Weibo> statuses = null;
  @JsonProperty("advertises") private List<Object> advertises = null;
  @JsonProperty("ad") private List<Object> ad = null;
  @JsonProperty("hasvisible") private Boolean hasvisible;
  @JsonProperty("previous_cursor") private Integer previousCursor;
  @JsonProperty("next_cursor") private Integer nextCursor;
  @JsonProperty("total_number") private Integer totalNumber;
  @JsonProperty("interval") private Integer interval;
  @JsonProperty("uve_blank") private Integer uveBlank;
  @JsonProperty("since_id") private Integer sinceId;
  @JsonProperty("max_id") private Integer maxId;
  @JsonProperty("has_unread") private Integer hasUnread;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty("statuses") public List<Weibo> getStatuses() {
    return statuses;
  }

  @JsonProperty("statuses") public void setStatuses(List<Weibo> statuses) {
    this.statuses = statuses;
  }

  @JsonProperty("advertises") public List<Object> getAdvertises() {
    return advertises;
  }

  @JsonProperty("advertises") public void setAdvertises(List<Object> advertises) {
    this.advertises = advertises;
  }

  @JsonProperty("ad") public List<Object> getAd() {
    return ad;
  }

  @JsonProperty("ad") public void setAd(List<Object> ad) {
    this.ad = ad;
  }

  @JsonProperty("hasvisible") public Boolean getHasvisible() {
    return hasvisible;
  }

  @JsonProperty("hasvisible") public void setHasvisible(Boolean hasvisible) {
    this.hasvisible = hasvisible;
  }

  @JsonProperty("previous_cursor") public Integer getPreviousCursor() {
    return previousCursor;
  }

  @JsonProperty("previous_cursor") public void setPreviousCursor(Integer previousCursor) {
    this.previousCursor = previousCursor;
  }

  @JsonProperty("next_cursor") public Integer getNextCursor() {
    return nextCursor;
  }

  @JsonProperty("next_cursor") public void setNextCursor(Integer nextCursor) {
    this.nextCursor = nextCursor;
  }

  @JsonProperty("total_number") public Integer getTotalNumber() {
    return totalNumber;
  }

  @JsonProperty("total_number") public void setTotalNumber(Integer totalNumber) {
    this.totalNumber = totalNumber;
  }

  @JsonProperty("interval") public Integer getInterval() {
    return interval;
  }

  @JsonProperty("interval") public void setInterval(Integer interval) {
    this.interval = interval;
  }

  @JsonProperty("uve_blank") public Integer getUveBlank() {
    return uveBlank;
  }

  @JsonProperty("uve_blank") public void setUveBlank(Integer uveBlank) {
    this.uveBlank = uveBlank;
  }

  @JsonProperty("since_id") public Integer getSinceId() {
    return sinceId;
  }

  @JsonProperty("since_id") public void setSinceId(Integer sinceId) {
    this.sinceId = sinceId;
  }

  @JsonProperty("max_id") public Integer getMaxId() {
    return maxId;
  }

  @JsonProperty("max_id") public void setMaxId(Integer maxId) {
    this.maxId = maxId;
  }

  @JsonProperty("has_unread") public Integer getHasUnread() {
    return hasUnread;
  }

  @JsonProperty("has_unread") public void setHasUnread(Integer hasUnread) {
    this.hasUnread = hasUnread;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}

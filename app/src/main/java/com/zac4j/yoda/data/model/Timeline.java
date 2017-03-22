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
  @JsonProperty("previous_cursor") private Long previousCursor;
  @JsonProperty("next_cursor") private Long nextCursor;
  @JsonProperty("total_number") private Long totalNumber;
  @JsonProperty("interval") private Long interval;
  @JsonProperty("uve_blank") private Long uveBlank;
  @JsonProperty("since_id") private Long sinceId;
  @JsonProperty("max_id") private Long maxId;
  @JsonProperty("has_unread") private Long hasUnread;
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

  @JsonProperty("previous_cursor") public Long getPreviousCursor() {
    return previousCursor;
  }

  @JsonProperty("previous_cursor") public void setPreviousCursor(Long previousCursor) {
    this.previousCursor = previousCursor;
  }

  @JsonProperty("next_cursor") public Long getNextCursor() {
    return nextCursor;
  }

  @JsonProperty("next_cursor") public void setNextCursor(Long nextCursor) {
    this.nextCursor = nextCursor;
  }

  @JsonProperty("total_number") public Long getTotalNumber() {
    return totalNumber;
  }

  @JsonProperty("total_number") public void setTotalNumber(Long totalNumber) {
    this.totalNumber = totalNumber;
  }

  @JsonProperty("interval") public Long getInterval() {
    return interval;
  }

  @JsonProperty("interval") public void setInterval(Long interval) {
    this.interval = interval;
  }

  @JsonProperty("uve_blank") public Long getUveBlank() {
    return uveBlank;
  }

  @JsonProperty("uve_blank") public void setUveBlank(Long uveBlank) {
    this.uveBlank = uveBlank;
  }

  @JsonProperty("since_id") public Long getSinceId() {
    return sinceId;
  }

  @JsonProperty("since_id") public void setSinceId(Long sinceId) {
    this.sinceId = sinceId;
  }

  @JsonProperty("max_id") public Long getMaxId() {
    return maxId;
  }

  @JsonProperty("max_id") public void setMaxId(Long maxId) {
    this.maxId = maxId;
  }

  @JsonProperty("has_unread") public Long getHasUnread() {
    return hasUnread;
  }

  @JsonProperty("has_unread") public void setHasUnread(Long hasUnread) {
    this.hasUnread = hasUnread;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override public String toString() {
    return "Timeline{"
        + "statuses="
        + statuses
        + ", advertises="
        + advertises
        + ", ad="
        + ad
        + ", hasvisible="
        + hasvisible
        + ", previousCursor="
        + previousCursor
        + ", nextCursor="
        + nextCursor
        + ", totalNumber="
        + totalNumber
        + ", interval="
        + interval
        + ", uveBlank="
        + uveBlank
        + ", sinceId="
        + sinceId
        + ", maxId="
        + maxId
        + ", hasUnread="
        + hasUnread
        + ", additionalProperties="
        + additionalProperties
        + '}';
  }
}

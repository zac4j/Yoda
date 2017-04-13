package com.zac4j.yoda.data.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Model for User Action Log
 * Created by zac on 4/13/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "follow_action", "card_action"
}) public class ActionLog {

  @JsonProperty("follow_action") private Action followAction;
  @JsonProperty("card_action") private Action cardAction;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty("follow_action") public Action getFollowAction() {
    return followAction;
  }

  @JsonProperty("follow_action") public void setFollowAction(Action followAction) {
    this.followAction = followAction;
  }

  @JsonProperty("card_action") public Action getCardAction() {
    return cardAction;
  }

  @JsonProperty("card_action") public void setCardAction(Action cardAction) {
    this.cardAction = cardAction;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}

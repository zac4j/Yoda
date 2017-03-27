package com.zac4j.yoda.data.model.post;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Text Weibo
 * Created by zac on 3/27/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "access_token", "status", "visible", "list_id", "lat", "long", "annotations", "rip"
}) public class TextWeibo {

  @JsonProperty("access_token") private String accessToken;
  @JsonProperty("status") private String status;
  @JsonProperty("visible") private Integer visible;
  @JsonProperty("list_id") private String listId;
  @JsonProperty("lat") private Double lat;
  @JsonProperty("long") private Double _long;
  @JsonProperty("annotations") private String annotations;
  @JsonProperty("rip") private String rip;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("access_token") public String getAccessToken() {
    return accessToken;
  }

  @JsonProperty("access_token") public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @JsonProperty("status") public String getStatus() {
    return status;
  }

  @JsonProperty("status") public void setStatus(String status) {
    this.status = status;
  }

  @JsonProperty("visible") public Integer getVisible() {
    return visible;
  }

  @JsonProperty("visible") public void setVisible(Integer visible) {
    this.visible = visible;
  }

  @JsonProperty("list_id") public String getListId() {
    return listId;
  }

  @JsonProperty("list_id") public void setListId(String listId) {
    this.listId = listId;
  }

  @JsonProperty("lat") public Double getLat() {
    return lat;
  }

  @JsonProperty("lat") public void setLat(Double lat) {
    this.lat = lat;
  }

  @JsonProperty("long") public Double getLong() {
    return _long;
  }

  @JsonProperty("long") public void setLong(Double _long) {
    this._long = _long;
  }

  @JsonProperty("annotations") public String getAnnotations() {
    return annotations;
  }

  @JsonProperty("annotations") public void setAnnotations(String annotations) {
    this.annotations = annotations;
  }

  @JsonProperty("rip") public String getRip() {
    return rip;
  }

  @JsonProperty("rip") public void setRip(String rip) {
    this.rip = rip;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}

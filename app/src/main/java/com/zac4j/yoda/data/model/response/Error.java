package com.zac4j.yoda.data.model.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Server Error Response.
 * Created by Zac on 2017/3/27.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "error", "error_code", "request"
}) public class Error {
  @JsonProperty("error") private String error;
  @JsonProperty("error_code") private Integer errorCode;
  @JsonProperty("request") private String request;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty("error") public String getError() {
    return error;
  }

  @JsonProperty("error") public void setError(String error) {
    this.error = error;
  }

  @JsonProperty("error_code") public Integer getErrorCode() {
    return errorCode;
  }

  @JsonProperty("error_code") public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  @JsonProperty("request") public String getRequest() {
    return request;
  }

  @JsonProperty("request") public void setRequest(String request) {
    this.request = request;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}

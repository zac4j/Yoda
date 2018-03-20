package com.zac4j.yoda.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "created_at", "id", "mid", "idstr", "text", "textLength", "source_allowclick", "source_type",
    "source", "favorited", "truncated", "in_reply_to_status_id", "in_reply_to_user_id",
    "in_reply_to_screen_name"
}) public class Fuck {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("mid")
    private String mid;
    @JsonProperty("idstr")
    private String idstr;
    @JsonProperty("text")
    private String text;
    @JsonProperty("textLength")
    private Integer textLength;
    @JsonProperty("source_allowclick")
    private Integer sourceAllowclick;
    @JsonProperty("source_type")
    private Integer sourceType;
    @JsonProperty("source")
    private String source;
    @JsonProperty("favorited")
    private Boolean favorited;
    @JsonProperty("truncated")
    private Boolean truncated;
    @JsonProperty("in_reply_to_status_id")
    private String inReplyToStatusId;
    @JsonProperty("in_reply_to_user_id")
    private String inReplyToUserId;
    @JsonProperty("in_reply_to_screen_name")
    private String inReplyToScreenName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("mid")
    public String getMid() {
        return mid;
    }

    @JsonProperty("mid")
    public void setMid(String mid) {
        this.mid = mid;
    }

    @JsonProperty("idstr")
    public String getIdstr() {
        return idstr;
    }

    @JsonProperty("idstr")
    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("textLength")
    public Integer getTextLength() {
        return textLength;
    }

    @JsonProperty("textLength")
    public void setTextLength(Integer textLength) {
        this.textLength = textLength;
    }

    @JsonProperty("source_allowclick")
    public Integer getSourceAllowclick() {
        return sourceAllowclick;
    }

    @JsonProperty("source_allowclick")
    public void setSourceAllowclick(Integer sourceAllowclick) {
        this.sourceAllowclick = sourceAllowclick;
    }

    @JsonProperty("source_type")
    public Integer getSourceType() {
        return sourceType;
    }

    @JsonProperty("source_type")
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("favorited")
    public Boolean getFavorited() {
        return favorited;
    }

    @JsonProperty("favorited")
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    @JsonProperty("truncated")
    public Boolean getTruncated() {
        return truncated;
    }

    @JsonProperty("truncated")
    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    @JsonProperty("in_reply_to_status_id")
    public String getInReplyToStatusId() {
        return inReplyToStatusId;
    }

    @JsonProperty("in_reply_to_status_id")
    public void setInReplyToStatusId(String inReplyToStatusId) {
        this.inReplyToStatusId = inReplyToStatusId;
    }

    @JsonProperty("in_reply_to_user_id")
    public String getInReplyToUserId() {
        return inReplyToUserId;
    }

    @JsonProperty("in_reply_to_user_id")
    public void setInReplyToUserId(String inReplyToUserId) {
        this.inReplyToUserId = inReplyToUserId;
    }

    @JsonProperty("in_reply_to_screen_name")
    public String getInReplyToScreenName() {
        return inReplyToScreenName;
    }

    @JsonProperty("in_reply_to_screen_name")
    public void setInReplyToScreenName(String inReplyToScreenName) {
        this.inReplyToScreenName = inReplyToScreenName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Fuck{"
            + "createdAt='"
            + createdAt
            + '\''
            + ", id="
            + id
            + ", mid='"
            + mid
            + '\''
            + ", idstr='"
            + idstr
            + '\''
            + ", text='"
            + text
            + '\''
            + ", textLength="
            + textLength
            + ", sourceAllowclick="
            + sourceAllowclick
            + ", sourceType="
            + sourceType
            + ", source='"
            + source
            + '\''
            + ", favorited="
            + favorited
            + ", truncated="
            + truncated
            + ", inReplyToStatusId='"
            + inReplyToStatusId
            + '\''
            + ", inReplyToUserId='"
            + inReplyToUserId
            + '\''
            + ", inReplyToScreenName='"
            + inReplyToScreenName
            + '\''
            + ", additionalProperties="
            + additionalProperties
            + '}';
    }
}

package com.zac4j.yoda.data.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at", "disable_reply", "floor_number", "id", "idstr", "mid", "reply_comment",
    "reply_original_text", "rootid", "status", "text", "user"
})
public class Comment {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("disable_reply")
    private Integer disableReply;
    @JsonProperty("floor_number")
    private Integer floorNumber;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("idstr")
    private String idstr;
    @JsonProperty("mid")
    private String mid;
    @JsonProperty("reply_comment")
    private Comment replyComment;
    @JsonProperty("reply_original_text")
    private String replyOriginalText;
    @JsonProperty("rootid")
    private Long rootid;
    @JsonProperty("status")
    private Weibo status;
    @JsonProperty("text")
    private String text;
    @JsonProperty("user")
    private User user;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("disable_reply")
    public Integer getDisableReply() {
        return disableReply;
    }

    @JsonProperty("disable_reply")
    public void setDisableReply(Integer disableReply) {
        this.disableReply = disableReply;
    }

    @JsonProperty("floor_number")
    public Integer getFloorNumber() {
        return floorNumber;
    }

    @JsonProperty("floor_number")
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("idstr")
    public String getIdstr() {
        return idstr;
    }

    @JsonProperty("idstr")
    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    @JsonProperty("mid")
    public String getMid() {
        return mid;
    }

    @JsonProperty("mid")
    public void setMid(String mid) {
        this.mid = mid;
    }

    @JsonProperty("reply_comment")
    public Comment getReplyComment() {
        return replyComment;
    }

    @JsonProperty("reply_comment")
    public void setReplyComment(Comment replyComment) {
        this.replyComment = replyComment;
    }

    @JsonProperty("reply_original_text")
    public String getReplyOriginalText() {
        return replyOriginalText;
    }

    @JsonProperty("reply_original_text")
    public void setReplyOriginalText(String replyOriginalText) {
        this.replyOriginalText = replyOriginalText;
    }

    @JsonProperty("rootid")
    public Long getRootid() {
        return rootid;
    }

    @JsonProperty("rootid")
    public void setRootid(Long rootid) {
        this.rootid = rootid;
    }

    @JsonProperty("status")
    public Weibo getWeibo() {
        return status;
    }

    @JsonProperty("status")
    public void setWeibo(Weibo status) {
        this.status = status;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
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
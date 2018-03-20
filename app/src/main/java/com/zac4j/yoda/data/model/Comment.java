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
 * Model for User Comment
 * Created by zac on 17-5-11.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "created_at", "id", "rootid", "floor_number", "text", "source_allowclick", "source_type",
    "source", "user", "mid", "idstr", "status", "like_count", "reply_count", "url_objects", "liked"
}) public class Comment {

    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("rootid")
    private Long rootid;
    @JsonProperty("floor_number")
    private Integer floorNumber;
    @JsonProperty("text")
    private String text;
    @JsonProperty("source_allowclick")
    private Integer sourceAllowclick;
    @JsonProperty("source_type")
    private Integer sourceType;
    @JsonProperty("source")
    private String source;
    @JsonProperty("user")
    private User user;
    @JsonProperty("mid")
    private String mid;
    @JsonProperty("idstr")
    private String idstr;
    @JsonProperty("status")
    private Weibo weibo;
    @JsonProperty("like_count")
    private Long likeCount;
    @JsonProperty("reply_count")
    private Long replyCount;
    @JsonProperty("url_objects")
    private List<Object> urlObjects = null;
    @JsonProperty("liked")
    private Boolean liked;
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

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("rootid")
    public Long getRootid() {
        return rootid;
    }

    @JsonProperty("rootid")
    public void setRootid(Long rootid) {
        this.rootid = rootid;
    }

    @JsonProperty("floor_number")
    public Integer getFloorNumber() {
        return floorNumber;
    }

    @JsonProperty("floor_number")
    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
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

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
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

    @JsonProperty("status")
    public Weibo getWeibo() {
        return weibo;
    }

    @JsonProperty("status")
    public void setWeibo(Weibo weibo) {
        this.weibo = weibo;
    }

    @JsonProperty("like_count")
    public Long getLikeCount() {
        return likeCount;
    }

    @JsonProperty("like_count")
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    @JsonProperty("reply_count")
    public Long getReplyCount() {
        return replyCount;
    }

    @JsonProperty("reply_count")
    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    @JsonProperty("url_objects")
    public List<Object> getUrlObjects() {
        return urlObjects;
    }

    @JsonProperty("url_objects")
    public void setUrlObjects(List<Object> urlObjects) {
        this.urlObjects = urlObjects;
    }

    @JsonProperty("liked")
    public Boolean getLiked() {
        return liked;
    }

    @JsonProperty("liked")
    public void setLiked(Boolean liked) {
        this.liked = liked;
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

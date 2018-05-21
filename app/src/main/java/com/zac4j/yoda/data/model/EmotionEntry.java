package com.zac4j.yoda.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by zac on 2018/5/21.
 * Description:Weibo Emotion
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "phrase",
    "type",
    "url",
    "hot",
    "common",
    "category",
    "icon",
    "value",
    "picid"
})
@Entity(tableName = "emotion", indices = {@Index(value = {"phrase"}, unique = true)})
public class EmotionEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @JsonProperty("phrase")
    private String phrase;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("hot")
    private Boolean hot;
    @JsonProperty("common")
    private Boolean common;
    @JsonProperty("category")
    private String category;
    @JsonProperty("icon")
    private String icon;
    @JsonProperty("value")
    private String value;
    @JsonProperty("picid")
    private String picid;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    // Constructor used by Room to create WeatherEntries
    public EmotionEntry(int id, String phrase, String type, String url, Boolean hot, Boolean common,
        String category, String icon, String value, String picid) {
        this.id = id;
        this.phrase = phrase;
        this.type = type;
        this.url = url;
        this.hot = hot;
        this.common = common;
        this.category = category;
        this.icon = icon;
        this.value = value;
        this.picid = picid;
    }

    @JsonProperty("phrase")
    public String getPhrase() {
        return phrase;
    }

    @JsonProperty("phrase")
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("hot")
    public Boolean getHot() {
        return hot;
    }

    @JsonProperty("hot")
    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    @JsonProperty("common")
    public Boolean getCommon() {
        return common;
    }

    @JsonProperty("common")
    public void setCommon(Boolean common) {
        this.common = common;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @JsonProperty("icon")
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("picid")
    public String getPicid() {
        return picid;
    }

    @JsonProperty("picid")
    public void setPicid(String picid) {
        this.picid = picid;
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

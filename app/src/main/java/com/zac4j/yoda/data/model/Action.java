package com.zac4j.yoda.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;

/**
 * Model for User Action
 * Created by zac on 4/13/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "act_code", "uicode", "luicode", "fid", "lfid", "cardid", "lcardid", "act_type", "source", "ext"
}) public class Action {

    @JsonProperty("act_code")
    private String actCode;
    @JsonProperty("uicode")
    private String uicode;
    @JsonProperty("luicode")
    private String luicode;
    @JsonProperty("fid")
    private String fid;
    @JsonProperty("lfid")
    private String lfid;
    @JsonProperty("cardid")
    private String cardid;
    @JsonProperty("lcardid")
    private String lcardid;
    @JsonProperty("act_type")
    private Integer actType;
    @JsonProperty("source")
    private String source;
    @JsonProperty("ext")
    private String ext;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("act_code")
    public String getActCode() {
        return actCode;
    }

    @JsonProperty("act_code")
    public void setActCode(String actCode) {
        this.actCode = actCode;
    }

    @JsonProperty("uicode")
    public String getUicode() {
        return uicode;
    }

    @JsonProperty("uicode")
    public void setUicode(String uicode) {
        this.uicode = uicode;
    }

    @JsonProperty("luicode")
    public String getLuicode() {
        return luicode;
    }

    @JsonProperty("luicode")
    public void setLuicode(String luicode) {
        this.luicode = luicode;
    }

    @JsonProperty("fid")
    public String getFid() {
        return fid;
    }

    @JsonProperty("fid")
    public void setFid(String fid) {
        this.fid = fid;
    }

    @JsonProperty("lfid")
    public String getLfid() {
        return lfid;
    }

    @JsonProperty("lfid")
    public void setLfid(String lfid) {
        this.lfid = lfid;
    }

    @JsonProperty("cardid")
    public String getCardid() {
        return cardid;
    }

    @JsonProperty("cardid")
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    @JsonProperty("lcardid")
    public String getLcardid() {
        return lcardid;
    }

    @JsonProperty("lcardid")
    public void setLcardid(String lcardid) {
        this.lcardid = lcardid;
    }

    @JsonProperty("act_type")
    public Integer getActType() {
        return actType;
    }

    @JsonProperty("act_type")
    public void setActType(Integer actType) {
        this.actType = actType;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("ext")
    public String getExt() {
        return ext;
    }

    @JsonProperty("ext")
    public void setExt(String ext) {
        this.ext = ext;
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

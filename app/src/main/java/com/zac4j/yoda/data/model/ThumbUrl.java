package com.zac4j.yoda.data.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Thumbnail Url Model
 * Created by zac on 4/1/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "thumbnail_pic"
}) public class ThumbUrl implements Serializable {

  public ThumbUrl() {
  }

  public ThumbUrl(String thumbnailPic) {
    this.thumbnailPic = thumbnailPic;
  }

  @JsonProperty("thumbnail_pic") private String thumbnailPic;

  @JsonProperty("thumbnail_pic") public String getThumbnailPic() {
    return thumbnailPic;
  }

  @JsonProperty("thumbnail_pic") public void setThumbnailPic(String thumbnailPic) {
    this.thumbnailPic = thumbnailPic;
  }
}
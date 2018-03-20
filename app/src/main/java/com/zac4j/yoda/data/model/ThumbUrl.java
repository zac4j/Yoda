package com.zac4j.yoda.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

/**
 * Thumbnail Url Model
 * Created by zac on 4/1/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "thumbnail_pic"
}) public class ThumbUrl implements Serializable {

    @JsonProperty("thumbnail_pic")
    private String thumbnailPic;

    public ThumbUrl() {
    }

    public ThumbUrl(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    @JsonProperty("thumbnail_pic")
    public String getThumbnailPic() {
        return thumbnailPic;
    }

    @JsonProperty("thumbnail_pic")
    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }
}
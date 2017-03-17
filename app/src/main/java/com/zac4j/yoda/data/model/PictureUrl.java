package com.zac4j.yoda.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PictureUrl {

  @SerializedName("thumbnail_pic")
  @Expose
  private String thumbnailPic;

  public String getThumbnailPic() {
    return thumbnailPic;
  }

  public void setThumbnailPic(String thumbnailPic) {
    this.thumbnailPic = thumbnailPic;
  }

}
package com.zac4j.yoda.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Insecurity Model
 * Created by zac on 3/17/2017.
 */

public class Insecurity {

  @SerializedName("sexual_content")
  @Expose
  private Boolean sexualContent;

  public Boolean getSexualContent() {
    return sexualContent;
  }

  public void setSexualContent(Boolean sexualContent) {
    this.sexualContent = sexualContent;
  }

}
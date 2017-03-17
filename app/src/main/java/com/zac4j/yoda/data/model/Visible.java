package com.zac4j.yoda.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Weibo Visible Model
 * Created by zac on 3/17/2017.
 */

public class Visible {

  @SerializedName("type")
  @Expose
  private Integer type;
  @SerializedName("list_id")
  @Expose
  private Integer listId;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getListId() {
    return listId;
  }

  public void setListId(Integer listId) {
    this.listId = listId;
  }

}

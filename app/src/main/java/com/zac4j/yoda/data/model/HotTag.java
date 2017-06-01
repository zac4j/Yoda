package com.zac4j.yoda.data.model;

import java.util.List;

/**
 * Model for Hot Tag
 * Created by zaccc on 6/1/2017.
 */

public class HotTag {

  private List<Tag> mTagList;

  public List<Tag> getTagList() {
    return mTagList;
  }

  public void setTagList(List<Tag> tagList) {
    mTagList = tagList;
  }
}

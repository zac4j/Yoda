package com.zac4j.yoda.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Weibo timeline
 * Created by zac on 3/17/2017.
 */

public class Timeline {

  @SerializedName("statuses") @Expose private List<Weibo> statuses = null;
  @SerializedName("advertises") @Expose private List<Object> advertises = null;
  @SerializedName("ad") @Expose private List<Object> ad = null;
  @SerializedName("hasvisible") @Expose private Boolean hasvisible;
  @SerializedName("previous_cursor") @Expose private Integer previousCursor;
  @SerializedName("next_cursor") @Expose private Integer nextCursor;
  @SerializedName("total_number") @Expose private Integer totalNumber;
  @SerializedName("interval") @Expose private Integer interval;
  @SerializedName("uve_blank") @Expose private Integer uveBlank;
  @SerializedName("since_id") @Expose private Integer sinceId;
  @SerializedName("max_id") @Expose private Integer maxId;
  @SerializedName("has_unread") @Expose private Integer hasUnread;

  public List<Weibo> getWeiboList() {
    return statuses;
  }

  public void setWeiboList(List<Weibo> statuses) {
    this.statuses = statuses;
  }

  public List<Object> getAdvertises() {
    return advertises;
  }

  public void setAdvertises(List<Object> advertises) {
    this.advertises = advertises;
  }

  public List<Object> getAd() {
    return ad;
  }

  public void setAd(List<Object> ad) {
    this.ad = ad;
  }

  public Boolean getHasvisible() {
    return hasvisible;
  }

  public void setHasvisible(Boolean hasvisible) {
    this.hasvisible = hasvisible;
  }

  public Integer getPreviousCursor() {
    return previousCursor;
  }

  public void setPreviousCursor(Integer previousCursor) {
    this.previousCursor = previousCursor;
  }

  public Integer getNextCursor() {
    return nextCursor;
  }

  public void setNextCursor(Integer nextCursor) {
    this.nextCursor = nextCursor;
  }

  public Integer getTotalNumber() {
    return totalNumber;
  }

  public void setTotalNumber(Integer totalNumber) {
    this.totalNumber = totalNumber;
  }

  public Integer getInterval() {
    return interval;
  }

  public void setInterval(Integer interval) {
    this.interval = interval;
  }

  public Integer getUveBlank() {
    return uveBlank;
  }

  public void setUveBlank(Integer uveBlank) {
    this.uveBlank = uveBlank;
  }

  public Integer getSinceId() {
    return sinceId;
  }

  public void setSinceId(Integer sinceId) {
    this.sinceId = sinceId;
  }

  public Integer getMaxId() {
    return maxId;
  }

  public void setMaxId(Integer maxId) {
    this.maxId = maxId;
  }

  public Integer getHasUnread() {
    return hasUnread;
  }

  public void setHasUnread(Integer hasUnread) {
    this.hasUnread = hasUnread;
  }
}

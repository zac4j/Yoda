package com.zac4j.yoda.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Repost Weibo Model
 * Created by zac on 3/17/2017.
 */

public class RepostedWeibo {

  @SerializedName("created_at")
  @Expose
  private String createdAt;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("mid")
  @Expose
  private String mid;
  @SerializedName("idstr")
  @Expose
  private String idstr;
  @SerializedName("text")
  @Expose
  private String text;
  @SerializedName("textLength")
  @Expose
  private Integer textLength;
  @SerializedName("source_allowclick")
  @Expose
  private Integer sourceAllowclick;
  @SerializedName("source_type")
  @Expose
  private Integer sourceType;
  @SerializedName("source")
  @Expose
  private String source;
  @SerializedName("favorited")
  @Expose
  private Boolean favorited;
  @SerializedName("truncated")
  @Expose
  private Boolean truncated;
  @SerializedName("in_reply_to_status_id")
  @Expose
  private String inReplyToStatusId;
  @SerializedName("in_reply_to_user_id")
  @Expose
  private String inReplyToUserId;
  @SerializedName("in_reply_to_screen_name")
  @Expose
  private String inReplyToScreenName;
  @SerializedName("pic_urls")
  @Expose
  private List<PictureUrl> picUrls = null;
  @SerializedName("thumbnail_pic")
  @Expose
  private String thumbnailPic;
  @SerializedName("bmiddle_pic")
  @Expose
  private String bmiddlePic;
  @SerializedName("original_pic")
  @Expose
  private String originalPic;
  @SerializedName("geo")
  @Expose
  private Object geo;
  @SerializedName("user")
  @Expose
  private User user;
  @SerializedName("reposts_count")
  @Expose
  private Integer repostsCount;
  @SerializedName("comments_count")
  @Expose
  private Integer commentsCount;
  @SerializedName("attitudes_count")
  @Expose
  private Integer attitudesCount;
  @SerializedName("isLongText")
  @Expose
  private Boolean isLongText;
  @SerializedName("mlevel")
  @Expose
  private Integer mlevel;
  @SerializedName("visible")
  @Expose
  private Visible visible;
  @SerializedName("biz_ids")
  @Expose
  private List<Integer> bizIds = null;
  @SerializedName("biz_feature")
  @Expose
  private Integer bizFeature;
  @SerializedName("page_type")
  @Expose
  private Integer pageType;
  @SerializedName("hasActionTypeCard")
  @Expose
  private Integer hasActionTypeCard;
  @SerializedName("darwin_tags")
  @Expose
  private List<Object> darwinTags = null;
  @SerializedName("hot_weibo_tags")
  @Expose
  private List<Object> hotWeiboTags = null;
  @SerializedName("text_tag_tips")
  @Expose
  private List<Object> textTagTips = null;
  @SerializedName("userType")
  @Expose
  private Integer userType;
  @SerializedName("cardid")
  @Expose
  private String cardid;
  @SerializedName("positive_recom_flag")
  @Expose
  private Integer positiveRecomFlag;
  @SerializedName("gif_ids")
  @Expose
  private String gifIds;
  @SerializedName("is_show_bulletin")
  @Expose
  private Integer isShowBulletin;

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getIdstr() {
    return idstr;
  }

  public void setIdstr(String idstr) {
    this.idstr = idstr;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Integer getTextLength() {
    return textLength;
  }

  public void setTextLength(Integer textLength) {
    this.textLength = textLength;
  }

  public Integer getSourceAllowclick() {
    return sourceAllowclick;
  }

  public void setSourceAllowclick(Integer sourceAllowclick) {
    this.sourceAllowclick = sourceAllowclick;
  }

  public Integer getSourceType() {
    return sourceType;
  }

  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public Boolean getFavorited() {
    return favorited;
  }

  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  public Boolean getTruncated() {
    return truncated;
  }

  public void setTruncated(Boolean truncated) {
    this.truncated = truncated;
  }

  public String getInReplyToStatusId() {
    return inReplyToStatusId;
  }

  public void setInReplyToStatusId(String inReplyToStatusId) {
    this.inReplyToStatusId = inReplyToStatusId;
  }

  public String getInReplyToUserId() {
    return inReplyToUserId;
  }

  public void setInReplyToUserId(String inReplyToUserId) {
    this.inReplyToUserId = inReplyToUserId;
  }

  public String getInReplyToScreenName() {
    return inReplyToScreenName;
  }

  public void setInReplyToScreenName(String inReplyToScreenName) {
    this.inReplyToScreenName = inReplyToScreenName;
  }

  public List<PictureUrl> getPicUrls() {
    return picUrls;
  }

  public void setPicUrls(List<PictureUrl> picUrls) {
    this.picUrls = picUrls;
  }

  public String getThumbnailPic() {
    return thumbnailPic;
  }

  public void setThumbnailPic(String thumbnailPic) {
    this.thumbnailPic = thumbnailPic;
  }

  public String getBmiddlePic() {
    return bmiddlePic;
  }

  public void setBmiddlePic(String bmiddlePic) {
    this.bmiddlePic = bmiddlePic;
  }

  public String getOriginalPic() {
    return originalPic;
  }

  public void setOriginalPic(String originalPic) {
    this.originalPic = originalPic;
  }

  public Object getGeo() {
    return geo;
  }

  public void setGeo(Object geo) {
    this.geo = geo;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getRepostsCount() {
    return repostsCount;
  }

  public void setRepostsCount(Integer repostsCount) {
    this.repostsCount = repostsCount;
  }

  public Integer getCommentsCount() {
    return commentsCount;
  }

  public void setCommentsCount(Integer commentsCount) {
    this.commentsCount = commentsCount;
  }

  public Integer getAttitudesCount() {
    return attitudesCount;
  }

  public void setAttitudesCount(Integer attitudesCount) {
    this.attitudesCount = attitudesCount;
  }

  public Boolean getIsLongText() {
    return isLongText;
  }

  public void setIsLongText(Boolean isLongText) {
    this.isLongText = isLongText;
  }

  public Integer getMlevel() {
    return mlevel;
  }

  public void setMlevel(Integer mlevel) {
    this.mlevel = mlevel;
  }

  public Visible getVisible() {
    return visible;
  }

  public void setVisible(Visible visible) {
    this.visible = visible;
  }

  public List<Integer> getBizIds() {
    return bizIds;
  }

  public void setBizIds(List<Integer> bizIds) {
    this.bizIds = bizIds;
  }

  public Integer getBizFeature() {
    return bizFeature;
  }

  public void setBizFeature(Integer bizFeature) {
    this.bizFeature = bizFeature;
  }

  public Integer getPageType() {
    return pageType;
  }

  public void setPageType(Integer pageType) {
    this.pageType = pageType;
  }

  public Integer getHasActionTypeCard() {
    return hasActionTypeCard;
  }

  public void setHasActionTypeCard(Integer hasActionTypeCard) {
    this.hasActionTypeCard = hasActionTypeCard;
  }

  public List<Object> getDarwinTags() {
    return darwinTags;
  }

  public void setDarwinTags(List<Object> darwinTags) {
    this.darwinTags = darwinTags;
  }

  public List<Object> getHotWeiboTags() {
    return hotWeiboTags;
  }

  public void setHotWeiboTags(List<Object> hotWeiboTags) {
    this.hotWeiboTags = hotWeiboTags;
  }

  public List<Object> getTextTagTips() {
    return textTagTips;
  }

  public void setTextTagTips(List<Object> textTagTips) {
    this.textTagTips = textTagTips;
  }

  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }

  public String getCardid() {
    return cardid;
  }

  public void setCardid(String cardid) {
    this.cardid = cardid;
  }

  public Integer getPositiveRecomFlag() {
    return positiveRecomFlag;
  }

  public void setPositiveRecomFlag(Integer positiveRecomFlag) {
    this.positiveRecomFlag = positiveRecomFlag;
  }

  public String getGifIds() {
    return gifIds;
  }

  public void setGifIds(String gifIds) {
    this.gifIds = gifIds;
  }

  public Integer getIsShowBulletin() {
    return isShowBulletin;
  }

  public void setIsShowBulletin(Integer isShowBulletin) {
    this.isShowBulletin = isShowBulletin;
  }

}

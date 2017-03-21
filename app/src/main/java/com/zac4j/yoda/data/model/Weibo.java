package com.zac4j.yoda.data.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Weibo Model
 * Created by zac on 3/21/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "created_at",
    "id",
    "mid",
    "idstr",
    "text",
    "textLength",
    "source_allowclick",
    "source_type",
    "source",
    "favorited",
    "truncated",
    "in_reply_to_status_id",
    "in_reply_to_user_id",
    "in_reply_to_screen_name",
    "pic_urls",
    "thumbnail_pic",
    "bmiddle_pic",
    "original_pic",
    "geo",
    "user",
    "reposts_count",
    "comments_count",
    "attitudes_count",
    "isLongText",
    "mlevel",
    "visible",
    "biz_feature",
    "page_type",
    "hasActionTypeCard",
    "darwin_tags",
    "hot_weibo_tags",
    "text_tag_tips",
    "rid",
    "userType",
    "cardid",
    "positive_recom_flag",
    "gif_ids",
    "is_show_bulletin"
})

public class Weibo {

  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty("id")
  private Integer id;
  @JsonProperty("mid")
  private String mid;
  @JsonProperty("idstr")
  private String idstr;
  @JsonProperty("text")
  private String text;
  @JsonProperty("textLength")
  private Integer textLength;
  @JsonProperty("source_allowclick")
  private Integer sourceAllowclick;
  @JsonProperty("source_type")
  private Integer sourceType;
  @JsonProperty("source")
  private String source;
  @JsonProperty("favorited")
  private Boolean favorited;
  @JsonProperty("truncated")
  private Boolean truncated;
  @JsonProperty("in_reply_to_status_id")
  private String inReplyToStatusId;
  @JsonProperty("in_reply_to_user_id")
  private String inReplyToUserId;
  @JsonProperty("in_reply_to_screen_name")
  private String inReplyToScreenName;
  @JsonProperty("pic_urls")
  private List<Object> picUrls = null;
  @JsonProperty("thumbnail_pic")
  private String thumbnailPic;
  @JsonProperty("bmiddle_pic")
  private String bmiddlePic;
  @JsonProperty("original_pic")
  private String originalPic;
  @JsonProperty("geo")
  private Object geo;
  @JsonProperty("user")
  private User user;
  @JsonProperty("reposts_count")
  private Integer repostsCount;
  @JsonProperty("comments_count")
  private Integer commentsCount;
  @JsonProperty("attitudes_count")
  private Integer attitudesCount;
  @JsonProperty("isLongText")
  private Boolean isLongText;
  @JsonProperty("mlevel")
  private Integer mlevel;
  @JsonProperty("visible")
  private Visible visible;
  @JsonProperty("biz_feature")
  private Integer bizFeature;
  @JsonProperty("page_type")
  private Integer pageType;
  @JsonProperty("hasActionTypeCard")
  private Integer hasActionTypeCard;
  @JsonProperty("darwin_tags")
  private List<Object> darwinTags = null;
  @JsonProperty("hot_weibo_tags")
  private List<Object> hotWeiboTags = null;
  @JsonProperty("text_tag_tips")
  private List<Object> textTagTips = null;
  @JsonProperty("rid")
  private String rid;
  @JsonProperty("userType")
  private Integer userType;
  @JsonProperty("cardid")
  private String cardid;
  @JsonProperty("positive_recom_flag")
  private Integer positiveRecomFlag;
  @JsonProperty("gif_ids")
  private String gifIds;
  @JsonProperty("is_show_bulletin")
  private Integer isShowBulletin;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("created_at")
  public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("created_at")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("mid")
  public String getMid() {
    return mid;
  }

  @JsonProperty("mid")
  public void setMid(String mid) {
    this.mid = mid;
  }

  @JsonProperty("idstr")
  public String getIdstr() {
    return idstr;
  }

  @JsonProperty("idstr")
  public void setIdstr(String idstr) {
    this.idstr = idstr;
  }

  @JsonProperty("text")
  public String getText() {
    return text;
  }

  @JsonProperty("text")
  public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("textLength")
  public Integer getTextLength() {
    return textLength;
  }

  @JsonProperty("textLength")
  public void setTextLength(Integer textLength) {
    this.textLength = textLength;
  }

  @JsonProperty("source_allowclick")
  public Integer getSourceAllowclick() {
    return sourceAllowclick;
  }

  @JsonProperty("source_allowclick")
  public void setSourceAllowclick(Integer sourceAllowclick) {
    this.sourceAllowclick = sourceAllowclick;
  }

  @JsonProperty("source_type")
  public Integer getSourceType() {
    return sourceType;
  }

  @JsonProperty("source_type")
  public void setSourceType(Integer sourceType) {
    this.sourceType = sourceType;
  }

  @JsonProperty("source")
  public String getSource() {
    return source;
  }

  @JsonProperty("source")
  public void setSource(String source) {
    this.source = source;
  }

  @JsonProperty("favorited")
  public Boolean getFavorited() {
    return favorited;
  }

  @JsonProperty("favorited")
  public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  @JsonProperty("truncated")
  public Boolean getTruncated() {
    return truncated;
  }

  @JsonProperty("truncated")
  public void setTruncated(Boolean truncated) {
    this.truncated = truncated;
  }

  @JsonProperty("in_reply_to_status_id")
  public String getInReplyToStatusId() {
    return inReplyToStatusId;
  }

  @JsonProperty("in_reply_to_status_id")
  public void setInReplyToStatusId(String inReplyToStatusId) {
    this.inReplyToStatusId = inReplyToStatusId;
  }

  @JsonProperty("in_reply_to_user_id")
  public String getInReplyToUserId() {
    return inReplyToUserId;
  }

  @JsonProperty("in_reply_to_user_id")
  public void setInReplyToUserId(String inReplyToUserId) {
    this.inReplyToUserId = inReplyToUserId;
  }

  @JsonProperty("in_reply_to_screen_name")
  public String getInReplyToScreenName() {
    return inReplyToScreenName;
  }

  @JsonProperty("in_reply_to_screen_name")
  public void setInReplyToScreenName(String inReplyToScreenName) {
    this.inReplyToScreenName = inReplyToScreenName;
  }

  @JsonProperty("pic_urls")
  public List<Object> getPicUrls() {
    return picUrls;
  }

  @JsonProperty("pic_urls")
  public void setPicUrls(List<Object> picUrls) {
    this.picUrls = picUrls;
  }

  @JsonProperty("thumbnail_pic")
  public String getThumbnailPic() {
    return thumbnailPic;
  }

  @JsonProperty("thumbnail_pic")
  public void setThumbnailPic(String thumbnailPic) {
    this.thumbnailPic = thumbnailPic;
  }

  @JsonProperty("bmiddle_pic")
  public String getBmiddlePic() {
    return bmiddlePic;
  }

  @JsonProperty("bmiddle_pic")
  public void setBmiddlePic(String bmiddlePic) {
    this.bmiddlePic = bmiddlePic;
  }

  @JsonProperty("original_pic")
  public String getOriginalPic() {
    return originalPic;
  }

  @JsonProperty("original_pic")
  public void setOriginalPic(String originalPic) {
    this.originalPic = originalPic;
  }

  @JsonProperty("geo")
  public Object getGeo() {
    return geo;
  }

  @JsonProperty("geo")
  public void setGeo(Object geo) {
    this.geo = geo;
  }

  @JsonProperty("user")
  public User getUser() {
    return user;
  }

  @JsonProperty("user")
  public void setUser(User user) {
    this.user = user;
  }

  @JsonProperty("reposts_count")
  public Integer getRepostsCount() {
    return repostsCount;
  }

  @JsonProperty("reposts_count")
  public void setRepostsCount(Integer repostsCount) {
    this.repostsCount = repostsCount;
  }

  @JsonProperty("comments_count")
  public Integer getCommentsCount() {
    return commentsCount;
  }

  @JsonProperty("comments_count")
  public void setCommentsCount(Integer commentsCount) {
    this.commentsCount = commentsCount;
  }

  @JsonProperty("attitudes_count")
  public Integer getAttitudesCount() {
    return attitudesCount;
  }

  @JsonProperty("attitudes_count")
  public void setAttitudesCount(Integer attitudesCount) {
    this.attitudesCount = attitudesCount;
  }

  @JsonProperty("isLongText")
  public Boolean getIsLongText() {
    return isLongText;
  }

  @JsonProperty("isLongText")
  public void setIsLongText(Boolean isLongText) {
    this.isLongText = isLongText;
  }

  @JsonProperty("mlevel")
  public Integer getMlevel() {
    return mlevel;
  }

  @JsonProperty("mlevel")
  public void setMlevel(Integer mlevel) {
    this.mlevel = mlevel;
  }

  @JsonProperty("visible")
  public Visible getVisible() {
    return visible;
  }

  @JsonProperty("visible")
  public void setVisible(Visible visible) {
    this.visible = visible;
  }

  @JsonProperty("biz_feature")
  public Integer getBizFeature() {
    return bizFeature;
  }

  @JsonProperty("biz_feature")
  public void setBizFeature(Integer bizFeature) {
    this.bizFeature = bizFeature;
  }

  @JsonProperty("page_type")
  public Integer getPageType() {
    return pageType;
  }

  @JsonProperty("page_type")
  public void setPageType(Integer pageType) {
    this.pageType = pageType;
  }

  @JsonProperty("hasActionTypeCard")
  public Integer getHasActionTypeCard() {
    return hasActionTypeCard;
  }

  @JsonProperty("hasActionTypeCard")
  public void setHasActionTypeCard(Integer hasActionTypeCard) {
    this.hasActionTypeCard = hasActionTypeCard;
  }

  @JsonProperty("darwin_tags")
  public List<Object> getDarwinTags() {
    return darwinTags;
  }

  @JsonProperty("darwin_tags")
  public void setDarwinTags(List<Object> darwinTags) {
    this.darwinTags = darwinTags;
  }

  @JsonProperty("hot_weibo_tags")
  public List<Object> getHotWeiboTags() {
    return hotWeiboTags;
  }

  @JsonProperty("hot_weibo_tags")
  public void setHotWeiboTags(List<Object> hotWeiboTags) {
    this.hotWeiboTags = hotWeiboTags;
  }

  @JsonProperty("text_tag_tips")
  public List<Object> getTextTagTips() {
    return textTagTips;
  }

  @JsonProperty("text_tag_tips")
  public void setTextTagTips(List<Object> textTagTips) {
    this.textTagTips = textTagTips;
  }

  @JsonProperty("rid")
  public String getRid() {
    return rid;
  }

  @JsonProperty("rid")
  public void setRid(String rid) {
    this.rid = rid;
  }

  @JsonProperty("userType")
  public Integer getUserType() {
    return userType;
  }

  @JsonProperty("userType")
  public void setUserType(Integer userType) {
    this.userType = userType;
  }

  @JsonProperty("cardid")
  public String getCardid() {
    return cardid;
  }

  @JsonProperty("cardid")
  public void setCardid(String cardid) {
    this.cardid = cardid;
  }

  @JsonProperty("positive_recom_flag")
  public Integer getPositiveRecomFlag() {
    return positiveRecomFlag;
  }

  @JsonProperty("positive_recom_flag")
  public void setPositiveRecomFlag(Integer positiveRecomFlag) {
    this.positiveRecomFlag = positiveRecomFlag;
  }

  @JsonProperty("gif_ids")
  public String getGifIds() {
    return gifIds;
  }

  @JsonProperty("gif_ids")
  public void setGifIds(String gifIds) {
    this.gifIds = gifIds;
  }

  @JsonProperty("is_show_bulletin")
  public Integer getIsShowBulletin() {
    return isShowBulletin;
  }

  @JsonProperty("is_show_bulletin")
  public void setIsShowBulletin(Integer isShowBulletin) {
    this.isShowBulletin = isShowBulletin;
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

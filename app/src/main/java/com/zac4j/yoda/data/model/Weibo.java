package com.zac4j.yoda.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Weibo Model
 * Created by zac on 3/21/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "created_at", "id", "mid", "idstr", "text", "textLength", "source_allowclick", "source_type",
    "source", "favorited", "truncated", "in_reply_to_status_id", "in_reply_to_user_id",
    "in_reply_to_screen_name", "pic_urls", "thumbnail_pic", "bmiddle_pic", "original_pic", "geo",
    "user", "reposts_count", "retweeted_status", "comments_count", "attitudes_count", "isLongText",
    "mlevel", "visible", "biz_feature", "page_type", "hasActionTypeCard", "darwin_tags",
    "hot_weibo_tags", "text_tag_tips", "rid", "userType", "cardid", "positive_recom_flag",
    "gif_ids", "is_show_bulletin"
})

public class Weibo {
  @JsonProperty("created_at") private String createdAt;
  @JsonProperty("id") private Long id;
  @JsonProperty("mid") private String mid;
  @JsonProperty("idstr") private String idstr;
  @JsonProperty("text") private String text;
  @JsonProperty("textLength") private Long textLength;
  @JsonProperty("source_allowclick") private Long sourceAllowclick;
  @JsonProperty("source_type") private Long sourceType;
  @JsonProperty("source") private String source;
  @JsonProperty("favorited") private Boolean favorited;
  @JsonProperty("truncated") private Boolean truncated;
  @JsonProperty("in_reply_to_status_id") private String inReplyToStatusId;
  @JsonProperty("in_reply_to_user_id") private String inReplyToUserId;
  @JsonProperty("in_reply_to_screen_name") private String inReplyToScreenName;
  @JsonProperty("pic_urls") private List<ThumbUrl> picUrls = null;
  @JsonProperty("thumbnail_pic") private String thumbnailPic;
  @JsonProperty("bmiddle_pic") private String bmiddlePic;
  @JsonProperty("original_pic") private String originalPic;
  @JsonProperty("geo") private Geography geo;
  @JsonProperty("user") private User user;
  @JsonProperty("reposts_count") private Long repostsCount;
  @JsonProperty("comments_count") private Long commentsCount;
  @JsonProperty("retweeted_status") private Weibo repostWeibo;
  @JsonProperty("attitudes_count") private Long attitudesCount;
  @JsonProperty("isLongText") private Boolean isLongText;
  @JsonProperty("mlevel") private Long mlevel;
  @JsonProperty("visible") private Visible visible;
  @JsonProperty("biz_feature") private Long bizFeature;
  @JsonProperty("page_type") private Long pageType;
  @JsonProperty("hasActionTypeCard") private Long hasActionTypeCard;
  @JsonProperty("darwin_tags") private List<Object> darwinTags = null;
  @JsonProperty("hot_weibo_tags") private List<Object> hotWeiboTags = null;
  @JsonProperty("text_tag_tips") private List<Object> textTagTips = null;
  @JsonProperty("rid") private String rid;
  @JsonProperty("userType") private Long userType;
  @JsonProperty("cardid") private String cardid;
  @JsonProperty("positive_recom_flag") private Long positiveRecomFlag;
  @JsonProperty("gif_ids") private String gifIds;
  @JsonProperty("is_show_bulletin") private Long isShowBulletin;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty("created_at") public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("created_at") public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("id") public Long getId() {
    return id;
  }

  @JsonProperty("id") public void setId(Long id) {
    this.id = id;
  }

  @JsonProperty("mid") public String getMid() {
    return mid;
  }

  @JsonProperty("mid") public void setMid(String mid) {
    this.mid = mid;
  }

  @JsonProperty("idstr") public String getIdstr() {
    return idstr;
  }

  @JsonProperty("idstr") public void setIdstr(String idstr) {
    this.idstr = idstr;
  }

  @JsonProperty("text") public String getText() {
    return text;
  }

  @JsonProperty("text") public void setText(String text) {
    this.text = text;
  }

  @JsonProperty("textLength") public Long getTextLength() {
    return textLength;
  }

  @JsonProperty("textLength") public void setTextLength(Long textLength) {
    this.textLength = textLength;
  }

  @JsonProperty("source_allowclick") public Long getSourceAllowclick() {
    return sourceAllowclick;
  }

  @JsonProperty("source_allowclick") public void setSourceAllowclick(Long sourceAllowclick) {
    this.sourceAllowclick = sourceAllowclick;
  }

  @JsonProperty("source_type") public Long getSourceType() {
    return sourceType;
  }

  @JsonProperty("source_type") public void setSourceType(Long sourceType) {
    this.sourceType = sourceType;
  }

  @JsonProperty("source") public String getSource() {
    return source;
  }

  @JsonProperty("source") public void setSource(String source) {
    this.source = source;
  }

  @JsonProperty("favorited") public Boolean getFavorited() {
    return favorited;
  }

  @JsonProperty("favorited") public void setFavorited(Boolean favorited) {
    this.favorited = favorited;
  }

  @JsonProperty("truncated") public Boolean getTruncated() {
    return truncated;
  }

  @JsonProperty("truncated") public void setTruncated(Boolean truncated) {
    this.truncated = truncated;
  }

  @JsonProperty("in_reply_to_status_id") public String getInReplyToStatusId() {
    return inReplyToStatusId;
  }

  @JsonProperty("in_reply_to_status_id")
  public void setInReplyToStatusId(String inReplyToStatusId) {
    this.inReplyToStatusId = inReplyToStatusId;
  }

  @JsonProperty("in_reply_to_user_id") public String getInReplyToUserId() {
    return inReplyToUserId;
  }

  @JsonProperty("in_reply_to_user_id") public void setInReplyToUserId(String inReplyToUserId) {
    this.inReplyToUserId = inReplyToUserId;
  }

  @JsonProperty("in_reply_to_screen_name") public String getInReplyToScreenName() {
    return inReplyToScreenName;
  }

  @JsonProperty("in_reply_to_screen_name")
  public void setInReplyToScreenName(String inReplyToScreenName) {
    this.inReplyToScreenName = inReplyToScreenName;
  }

  @JsonProperty("pic_urls") public List<ThumbUrl> getPicUrls() {
    return picUrls;
  }

  @JsonProperty("pic_urls") public void setPicUrls(List<ThumbUrl> picUrls) {
    this.picUrls = picUrls;
  }

  @JsonProperty("thumbnail_pic") public String getThumbnailPic() {
    return thumbnailPic;
  }

  @JsonProperty("thumbnail_pic") public void setThumbnailPic(String thumbnailPic) {
    this.thumbnailPic = thumbnailPic;
  }

  @JsonProperty("bmiddle_pic") public String getBmiddlePic() {
    return bmiddlePic;
  }

  @JsonProperty("bmiddle_pic") public void setBmiddlePic(String bmiddlePic) {
    this.bmiddlePic = bmiddlePic;
  }

  @JsonProperty("original_pic") public String getOriginalPic() {
    return originalPic;
  }

  @JsonProperty("original_pic") public void setOriginalPic(String originalPic) {
    this.originalPic = originalPic;
  }

  @JsonProperty("geo") public Geography getGeo() {
    return geo;
  }

  @JsonProperty("geo") public void setGeo(Geography geo) {
    this.geo = geo;
  }

  @JsonProperty("user") public User getUser() {
    return user;
  }

  @JsonProperty("user") public void setUser(User user) {
    this.user = user;
  }

  @JsonProperty("reposts_count") public Long getRepostsCount() {
    return repostsCount;
  }

  @JsonProperty("reposts_count") public void setRepostsCount(Long repostsCount) {
    this.repostsCount = repostsCount;
  }

  @JsonProperty("retweeted_status") public Weibo getRepostWeibo() {
    return repostWeibo;
  }

  @JsonProperty("retweeted_status") public void setRepostWeibo(Weibo repostWeibo) {
    this.repostWeibo = repostWeibo;
  }

  @JsonProperty("comments_count") public Long getCommentsCount() {
    return commentsCount;
  }

  @JsonProperty("comments_count") public void setCommentsCount(Long commentsCount) {
    this.commentsCount = commentsCount;
  }

  @JsonProperty("attitudes_count") public Long getAttitudesCount() {
    return attitudesCount;
  }

  @JsonProperty("attitudes_count") public void setAttitudesCount(Long attitudesCount) {
    this.attitudesCount = attitudesCount;
  }

  @JsonProperty("isLongText") public Boolean getIsLongText() {
    return isLongText;
  }

  @JsonProperty("isLongText") public void setIsLongText(Boolean isLongText) {
    this.isLongText = isLongText;
  }

  @JsonProperty("mlevel") public Long getMlevel() {
    return mlevel;
  }

  @JsonProperty("mlevel") public void setMlevel(Long mlevel) {
    this.mlevel = mlevel;
  }

  @JsonProperty("visible") public Visible getVisible() {
    return visible;
  }

  @JsonProperty("visible") public void setVisible(Visible visible) {
    this.visible = visible;
  }

  @JsonProperty("biz_feature") public Long getBizFeature() {
    return bizFeature;
  }

  @JsonProperty("biz_feature") public void setBizFeature(Long bizFeature) {
    this.bizFeature = bizFeature;
  }

  @JsonProperty("page_type") public Long getPageType() {
    return pageType;
  }

  @JsonProperty("page_type") public void setPageType(Long pageType) {
    this.pageType = pageType;
  }

  @JsonProperty("hasActionTypeCard") public Long getHasActionTypeCard() {
    return hasActionTypeCard;
  }

  @JsonProperty("hasActionTypeCard") public void setHasActionTypeCard(Long hasActionTypeCard) {
    this.hasActionTypeCard = hasActionTypeCard;
  }

  @JsonProperty("darwin_tags") public List<Object> getDarwinTags() {
    return darwinTags;
  }

  @JsonProperty("darwin_tags") public void setDarwinTags(List<Object> darwinTags) {
    this.darwinTags = darwinTags;
  }

  @JsonProperty("hot_weibo_tags") public List<Object> getHotWeiboTags() {
    return hotWeiboTags;
  }

  @JsonProperty("hot_weibo_tags") public void setHotWeiboTags(List<Object> hotWeiboTags) {
    this.hotWeiboTags = hotWeiboTags;
  }

  @JsonProperty("text_tag_tips") public List<Object> getTextTagTips() {
    return textTagTips;
  }

  @JsonProperty("text_tag_tips") public void setTextTagTips(List<Object> textTagTips) {
    this.textTagTips = textTagTips;
  }

  @JsonProperty("rid") public String getRid() {
    return rid;
  }

  @JsonProperty("rid") public void setRid(String rid) {
    this.rid = rid;
  }

  @JsonProperty("userType") public Long getUserType() {
    return userType;
  }

  @JsonProperty("userType") public void setUserType(Long userType) {
    this.userType = userType;
  }

  @JsonProperty("cardid") public String getCardid() {
    return cardid;
  }

  @JsonProperty("cardid") public void setCardid(String cardid) {
    this.cardid = cardid;
  }

  @JsonProperty("positive_recom_flag") public Long getPositiveRecomFlag() {
    return positiveRecomFlag;
  }

  @JsonProperty("positive_recom_flag") public void setPositiveRecomFlag(Long positiveRecomFlag) {
    this.positiveRecomFlag = positiveRecomFlag;
  }

  @JsonProperty("gif_ids") public String getGifIds() {
    return gifIds;
  }

  @JsonProperty("gif_ids") public void setGifIds(String gifIds) {
    this.gifIds = gifIds;
  }

  @JsonProperty("is_show_bulletin") public Long getIsShowBulletin() {
    return isShowBulletin;
  }

  @JsonProperty("is_show_bulletin") public void setIsShowBulletin(Long isShowBulletin) {
    this.isShowBulletin = isShowBulletin;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Weibo weibo = (Weibo) o;

    if (createdAt != null ? !createdAt.equals(weibo.createdAt) : weibo.createdAt != null) {
      return false;
    }
    if (id != null ? !id.equals(weibo.id) : weibo.id != null) return false;
    if (mid != null ? !mid.equals(weibo.mid) : weibo.mid != null) return false;
    if (idstr != null ? !idstr.equals(weibo.idstr) : weibo.idstr != null) return false;
    if (text != null ? !text.equals(weibo.text) : weibo.text != null) return false;
    if (textLength != null ? !textLength.equals(weibo.textLength) : weibo.textLength != null) {
      return false;
    }
    if (sourceAllowclick != null ? !sourceAllowclick.equals(weibo.sourceAllowclick)
        : weibo.sourceAllowclick != null) {
      return false;
    }
    if (sourceType != null ? !sourceType.equals(weibo.sourceType) : weibo.sourceType != null) {
      return false;
    }
    if (source != null ? !source.equals(weibo.source) : weibo.source != null) return false;
    if (favorited != null ? !favorited.equals(weibo.favorited) : weibo.favorited != null) {
      return false;
    }
    if (truncated != null ? !truncated.equals(weibo.truncated) : weibo.truncated != null) {
      return false;
    }
    if (inReplyToStatusId != null ? !inReplyToStatusId.equals(weibo.inReplyToStatusId)
        : weibo.inReplyToStatusId != null) {
      return false;
    }
    if (inReplyToUserId != null ? !inReplyToUserId.equals(weibo.inReplyToUserId)
        : weibo.inReplyToUserId != null) {
      return false;
    }
    if (inReplyToScreenName != null ? !inReplyToScreenName.equals(weibo.inReplyToScreenName)
        : weibo.inReplyToScreenName != null) {
      return false;
    }
    if (picUrls != null ? !picUrls.equals(weibo.picUrls) : weibo.picUrls != null) return false;
    if (thumbnailPic != null ? !thumbnailPic.equals(weibo.thumbnailPic)
        : weibo.thumbnailPic != null) {
      return false;
    }
    if (bmiddlePic != null ? !bmiddlePic.equals(weibo.bmiddlePic) : weibo.bmiddlePic != null) {
      return false;
    }
    if (originalPic != null ? !originalPic.equals(weibo.originalPic) : weibo.originalPic != null) {
      return false;
    }
    if (geo != null ? !geo.equals(weibo.geo) : weibo.geo != null) return false;
    if (user != null ? !user.equals(weibo.user) : weibo.user != null) return false;
    if (repostsCount != null ? !repostsCount.equals(weibo.repostsCount)
        : weibo.repostsCount != null) {
      return false;
    }
    if (commentsCount != null ? !commentsCount.equals(weibo.commentsCount)
        : weibo.commentsCount != null) {
      return false;
    }
    if (repostWeibo != null ? !repostWeibo.equals(weibo.repostWeibo) : weibo.repostWeibo != null) {
      return false;
    }
    if (attitudesCount != null ? !attitudesCount.equals(weibo.attitudesCount)
        : weibo.attitudesCount != null) {
      return false;
    }
    if (isLongText != null ? !isLongText.equals(weibo.isLongText) : weibo.isLongText != null) {
      return false;
    }
    if (mlevel != null ? !mlevel.equals(weibo.mlevel) : weibo.mlevel != null) return false;
    if (visible != null ? !visible.equals(weibo.visible) : weibo.visible != null) return false;
    if (bizFeature != null ? !bizFeature.equals(weibo.bizFeature) : weibo.bizFeature != null) {
      return false;
    }
    if (pageType != null ? !pageType.equals(weibo.pageType) : weibo.pageType != null) return false;
    if (hasActionTypeCard != null ? !hasActionTypeCard.equals(weibo.hasActionTypeCard)
        : weibo.hasActionTypeCard != null) {
      return false;
    }
    if (darwinTags != null ? !darwinTags.equals(weibo.darwinTags) : weibo.darwinTags != null) {
      return false;
    }
    if (hotWeiboTags != null ? !hotWeiboTags.equals(weibo.hotWeiboTags)
        : weibo.hotWeiboTags != null) {
      return false;
    }
    if (textTagTips != null ? !textTagTips.equals(weibo.textTagTips) : weibo.textTagTips != null) {
      return false;
    }
    if (rid != null ? !rid.equals(weibo.rid) : weibo.rid != null) return false;
    if (userType != null ? !userType.equals(weibo.userType) : weibo.userType != null) return false;
    if (cardid != null ? !cardid.equals(weibo.cardid) : weibo.cardid != null) return false;
    if (positiveRecomFlag != null ? !positiveRecomFlag.equals(weibo.positiveRecomFlag)
        : weibo.positiveRecomFlag != null) {
      return false;
    }
    if (gifIds != null ? !gifIds.equals(weibo.gifIds) : weibo.gifIds != null) return false;
    if (isShowBulletin != null ? !isShowBulletin.equals(weibo.isShowBulletin)
        : weibo.isShowBulletin != null) {
      return false;
    }
    return additionalProperties != null ? additionalProperties.equals(weibo.additionalProperties)
        : weibo.additionalProperties == null;
  }

  @Override public int hashCode() {
    int result = createdAt != null ? createdAt.hashCode() : 0;
    result = 31 * result + (id != null ? id.hashCode() : 0);
    result = 31 * result + (mid != null ? mid.hashCode() : 0);
    result = 31 * result + (idstr != null ? idstr.hashCode() : 0);
    result = 31 * result + (text != null ? text.hashCode() : 0);
    result = 31 * result + (textLength != null ? textLength.hashCode() : 0);
    result = 31 * result + (sourceAllowclick != null ? sourceAllowclick.hashCode() : 0);
    result = 31 * result + (sourceType != null ? sourceType.hashCode() : 0);
    result = 31 * result + (source != null ? source.hashCode() : 0);
    result = 31 * result + (favorited != null ? favorited.hashCode() : 0);
    result = 31 * result + (truncated != null ? truncated.hashCode() : 0);
    result = 31 * result + (inReplyToStatusId != null ? inReplyToStatusId.hashCode() : 0);
    result = 31 * result + (inReplyToUserId != null ? inReplyToUserId.hashCode() : 0);
    result = 31 * result + (inReplyToScreenName != null ? inReplyToScreenName.hashCode() : 0);
    result = 31 * result + (picUrls != null ? picUrls.hashCode() : 0);
    result = 31 * result + (thumbnailPic != null ? thumbnailPic.hashCode() : 0);
    result = 31 * result + (bmiddlePic != null ? bmiddlePic.hashCode() : 0);
    result = 31 * result + (originalPic != null ? originalPic.hashCode() : 0);
    result = 31 * result + (geo != null ? geo.hashCode() : 0);
    result = 31 * result + (user != null ? user.hashCode() : 0);
    result = 31 * result + (repostsCount != null ? repostsCount.hashCode() : 0);
    result = 31 * result + (commentsCount != null ? commentsCount.hashCode() : 0);
    result = 31 * result + (repostWeibo != null ? repostWeibo.hashCode() : 0);
    result = 31 * result + (attitudesCount != null ? attitudesCount.hashCode() : 0);
    result = 31 * result + (isLongText != null ? isLongText.hashCode() : 0);
    result = 31 * result + (mlevel != null ? mlevel.hashCode() : 0);
    result = 31 * result + (visible != null ? visible.hashCode() : 0);
    result = 31 * result + (bizFeature != null ? bizFeature.hashCode() : 0);
    result = 31 * result + (pageType != null ? pageType.hashCode() : 0);
    result = 31 * result + (hasActionTypeCard != null ? hasActionTypeCard.hashCode() : 0);
    result = 31 * result + (darwinTags != null ? darwinTags.hashCode() : 0);
    result = 31 * result + (hotWeiboTags != null ? hotWeiboTags.hashCode() : 0);
    result = 31 * result + (textTagTips != null ? textTagTips.hashCode() : 0);
    result = 31 * result + (rid != null ? rid.hashCode() : 0);
    result = 31 * result + (userType != null ? userType.hashCode() : 0);
    result = 31 * result + (cardid != null ? cardid.hashCode() : 0);
    result = 31 * result + (positiveRecomFlag != null ? positiveRecomFlag.hashCode() : 0);
    result = 31 * result + (gifIds != null ? gifIds.hashCode() : 0);
    result = 31 * result + (isShowBulletin != null ? isShowBulletin.hashCode() : 0);
    result = 31 * result + (additionalProperties != null ? additionalProperties.hashCode() : 0);
    return result;
  }
}

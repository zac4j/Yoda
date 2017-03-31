package com.zac4j.yoda.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
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

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "created_at", "id", "mid", "idstr", "text", "textLength", "source_allowclick", "source_type",
    "source", "favorited", "truncated", "in_reply_to_status_id", "in_reply_to_user_id",
    "in_reply_to_screen_name", "pic_urls", "thumbnail_pic", "bmiddle_pic", "original_pic", "geo",
    "user", "reposts_count", "comments_count", "attitudes_count", "isLongText", "mlevel", "visible",
    "biz_feature", "page_type", "hasActionTypeCard", "darwin_tags", "hot_weibo_tags",
    "text_tag_tips", "rid", "userType", "cardid", "positive_recom_flag", "gif_ids",
    "is_show_bulletin"
})

public class Weibo implements Parcelable {
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
  @JsonProperty("pic_urls") private List<Object> picUrls = null;
  @JsonProperty("thumbnail_pic") private String thumbnailPic;
  @JsonProperty("bmiddle_pic") private String bmiddlePic;
  @JsonProperty("original_pic") private String originalPic;
  @JsonProperty("geo") private Geography geo;
  @JsonProperty("user") private User user;
  @JsonProperty("reposts_count") private Long repostsCount;
  @JsonProperty("comments_count") private Long commentsCount;
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

  @JsonProperty("pic_urls") public List<Object> getPicUrls() {
    return picUrls;
  }

  @JsonProperty("pic_urls") public void setPicUrls(List<Object> picUrls) {
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

  protected Weibo(Parcel in) {
    createdAt = in.readString();
    id = in.readByte() == 0x00 ? null : in.readLong();
    mid = in.readString();
    idstr = in.readString();
    text = in.readString();
    textLength = in.readByte() == 0x00 ? null : in.readLong();
    sourceAllowclick = in.readByte() == 0x00 ? null : in.readLong();
    sourceType = in.readByte() == 0x00 ? null : in.readLong();
    source = in.readString();
    byte favoritedVal = in.readByte();
    favorited = favoritedVal == 0x02 ? null : favoritedVal != 0x00;
    byte truncatedVal = in.readByte();
    truncated = truncatedVal == 0x02 ? null : truncatedVal != 0x00;
    inReplyToStatusId = in.readString();
    inReplyToUserId = in.readString();
    inReplyToScreenName = in.readString();
    if (in.readByte() == 0x01) {
      picUrls = new ArrayList<Object>();
      in.readList(picUrls, Object.class.getClassLoader());
    } else {
      picUrls = null;
    }
    thumbnailPic = in.readString();
    bmiddlePic = in.readString();
    originalPic = in.readString();
    geo = (Geography) in.readValue(Geography.class.getClassLoader());
    user = (User) in.readValue(User.class.getClassLoader());
    repostsCount = in.readByte() == 0x00 ? null : in.readLong();
    commentsCount = in.readByte() == 0x00 ? null : in.readLong();
    attitudesCount = in.readByte() == 0x00 ? null : in.readLong();
    byte isLongTextVal = in.readByte();
    isLongText = isLongTextVal == 0x02 ? null : isLongTextVal != 0x00;
    mlevel = in.readByte() == 0x00 ? null : in.readLong();
    visible = (Visible) in.readValue(Visible.class.getClassLoader());
    bizFeature = in.readByte() == 0x00 ? null : in.readLong();
    pageType = in.readByte() == 0x00 ? null : in.readLong();
    hasActionTypeCard = in.readByte() == 0x00 ? null : in.readLong();
    if (in.readByte() == 0x01) {
      darwinTags = new ArrayList<>();
      in.readList(darwinTags, Object.class.getClassLoader());
    } else {
      darwinTags = null;
    }
    if (in.readByte() == 0x01) {
      hotWeiboTags = new ArrayList<>();
      in.readList(hotWeiboTags, Object.class.getClassLoader());
    } else {
      hotWeiboTags = null;
    }
    if (in.readByte() == 0x01) {
      textTagTips = new ArrayList<>();
      in.readList(textTagTips, Object.class.getClassLoader());
    } else {
      textTagTips = null;
    }
    rid = in.readString();
    userType = in.readByte() == 0x00 ? null : in.readLong();
    cardid = in.readString();
    positiveRecomFlag = in.readByte() == 0x00 ? null : in.readLong();
    gifIds = in.readString();
    isShowBulletin = in.readByte() == 0x00 ? null : in.readLong();
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(createdAt);
    if (id == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(id);
    }
    dest.writeString(mid);
    dest.writeString(idstr);
    dest.writeString(text);
    if (textLength == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(textLength);
    }
    if (sourceAllowclick == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(sourceAllowclick);
    }
    if (sourceType == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(sourceType);
    }
    dest.writeString(source);
    if (favorited == null) {
      dest.writeByte((byte) (0x02));
    } else {
      dest.writeByte((byte) (favorited ? 0x01 : 0x00));
    }
    if (truncated == null) {
      dest.writeByte((byte) (0x02));
    } else {
      dest.writeByte((byte) (truncated ? 0x01 : 0x00));
    }
    dest.writeString(inReplyToStatusId);
    dest.writeString(inReplyToUserId);
    dest.writeString(inReplyToScreenName);
    if (picUrls == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(picUrls);
    }
    dest.writeString(thumbnailPic);
    dest.writeString(bmiddlePic);
    dest.writeString(originalPic);
    dest.writeValue(geo);
    dest.writeValue(user);
    if (repostsCount == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(repostsCount);
    }
    if (commentsCount == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(commentsCount);
    }
    if (attitudesCount == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(attitudesCount);
    }
    if (isLongText == null) {
      dest.writeByte((byte) (0x02));
    } else {
      dest.writeByte((byte) (isLongText ? 0x01 : 0x00));
    }
    if (mlevel == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(mlevel);
    }
    dest.writeValue(visible);
    if (bizFeature == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(bizFeature);
    }
    if (pageType == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(pageType);
    }
    if (hasActionTypeCard == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(hasActionTypeCard);
    }
    if (darwinTags == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(darwinTags);
    }
    if (hotWeiboTags == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(hotWeiboTags);
    }
    if (textTagTips == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeList(textTagTips);
    }
    dest.writeString(rid);
    if (userType == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(userType);
    }
    dest.writeString(cardid);
    if (positiveRecomFlag == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(positiveRecomFlag);
    }
    dest.writeString(gifIds);
    if (isShowBulletin == null) {
      dest.writeByte((byte) (0x00));
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeLong(isShowBulletin);
    }
  }

  @SuppressWarnings("unused") public static final Parcelable.Creator<Weibo> CREATOR =
      new Parcelable.Creator<Weibo>() {
        @Override public Weibo createFromParcel(Parcel in) {
          return new Weibo(in);
        }

        @Override public Weibo[] newArray(int size) {
          return new Weibo[size];
        }
      };
}

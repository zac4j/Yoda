package com.zac4j.yoda.data.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
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

public class Weibo implements Serializable {
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("mid")
    private String mid;
    @JsonProperty("idstr")
    private String idstr;
    @JsonProperty("text")
    private String text;
    @JsonProperty("textLength")
    private Long textLength;
    @JsonProperty("source_allowclick")
    private Long sourceAllowclick;
    @JsonProperty("source_type")
    private Long sourceType;
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
    private List<ThumbUrl> picUrls = null;
    @JsonProperty("thumbnail_pic")
    private String thumbnailPic;
    @JsonProperty("bmiddle_pic")
    private String bmiddlePic;
    @JsonProperty("original_pic")
    private String originalPic;
    @JsonProperty("geo")
    private Geography geo;
    @JsonProperty("user")
    private User user;
    @JsonProperty("reposts_count")
    private Long repostsCount;
    @JsonProperty("comments_count")
    private Long commentsCount;
    @JsonProperty("retweeted_status")
    private Weibo repostWeibo;
    @JsonProperty("attitudes_count")
    private Long attitudesCount;
    @JsonProperty("isLongText")
    private Boolean isLongText;
    @JsonProperty("mlevel")
    private Long mlevel;
    @JsonProperty("visible")
    private Visible visible;
    @JsonProperty("biz_feature")
    private Long bizFeature;
    @JsonProperty("page_type")
    private Long pageType;
    @JsonProperty("hasActionTypeCard")
    private Long hasActionTypeCard;
    @JsonProperty("darwin_tags")
    private List<Object> darwinTags = null;
    @JsonProperty("hot_weibo_tags")
    private List<Object> hotWeiboTags = null;
    @JsonProperty("text_tag_tips")
    private List<Object> textTagTips = null;
    @JsonProperty("rid")
    private String rid;
    @JsonProperty("userType")
    private Long userType;
    @JsonProperty("cardid")
    private String cardid;
    @JsonProperty("positive_recom_flag")
    private Long positiveRecomFlag;
    @JsonProperty("gif_ids")
    private String gifIds;
    @JsonProperty("is_show_bulletin")
    private Long isShowBulletin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
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
    public Long getTextLength() {
        return textLength;
    }

    @JsonProperty("textLength")
    public void setTextLength(Long textLength) {
        this.textLength = textLength;
    }

    @JsonProperty("source_allowclick")
    public Long getSourceAllowclick() {
        return sourceAllowclick;
    }

    @JsonProperty("source_allowclick")
    public void setSourceAllowclick(Long sourceAllowclick) {
        this.sourceAllowclick = sourceAllowclick;
    }

    @JsonProperty("source_type")
    public Long getSourceType() {
        return sourceType;
    }

    @JsonProperty("source_type")
    public void setSourceType(Long sourceType) {
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
    public List<ThumbUrl> getPicUrls() {
        return picUrls;
    }

    @JsonProperty("pic_urls")
    public void setPicUrls(List<ThumbUrl> picUrls) {
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
    public Geography getGeo() {
        return geo;
    }

    @JsonProperty("geo")
    public void setGeo(Geography geo) {
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
    public Long getRepostsCount() {
        return repostsCount;
    }

    @JsonProperty("reposts_count")
    public void setRepostsCount(Long repostsCount) {
        this.repostsCount = repostsCount;
    }

    @JsonProperty("retweeted_status")
    public Weibo getRepostWeibo() {
        return repostWeibo;
    }

    @JsonProperty("retweeted_status")
    public void setRepostWeibo(Weibo repostWeibo) {
        this.repostWeibo = repostWeibo;
    }

    @JsonProperty("comments_count")
    public Long getCommentsCount() {
        return commentsCount;
    }

    @JsonProperty("comments_count")
    public void setCommentsCount(Long commentsCount) {
        this.commentsCount = commentsCount;
    }

    @JsonProperty("attitudes_count")
    public Long getAttitudesCount() {
        return attitudesCount;
    }

    @JsonProperty("attitudes_count")
    public void setAttitudesCount(Long attitudesCount) {
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
    public Long getMlevel() {
        return mlevel;
    }

    @JsonProperty("mlevel")
    public void setMlevel(Long mlevel) {
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
    public Long getBizFeature() {
        return bizFeature;
    }

    @JsonProperty("biz_feature")
    public void setBizFeature(Long bizFeature) {
        this.bizFeature = bizFeature;
    }

    @JsonProperty("page_type")
    public Long getPageType() {
        return pageType;
    }

    @JsonProperty("page_type")
    public void setPageType(Long pageType) {
        this.pageType = pageType;
    }

    @JsonProperty("hasActionTypeCard")
    public Long getHasActionTypeCard() {
        return hasActionTypeCard;
    }

    @JsonProperty("hasActionTypeCard")
    public void setHasActionTypeCard(Long hasActionTypeCard) {
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
    public Long getUserType() {
        return userType;
    }

    @JsonProperty("userType")
    public void setUserType(Long userType) {
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
    public Long getPositiveRecomFlag() {
        return positiveRecomFlag;
    }

    @JsonProperty("positive_recom_flag")
    public void setPositiveRecomFlag(Long positiveRecomFlag) {
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
    public Long getIsShowBulletin() {
        return isShowBulletin;
    }

    @JsonProperty("is_show_bulletin")
    public void setIsShowBulletin(Long isShowBulletin) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weibo weibo = (Weibo) o;
        return id != null ? id.equals(weibo.id) : weibo.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idstr != null ? idstr.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Weibo{"
            + "createdAt='"
            + createdAt
            + '\''
            + ", id="
            + id
            + ", mid='"
            + mid
            + '\''
            + ", idstr='"
            + idstr
            + '\''
            + ", text='"
            + text
            + '\''
            + ", textLength="
            + textLength
            + ", sourceAllowclick="
            + sourceAllowclick
            + ", sourceType="
            + sourceType
            + ", source='"
            + source
            + '\''
            + ", favorited="
            + favorited
            + ", truncated="
            + truncated
            + ", inReplyToStatusId='"
            + inReplyToStatusId
            + '\''
            + ", inReplyToUserId='"
            + inReplyToUserId
            + '\''
            + ", inReplyToScreenName='"
            + inReplyToScreenName
            + '\''
            + ", picUrls="
            + picUrls
            + ", thumbnailPic='"
            + thumbnailPic
            + '\''
            + ", bmiddlePic='"
            + bmiddlePic
            + '\''
            + ", originalPic='"
            + originalPic
            + '\''
            + ", geo="
            + geo
            + ", user="
            + user
            + ", repostsCount="
            + repostsCount
            + ", commentsCount="
            + commentsCount
            + ", repostWeibo="
            + repostWeibo
            + ", attitudesCount="
            + attitudesCount
            + ", isLongText="
            + isLongText
            + ", mlevel="
            + mlevel
            + ", visible="
            + visible
            + ", bizFeature="
            + bizFeature
            + ", pageType="
            + pageType
            + ", hasActionTypeCard="
            + hasActionTypeCard
            + ", darwinTags="
            + darwinTags
            + ", hotWeiboTags="
            + hotWeiboTags
            + ", textTagTips="
            + textTagTips
            + ", rid='"
            + rid
            + '\''
            + ", userType="
            + userType
            + ", cardid='"
            + cardid
            + '\''
            + ", positiveRecomFlag="
            + positiveRecomFlag
            + ", gifIds='"
            + gifIds
            + '\''
            + ", isShowBulletin="
            + isShowBulletin
            + ", additionalProperties="
            + additionalProperties
            + '}';
    }
}

package com.zac4j.yoda.data.model;

import android.arch.persistence.room.Entity;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * User Model
 * Created by zac on 3/21/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({
    "id", "idstr", "class", "screen_name", "name", "province", "city", "location", "description",
    "url", "profile_image_url", "cover_image", "cover_image_phone", "profile_url", "domain",
    "weihao", "gender", "followers_count", "friends_count", "pagefriends_count", "statuses_count",
    "favourites_count", "created_at", "following", "allow_all_act_msg", "geo_enabled", "verified",
    "verified_type", "remark", "insecurity", "ptype", "allow_all_comment", "avatar_large",
    "avatar_hd", "verified_reason", "verified_trade", "verified_reason_url", "verified_source",
    "verified_source_url", "verified_state", "verified_level", "verified_type_ext", "pay_remind",
    "pay_date", "has_service_tel", "verified_reason_modified", "verified_contact_name",
    "verified_contact_email", "verified_contact_mobile", "follow_me", "online_status",
    "bi_followers_count", "lang", "star", "mbtype", "mbrank", "block_word", "block_app",
    "credit_score", "user_ability", "cardid", "urank", "action_log"
}) @Entity(tableName = "users") public class User implements Serializable{

  @JsonProperty("id") private Long id;
  @JsonProperty("idstr") private String idstr;
  @JsonProperty("class") private Integer _class;
  @JsonProperty("screen_name") private String screenName;
  @JsonProperty("name") private String name;
  @JsonProperty("province") private String province;
  @JsonProperty("city") private String city;
  @JsonProperty("location") private String location;
  @JsonProperty("description") private String description;
  @JsonProperty("url") private String url;
  @JsonProperty("profile_image_url") private String profileImageUrl;
  @JsonProperty("cover_image") private String coverImage;
  @JsonProperty("cover_image_phone") private String coverImagePhone;
  @JsonProperty("profile_url") private String profileUrl;
  @JsonProperty("domain") private String domain;
  @JsonProperty("weihao") private String weihao;
  @JsonProperty("gender") private String gender;
  @JsonProperty("followers_count") private Integer followersCount;
  @JsonProperty("friends_count") private Integer friendsCount;
  @JsonProperty("pagefriends_count") private Integer pagefriendsCount;
  @JsonProperty("statuses_count") private Integer statusesCount;
  @JsonProperty("favourites_count") private Integer favouritesCount;
  @JsonProperty("created_at") private String createdAt;
  @JsonProperty("following") private Boolean following;
  @JsonProperty("allow_all_act_msg") private Boolean allowAllActMsg;
  @JsonProperty("geo_enabled") private Boolean geoEnabled;
  @JsonProperty("verified") private Boolean verified;
  @JsonProperty("verified_type") private Integer verifiedType;
  @JsonProperty("remark") private String remark;
  @JsonProperty("insecurity") private Insecurity insecurity;
  @JsonProperty("ptype") private Integer ptype;
  @JsonProperty("allow_all_comment") private Boolean allowAllComment;
  @JsonProperty("avatar_large") private String avatarLarge;
  @JsonProperty("avatar_hd") private String avatarHd;
  @JsonProperty("verified_reason") private String verifiedReason;
  @JsonProperty("verified_trade") private String verifiedTrade;
  @JsonProperty("verified_reason_url") private String verifiedReasonUrl;
  @JsonProperty("verified_source") private String verifiedSource;
  @JsonProperty("verified_source_url") private String verifiedSourceUrl;
  @JsonProperty("verified_state") private Integer verifiedState;
  @JsonProperty("verified_level") private Integer verifiedLevel;
  @JsonProperty("verified_type_ext") private Integer verifiedTypeExt;
  @JsonProperty("pay_remind") private Integer payRemind;
  @JsonProperty("pay_date") private String payDate;
  @JsonProperty("has_service_tel") private Boolean hasServiceTel;
  @JsonProperty("verified_reason_modified") private String verifiedReasonModified;
  @JsonProperty("verified_contact_name") private String verifiedContactName;
  @JsonProperty("verified_contact_email") private String verifiedContactEmail;
  @JsonProperty("verified_contact_mobile") private String verifiedContactMobile;
  @JsonProperty("follow_me") private Boolean followMe;
  @JsonProperty("online_status") private Integer onlineStatus;
  @JsonProperty("bi_followers_count") private Integer biFollowersCount;
  @JsonProperty("lang") private String lang;
  @JsonProperty("star") private Integer star;
  @JsonProperty("mbtype") private Integer mbtype;
  @JsonProperty("mbrank") private Integer mbrank;
  @JsonProperty("block_word") private Integer blockWord;
  @JsonProperty("block_app") private Integer blockApp;
  @JsonProperty("credit_score") private Integer creditScore;
  @JsonProperty("user_ability") private Integer userAbility;
  @JsonProperty("cardid") private String cardid;
  @JsonProperty("urank") private Integer urank;
  @JsonProperty("action_log") private ActionLog actionLog;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<>();

  @JsonProperty("id") public Long getId() {
    return id;
  }

  @JsonProperty("id") public void setId(Long id) {
    this.id = id;
  }

  @JsonProperty("idstr") public String getIdstr() {
    return idstr;
  }

  @JsonProperty("idstr") public void setIdstr(String idstr) {
    this.idstr = idstr;
  }

  @JsonProperty("class") public Integer getClass_() {
    return _class;
  }

  @JsonProperty("class") public void setClass_(Integer _class) {
    this._class = _class;
  }

  @JsonProperty("screen_name") public String getScreenName() {
    return screenName;
  }

  @JsonProperty("screen_name") public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  @JsonProperty("name") public String getName() {
    return name;
  }

  @JsonProperty("name") public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("province") public String getProvince() {
    return province;
  }

  @JsonProperty("province") public void setProvince(String province) {
    this.province = province;
  }

  @JsonProperty("city") public String getCity() {
    return city;
  }

  @JsonProperty("city") public void setCity(String city) {
    this.city = city;
  }

  @JsonProperty("location") public String getLocation() {
    return location;
  }

  @JsonProperty("location") public void setLocation(String location) {
    this.location = location;
  }

  @JsonProperty("description") public String getDescription() {
    return description;
  }

  @JsonProperty("description") public void setDescription(String description) {
    this.description = description;
  }

  @JsonProperty("url") public String getUrl() {
    return url;
  }

  @JsonProperty("url") public void setUrl(String url) {
    this.url = url;
  }

  @JsonProperty("profile_image_url") public String getProfileImageUrl() {
    return profileImageUrl;
  }

  @JsonProperty("profile_image_url") public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  @JsonProperty("cover_image") public String getCoverImage() {
    return coverImage;
  }

  @JsonProperty("cover_image") public void setCoverImage(String coverImage) {
    this.coverImage = coverImage;
  }

  @JsonProperty("cover_image_phone") public String getCoverImagePhone() {
    return coverImagePhone;
  }

  @JsonProperty("cover_image_phone") public void setCoverImagePhone(String coverImagePhone) {
    this.coverImagePhone = coverImagePhone;
  }

  @JsonProperty("profile_url") public String getProfileUrl() {
    return profileUrl;
  }

  @JsonProperty("profile_url") public void setProfileUrl(String profileUrl) {
    this.profileUrl = profileUrl;
  }

  @JsonProperty("domain") public String getDomain() {
    return domain;
  }

  @JsonProperty("domain") public void setDomain(String domain) {
    this.domain = domain;
  }

  @JsonProperty("weihao") public String getWeihao() {
    return weihao;
  }

  @JsonProperty("weihao") public void setWeihao(String weihao) {
    this.weihao = weihao;
  }

  @JsonProperty("gender") public String getGender() {
    return gender;
  }

  @JsonProperty("gender") public void setGender(String gender) {
    this.gender = gender;
  }

  @JsonProperty("followers_count") public Integer getFollowersCount() {
    return followersCount;
  }

  @JsonProperty("followers_count") public void setFollowersCount(Integer followersCount) {
    this.followersCount = followersCount;
  }

  @JsonProperty("friends_count") public Integer getFriendsCount() {
    return friendsCount;
  }

  @JsonProperty("friends_count") public void setFriendsCount(Integer friendsCount) {
    this.friendsCount = friendsCount;
  }

  @JsonProperty("pagefriends_count") public Integer getPagefriendsCount() {
    return pagefriendsCount;
  }

  @JsonProperty("pagefriends_count") public void setPagefriendsCount(Integer pagefriendsCount) {
    this.pagefriendsCount = pagefriendsCount;
  }

  @JsonProperty("statuses_count") public Integer getStatusesCount() {
    return statusesCount;
  }

  @JsonProperty("statuses_count") public void setStatusesCount(Integer statusesCount) {
    this.statusesCount = statusesCount;
  }

  @JsonProperty("favourites_count") public Integer getFavouritesCount() {
    return favouritesCount;
  }

  @JsonProperty("favourites_count") public void setFavouritesCount(Integer favouritesCount) {
    this.favouritesCount = favouritesCount;
  }

  @JsonProperty("created_at") public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("created_at") public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("following") public Boolean getFollowing() {
    return following;
  }

  @JsonProperty("following") public void setFollowing(Boolean following) {
    this.following = following;
  }

  @JsonProperty("allow_all_act_msg") public Boolean getAllowAllActMsg() {
    return allowAllActMsg;
  }

  @JsonProperty("allow_all_act_msg") public void setAllowAllActMsg(Boolean allowAllActMsg) {
    this.allowAllActMsg = allowAllActMsg;
  }

  @JsonProperty("geo_enabled") public Boolean getGeoEnabled() {
    return geoEnabled;
  }

  @JsonProperty("geo_enabled") public void setGeoEnabled(Boolean geoEnabled) {
    this.geoEnabled = geoEnabled;
  }

  @JsonProperty("verified") public Boolean getVerified() {
    return verified;
  }

  @JsonProperty("verified") public void setVerified(Boolean verified) {
    this.verified = verified;
  }

  @JsonProperty("verified_type") public Integer getVerifiedType() {
    return verifiedType;
  }

  @JsonProperty("verified_type") public void setVerifiedType(Integer verifiedType) {
    this.verifiedType = verifiedType;
  }

  @JsonProperty("remark") public String getRemark() {
    return remark;
  }

  @JsonProperty("remark") public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty("insecurity") public Insecurity getInsecurity() {
    return insecurity;
  }

  @JsonProperty("insecurity") public void setInsecurity(Insecurity insecurity) {
    this.insecurity = insecurity;
  }

  @JsonProperty("ptype") public Integer getPtype() {
    return ptype;
  }

  @JsonProperty("ptype") public void setPtype(Integer ptype) {
    this.ptype = ptype;
  }

  @JsonProperty("allow_all_comment") public Boolean getAllowAllComment() {
    return allowAllComment;
  }

  @JsonProperty("allow_all_comment") public void setAllowAllComment(Boolean allowAllComment) {
    this.allowAllComment = allowAllComment;
  }

  @JsonProperty("avatar_large") public String getAvatarLarge() {
    return avatarLarge;
  }

  @JsonProperty("avatar_large") public void setAvatarLarge(String avatarLarge) {
    this.avatarLarge = avatarLarge;
  }

  @JsonProperty("avatar_hd") public String getAvatarHd() {
    return avatarHd;
  }

  @JsonProperty("avatar_hd") public void setAvatarHd(String avatarHd) {
    this.avatarHd = avatarHd;
  }

  @JsonProperty("verified_reason") public String getVerifiedReason() {
    return verifiedReason;
  }

  @JsonProperty("verified_reason") public void setVerifiedReason(String verifiedReason) {
    this.verifiedReason = verifiedReason;
  }

  @JsonProperty("verified_trade") public String getVerifiedTrade() {
    return verifiedTrade;
  }

  @JsonProperty("verified_trade") public void setVerifiedTrade(String verifiedTrade) {
    this.verifiedTrade = verifiedTrade;
  }

  @JsonProperty("verified_reason_url") public String getVerifiedReasonUrl() {
    return verifiedReasonUrl;
  }

  @JsonProperty("verified_reason_url") public void setVerifiedReasonUrl(String verifiedReasonUrl) {
    this.verifiedReasonUrl = verifiedReasonUrl;
  }

  @JsonProperty("verified_source") public String getVerifiedSource() {
    return verifiedSource;
  }

  @JsonProperty("verified_source") public void setVerifiedSource(String verifiedSource) {
    this.verifiedSource = verifiedSource;
  }

  @JsonProperty("verified_source_url") public String getVerifiedSourceUrl() {
    return verifiedSourceUrl;
  }

  @JsonProperty("verified_source_url") public void setVerifiedSourceUrl(String verifiedSourceUrl) {
    this.verifiedSourceUrl = verifiedSourceUrl;
  }

  @JsonProperty("verified_state") public Integer getVerifiedState() {
    return verifiedState;
  }

  @JsonProperty("verified_state") public void setVerifiedState(Integer verifiedState) {
    this.verifiedState = verifiedState;
  }

  @JsonProperty("verified_level") public Integer getVerifiedLevel() {
    return verifiedLevel;
  }

  @JsonProperty("verified_level") public void setVerifiedLevel(Integer verifiedLevel) {
    this.verifiedLevel = verifiedLevel;
  }

  @JsonProperty("verified_type_ext") public Integer getVerifiedTypeExt() {
    return verifiedTypeExt;
  }

  @JsonProperty("verified_type_ext") public void setVerifiedTypeExt(Integer verifiedTypeExt) {
    this.verifiedTypeExt = verifiedTypeExt;
  }

  @JsonProperty("pay_remind") public Integer getPayRemind() {
    return payRemind;
  }

  @JsonProperty("pay_remind") public void setPayRemind(Integer payRemind) {
    this.payRemind = payRemind;
  }

  @JsonProperty("pay_date") public String getPayDate() {
    return payDate;
  }

  @JsonProperty("pay_date") public void setPayDate(String payDate) {
    this.payDate = payDate;
  }

  @JsonProperty("has_service_tel") public Boolean getHasServiceTel() {
    return hasServiceTel;
  }

  @JsonProperty("has_service_tel") public void setHasServiceTel(Boolean hasServiceTel) {
    this.hasServiceTel = hasServiceTel;
  }

  @JsonProperty("verified_reason_modified") public String getVerifiedReasonModified() {
    return verifiedReasonModified;
  }

  @JsonProperty("verified_reason_modified")
  public void setVerifiedReasonModified(String verifiedReasonModified) {
    this.verifiedReasonModified = verifiedReasonModified;
  }

  @JsonProperty("verified_contact_name") public String getVerifiedContactName() {
    return verifiedContactName;
  }

  @JsonProperty("verified_contact_name")
  public void setVerifiedContactName(String verifiedContactName) {
    this.verifiedContactName = verifiedContactName;
  }

  @JsonProperty("verified_contact_email") public String getVerifiedContactEmail() {
    return verifiedContactEmail;
  }

  @JsonProperty("verified_contact_email")
  public void setVerifiedContactEmail(String verifiedContactEmail) {
    this.verifiedContactEmail = verifiedContactEmail;
  }

  @JsonProperty("verified_contact_mobile") public String getVerifiedContactMobile() {
    return verifiedContactMobile;
  }

  @JsonProperty("verified_contact_mobile")
  public void setVerifiedContactMobile(String verifiedContactMobile) {
    this.verifiedContactMobile = verifiedContactMobile;
  }

  @JsonProperty("follow_me") public Boolean getFollowMe() {
    return followMe;
  }

  @JsonProperty("follow_me") public void setFollowMe(Boolean followMe) {
    this.followMe = followMe;
  }

  @JsonProperty("online_status") public Integer getOnlineStatus() {
    return onlineStatus;
  }

  @JsonProperty("online_status") public void setOnlineStatus(Integer onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  @JsonProperty("bi_followers_count") public Integer getBiFollowersCount() {
    return biFollowersCount;
  }

  @JsonProperty("bi_followers_count") public void setBiFollowersCount(Integer biFollowersCount) {
    this.biFollowersCount = biFollowersCount;
  }

  @JsonProperty("lang") public String getLang() {
    return lang;
  }

  @JsonProperty("lang") public void setLang(String lang) {
    this.lang = lang;
  }

  @JsonProperty("star") public Integer getStar() {
    return star;
  }

  @JsonProperty("star") public void setStar(Integer star) {
    this.star = star;
  }

  @JsonProperty("mbtype") public Integer getMbtype() {
    return mbtype;
  }

  @JsonProperty("mbtype") public void setMbtype(Integer mbtype) {
    this.mbtype = mbtype;
  }

  @JsonProperty("mbrank") public Integer getMbrank() {
    return mbrank;
  }

  @JsonProperty("mbrank") public void setMbrank(Integer mbrank) {
    this.mbrank = mbrank;
  }

  @JsonProperty("block_word") public Integer getBlockWord() {
    return blockWord;
  }

  @JsonProperty("block_word") public void setBlockWord(Integer blockWord) {
    this.blockWord = blockWord;
  }

  @JsonProperty("block_app") public Integer getBlockApp() {
    return blockApp;
  }

  @JsonProperty("block_app") public void setBlockApp(Integer blockApp) {
    this.blockApp = blockApp;
  }

  @JsonProperty("credit_score") public Integer getCreditScore() {
    return creditScore;
  }

  @JsonProperty("credit_score") public void setCreditScore(Integer creditScore) {
    this.creditScore = creditScore;
  }

  @JsonProperty("user_ability") public Integer getUserAbility() {
    return userAbility;
  }

  @JsonProperty("user_ability") public void setUserAbility(Integer userAbility) {
    this.userAbility = userAbility;
  }

  @JsonProperty("cardid") public String getCardid() {
    return cardid;
  }

  @JsonProperty("cardid") public void setCardid(String cardid) {
    this.cardid = cardid;
  }

  @JsonProperty("urank") public Integer getUrank() {
    return urank;
  }

  @JsonProperty("urank") public void setUrank(Integer urank) {
    this.urank = urank;
  }

  @JsonProperty("action_log") public ActionLog getActionLog() {
    return actionLog;
  }

  @JsonProperty("action_log") public void setActionLog(ActionLog actionLog) {
    this.actionLog = actionLog;
  }

  @JsonAnyGetter public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }
}

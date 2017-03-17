package com.zac4j.yoda.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * User Model
 * Created by zac on 3/17/2017.
 */

public class User {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("idstr")
  @Expose
  private String idstr;
  @SerializedName("class")
  @Expose
  private Integer _class;
  @SerializedName("screen_name")
  @Expose
  private String screenName;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("province")
  @Expose
  private String province;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("location")
  @Expose
  private String location;
  @SerializedName("description")
  @Expose
  private String description;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("profile_image_url")
  @Expose
  private String profileImageUrl;
  @SerializedName("cover_image")
  @Expose
  private String coverImage;
  @SerializedName("cover_image_phone")
  @Expose
  private String coverImagePhone;
  @SerializedName("profile_url")
  @Expose
  private String profileUrl;
  @SerializedName("domain")
  @Expose
  private String domain;
  @SerializedName("weihao")
  @Expose
  private String weihao;
  @SerializedName("gender")
  @Expose
  private String gender;
  @SerializedName("followers_count")
  @Expose
  private Integer followersCount;
  @SerializedName("friends_count")
  @Expose
  private Integer friendsCount;
  @SerializedName("pagefriends_count")
  @Expose
  private Integer pagefriendsCount;
  @SerializedName("statuses_count")
  @Expose
  private Integer statusesCount;
  @SerializedName("favourites_count")
  @Expose
  private Integer favouritesCount;
  @SerializedName("created_at")
  @Expose
  private String createdAt;
  @SerializedName("following")
  @Expose
  private Boolean following;
  @SerializedName("allow_all_act_msg")
  @Expose
  private Boolean allowAllActMsg;
  @SerializedName("geo_enabled")
  @Expose
  private Boolean geoEnabled;
  @SerializedName("verified")
  @Expose
  private Boolean verified;
  @SerializedName("verified_type")
  @Expose
  private Integer verifiedType;
  @SerializedName("remark")
  @Expose
  private String remark;
  @SerializedName("insecurity")
  @Expose
  private Insecurity insecurity;
  @SerializedName("ptype")
  @Expose
  private Integer ptype;
  @SerializedName("allow_all_comment")
  @Expose
  private Boolean allowAllComment;
  @SerializedName("avatar_large")
  @Expose
  private String avatarLarge;
  @SerializedName("avatar_hd")
  @Expose
  private String avatarHd;
  @SerializedName("verified_reason")
  @Expose
  private String verifiedReason;
  @SerializedName("verified_trade")
  @Expose
  private String verifiedTrade;
  @SerializedName("verified_reason_url")
  @Expose
  private String verifiedReasonUrl;
  @SerializedName("verified_source")
  @Expose
  private String verifiedSource;
  @SerializedName("verified_source_url")
  @Expose
  private String verifiedSourceUrl;
  @SerializedName("verified_state")
  @Expose
  private Integer verifiedState;
  @SerializedName("verified_level")
  @Expose
  private Integer verifiedLevel;
  @SerializedName("verified_type_ext")
  @Expose
  private Integer verifiedTypeExt;
  @SerializedName("pay_remind")
  @Expose
  private Integer payRemind;
  @SerializedName("pay_date")
  @Expose
  private String payDate;
  @SerializedName("has_service_tel")
  @Expose
  private Boolean hasServiceTel;
  @SerializedName("verified_reason_modified")
  @Expose
  private String verifiedReasonModified;
  @SerializedName("verified_contact_name")
  @Expose
  private String verifiedContactName;
  @SerializedName("verified_contact_email")
  @Expose
  private String verifiedContactEmail;
  @SerializedName("verified_contact_mobile")
  @Expose
  private String verifiedContactMobile;
  @SerializedName("follow_me")
  @Expose
  private Boolean followMe;
  @SerializedName("online_status")
  @Expose
  private Integer onlineStatus;
  @SerializedName("bi_followers_count")
  @Expose
  private Integer biFollowersCount;
  @SerializedName("lang")
  @Expose
  private String lang;
  @SerializedName("star")
  @Expose
  private Integer star;
  @SerializedName("mbtype")
  @Expose
  private Integer mbtype;
  @SerializedName("mbrank")
  @Expose
  private Integer mbrank;
  @SerializedName("block_word")
  @Expose
  private Integer blockWord;
  @SerializedName("block_app")
  @Expose
  private Integer blockApp;
  @SerializedName("credit_score")
  @Expose
  private Integer creditScore;
  @SerializedName("user_ability")
  @Expose
  private Integer userAbility;
  @SerializedName("cardid")
  @Expose
  private String cardid;
  @SerializedName("urank")
  @Expose
  private Integer urank;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdstr() {
    return idstr;
  }

  public void setIdstr(String idstr) {
    this.idstr = idstr;
  }

  public Integer getClass_() {
    return _class;
  }

  public void setClass_(Integer _class) {
    this._class = _class;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getProfileImageUrl() {
    return profileImageUrl;
  }

  public void setProfileImageUrl(String profileImageUrl) {
    this.profileImageUrl = profileImageUrl;
  }

  public String getCoverImage() {
    return coverImage;
  }

  public void setCoverImage(String coverImage) {
    this.coverImage = coverImage;
  }

  public String getCoverImagePhone() {
    return coverImagePhone;
  }

  public void setCoverImagePhone(String coverImagePhone) {
    this.coverImagePhone = coverImagePhone;
  }

  public String getProfileUrl() {
    return profileUrl;
  }

  public void setProfileUrl(String profileUrl) {
    this.profileUrl = profileUrl;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getWeihao() {
    return weihao;
  }

  public void setWeihao(String weihao) {
    this.weihao = weihao;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Integer getFollowersCount() {
    return followersCount;
  }

  public void setFollowersCount(Integer followersCount) {
    this.followersCount = followersCount;
  }

  public Integer getFriendsCount() {
    return friendsCount;
  }

  public void setFriendsCount(Integer friendsCount) {
    this.friendsCount = friendsCount;
  }

  public Integer getPagefriendsCount() {
    return pagefriendsCount;
  }

  public void setPagefriendsCount(Integer pagefriendsCount) {
    this.pagefriendsCount = pagefriendsCount;
  }

  public Integer getStatusesCount() {
    return statusesCount;
  }

  public void setStatusesCount(Integer statusesCount) {
    this.statusesCount = statusesCount;
  }

  public Integer getFavouritesCount() {
    return favouritesCount;
  }

  public void setFavouritesCount(Integer favouritesCount) {
    this.favouritesCount = favouritesCount;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public Boolean getFollowing() {
    return following;
  }

  public void setFollowing(Boolean following) {
    this.following = following;
  }

  public Boolean getAllowAllActMsg() {
    return allowAllActMsg;
  }

  public void setAllowAllActMsg(Boolean allowAllActMsg) {
    this.allowAllActMsg = allowAllActMsg;
  }

  public Boolean getGeoEnabled() {
    return geoEnabled;
  }

  public void setGeoEnabled(Boolean geoEnabled) {
    this.geoEnabled = geoEnabled;
  }

  public Boolean getVerified() {
    return verified;
  }

  public void setVerified(Boolean verified) {
    this.verified = verified;
  }

  public Integer getVerifiedType() {
    return verifiedType;
  }

  public void setVerifiedType(Integer verifiedType) {
    this.verifiedType = verifiedType;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Insecurity getInsecurity() {
    return insecurity;
  }

  public void setInsecurity(Insecurity insecurity) {
    this.insecurity = insecurity;
  }

  public Integer getPtype() {
    return ptype;
  }

  public void setPtype(Integer ptype) {
    this.ptype = ptype;
  }

  public Boolean getAllowAllComment() {
    return allowAllComment;
  }

  public void setAllowAllComment(Boolean allowAllComment) {
    this.allowAllComment = allowAllComment;
  }

  public String getAvatarLarge() {
    return avatarLarge;
  }

  public void setAvatarLarge(String avatarLarge) {
    this.avatarLarge = avatarLarge;
  }

  public String getAvatarHd() {
    return avatarHd;
  }

  public void setAvatarHd(String avatarHd) {
    this.avatarHd = avatarHd;
  }

  public String getVerifiedReason() {
    return verifiedReason;
  }

  public void setVerifiedReason(String verifiedReason) {
    this.verifiedReason = verifiedReason;
  }

  public String getVerifiedTrade() {
    return verifiedTrade;
  }

  public void setVerifiedTrade(String verifiedTrade) {
    this.verifiedTrade = verifiedTrade;
  }

  public String getVerifiedReasonUrl() {
    return verifiedReasonUrl;
  }

  public void setVerifiedReasonUrl(String verifiedReasonUrl) {
    this.verifiedReasonUrl = verifiedReasonUrl;
  }

  public String getVerifiedSource() {
    return verifiedSource;
  }

  public void setVerifiedSource(String verifiedSource) {
    this.verifiedSource = verifiedSource;
  }

  public String getVerifiedSourceUrl() {
    return verifiedSourceUrl;
  }

  public void setVerifiedSourceUrl(String verifiedSourceUrl) {
    this.verifiedSourceUrl = verifiedSourceUrl;
  }

  public Integer getVerifiedState() {
    return verifiedState;
  }

  public void setVerifiedState(Integer verifiedState) {
    this.verifiedState = verifiedState;
  }

  public Integer getVerifiedLevel() {
    return verifiedLevel;
  }

  public void setVerifiedLevel(Integer verifiedLevel) {
    this.verifiedLevel = verifiedLevel;
  }

  public Integer getVerifiedTypeExt() {
    return verifiedTypeExt;
  }

  public void setVerifiedTypeExt(Integer verifiedTypeExt) {
    this.verifiedTypeExt = verifiedTypeExt;
  }

  public Integer getPayRemind() {
    return payRemind;
  }

  public void setPayRemind(Integer payRemind) {
    this.payRemind = payRemind;
  }

  public String getPayDate() {
    return payDate;
  }

  public void setPayDate(String payDate) {
    this.payDate = payDate;
  }

  public Boolean getHasServiceTel() {
    return hasServiceTel;
  }

  public void setHasServiceTel(Boolean hasServiceTel) {
    this.hasServiceTel = hasServiceTel;
  }

  public String getVerifiedReasonModified() {
    return verifiedReasonModified;
  }

  public void setVerifiedReasonModified(String verifiedReasonModified) {
    this.verifiedReasonModified = verifiedReasonModified;
  }

  public String getVerifiedContactName() {
    return verifiedContactName;
  }

  public void setVerifiedContactName(String verifiedContactName) {
    this.verifiedContactName = verifiedContactName;
  }

  public String getVerifiedContactEmail() {
    return verifiedContactEmail;
  }

  public void setVerifiedContactEmail(String verifiedContactEmail) {
    this.verifiedContactEmail = verifiedContactEmail;
  }

  public String getVerifiedContactMobile() {
    return verifiedContactMobile;
  }

  public void setVerifiedContactMobile(String verifiedContactMobile) {
    this.verifiedContactMobile = verifiedContactMobile;
  }

  public Boolean getFollowMe() {
    return followMe;
  }

  public void setFollowMe(Boolean followMe) {
    this.followMe = followMe;
  }

  public Integer getOnlineStatus() {
    return onlineStatus;
  }

  public void setOnlineStatus(Integer onlineStatus) {
    this.onlineStatus = onlineStatus;
  }

  public Integer getBiFollowersCount() {
    return biFollowersCount;
  }

  public void setBiFollowersCount(Integer biFollowersCount) {
    this.biFollowersCount = biFollowersCount;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public Integer getStar() {
    return star;
  }

  public void setStar(Integer star) {
    this.star = star;
  }

  public Integer getMbtype() {
    return mbtype;
  }

  public void setMbtype(Integer mbtype) {
    this.mbtype = mbtype;
  }

  public Integer getMbrank() {
    return mbrank;
  }

  public void setMbrank(Integer mbrank) {
    this.mbrank = mbrank;
  }

  public Integer getBlockWord() {
    return blockWord;
  }

  public void setBlockWord(Integer blockWord) {
    this.blockWord = blockWord;
  }

  public Integer getBlockApp() {
    return blockApp;
  }

  public void setBlockApp(Integer blockApp) {
    this.blockApp = blockApp;
  }

  public Integer getCreditScore() {
    return creditScore;
  }

  public void setCreditScore(Integer creditScore) {
    this.creditScore = creditScore;
  }

  public Integer getUserAbility() {
    return userAbility;
  }

  public void setUserAbility(Integer userAbility) {
    this.userAbility = userAbility;
  }

  public String getCardid() {
    return cardid;
  }

  public void setCardid(String cardid) {
    this.cardid = cardid;
  }

  public Integer getUrank() {
    return urank;
  }

  public void setUrank(Integer urank) {
    this.urank = urank;
  }

}
package com.zac4j.yoda.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.sqlbrite2.BriteDatabase;
import com.zac4j.yoda.data.local.PreferencesHelper;
import com.zac4j.yoda.data.remote.ApiServer;
import io.reactivex.Single;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Data access manager
 * Created by zac on 16-7-21.
 */

@Singleton public class DataManager {

  private ApiServer mApiServer;
  private PreferencesHelper mPrefsHelper;
  private ObjectMapper mObjectMapper;
  private BriteDatabase mDatabase;

  @Inject
  public DataManager(ApiServer apiServer, PreferencesHelper prefsHelper, ObjectMapper mapper,
      BriteDatabase database) {
    mApiServer = apiServer;
    mPrefsHelper = prefsHelper;
    mObjectMapper = mapper;
    mDatabase = database;
  }

  public PreferencesHelper getPrefsHelper() {
    return mPrefsHelper;
  }

  public ApiServer getApiServer() {
    return mApiServer;
  }

  public ObjectMapper getObjectMapper() {
    return mObjectMapper;
  }

  public BriteDatabase getDatabase() {
    return mDatabase;
  }

  public Single<Response<Object>> getHomeTimeline(String token, int count, int page) {
    return mApiServer.getTimeline("friends", token, count, page);
  }

  public Single<Response<Object>> getUserTimeline(String token, int count, int page) {
    return mApiServer.getTimeline("user", token, count, page);
  }

  public Single<Response<Object>> getUserProfile(String token, String uid) {
    return mApiServer.getUserProfile(token, uid);
  }

  public Single<Response<Object>> getWeiboById(String token, long id) {
    return mApiServer.getWeiboInfo(token, id);
  }

  public Single<Response<Object>> sendTextWeibo(Map<String, String> weibo) {
    return mApiServer.sendTextWeibo(weibo);
  }

  public Single<Response<Object>> sendPictureWeibo(Map<String, RequestBody> weibo,
      MultipartBody.Part image) {
    return mApiServer.sendPictureWeibo(weibo, image);
  }

  public Single<Response<Object>> repostWeibo(String token, String id, String status,
      int asComment) {
    return mApiServer.repostWeibo(token, id, status, asComment);
  }

  public Single<Response<Object>> unfollowFriend(String token, long uid) {
    return mApiServer.unfollowFriend(token, uid);
  }

  public Single<Response<Object>> search(String token, String keywords, int count, int page) {
    return mApiServer.search(token, keywords, count, page);
  }

  public Single<Response<Object>> getHotTags(String token) {
    return mApiServer.getHotTags(token);
  }

  public Single<Response<Object>> getUserFriends(String token, long uid, int count, int cursor) {
    return mApiServer.getFriends(token, uid, count, cursor);
  }

  public Single<Response<Object>> getFollowers(String token, String uid) {
    return mApiServer.getFollowers(token, uid);
  }

  public Single<Response<Object>> getComments(String token, int count, int page) {
    return mApiServer.getComments(token, count, page);
  }

  public Single<Response<Object>> getLatestComments(String token) {
    return mApiServer.getLatestComments(token);
  }
}

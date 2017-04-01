package com.zac4j.yoda.data;

import com.fasterxml.jackson.databind.ObjectMapper;
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

  @Inject
  public DataManager(ApiServer apiServer, PreferencesHelper prefsHelper, ObjectMapper mapper) {
    mApiServer = apiServer;
    mPrefsHelper = prefsHelper;
    mObjectMapper = mapper;
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

  public Single<Response<Object>> sendPictureWeibo(Map<String, RequestBody> weibo, MultipartBody.Part image) {
    return mApiServer.sendPictureWeibo(weibo, image);
  }
}

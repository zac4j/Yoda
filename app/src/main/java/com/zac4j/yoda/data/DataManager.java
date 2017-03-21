package com.zac4j.yoda.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.local.PreferencesHelper;
import com.zac4j.yoda.data.remote.ApiServer;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;
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

  public Single<Response<Object>> getTimeline(String token, int count, int page) {
    return mApiServer.getTimeline("friends", token, count, page);
  }
}

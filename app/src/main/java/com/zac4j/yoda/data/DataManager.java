package com.zac4j.yoda.data;

import com.zac4j.yoda.data.local.PreferencesHelper;
import com.zac4j.yoda.data.model.Timeline;
import com.zac4j.yoda.data.remote.ApiServer;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.http.Query;

/**
 * Data access manager
 * Created by zac on 16-7-21.
 */

@Singleton public class DataManager {

  private ApiServer mApiServer;
  private PreferencesHelper mPrefsHelper;

  @Inject public DataManager(ApiServer apiServer, PreferencesHelper prefsHelper) {
    mApiServer = apiServer;
    mPrefsHelper = prefsHelper;
  }

  public PreferencesHelper getPrefsHelper() {
    return mPrefsHelper;
  }

  public ApiServer getApiServer() {
    return mApiServer;
  }

  public Single<Timeline> getTimeline(String token, String count, String page) {
    return mApiServer.getTimeline("friends", token, count, page);
  }
}

package com.zac4j.yoda.di.module;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.zac4j.yoda.data.remote.ApiServer;
import com.zac4j.yoda.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Application Module
 * Created by zac on 16-7-3.
 */

@Module
public class ApplicationModule {

  private Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides Application provideApplication() {
    return mApplication;
  }

  @Provides @ApplicationContext Context provideContext() {
    return mApplication;
  }

  @Provides Gson provideGson() {
    return new Gson();
  }

  @Provides @Singleton ApiServer provideWebService() {
    return ApiServer.Factory.create();
  }

}

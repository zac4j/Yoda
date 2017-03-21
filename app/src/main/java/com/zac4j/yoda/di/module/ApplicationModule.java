package com.zac4j.yoda.di.module;

import android.app.Application;
import android.content.Context;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.remote.ApiServer;
import com.zac4j.yoda.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Application Module
 * Created by zac on 16-7-3.
 */

@Module public class ApplicationModule {

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

  @Provides ObjectMapper provideMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    return mapper;
  }

  @Provides @Singleton ApiServer provideWebService() {
    return ApiServer.Factory.create();
  }
}

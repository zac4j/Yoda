package com.zac4j.yoda.di.module;

import android.app.Application;
import android.content.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;
import com.zac4j.yoda.data.local.DatabaseHelper;
import com.zac4j.yoda.data.remote.ApiServer;
import com.zac4j.yoda.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Singleton;
import timber.log.Timber;

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
    return new ObjectMapper();
  }

  @Provides @Singleton ApiServer provideWebService() {
    return ApiServer.Factory.create(mApplication);
  }

  @Provides @Singleton SqlBrite provideSqlBrite() {
    return new SqlBrite.Builder().logger(new SqlBrite.Logger() {
      @Override public void log(String message) {
        Timber.tag("Database").v(message);
      }
    }).build();
  }

  @Provides @Singleton BriteDatabase provideDatabase(SqlBrite sqlBrite, DatabaseHelper helper) {
    BriteDatabase database = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
    database.setLoggingEnabled(true);
    return database;
  }
}

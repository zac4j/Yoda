package com.zac4j.yoda.di.component;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.local.DatabaseHelper;
import com.zac4j.yoda.data.local.PreferencesHelper;
import com.zac4j.yoda.data.remote.ApiServer;
import com.zac4j.yoda.di.ApplicationContext;
import com.zac4j.yoda.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Application Component
 * Created by zac on 16-7-3.
 */

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {
  @ApplicationContext Context context();

  PreferencesHelper prefsHelper();

  DatabaseHelper databaseHelper();

  SqlBrite sqlBrite();

  BriteDatabase database();

  ObjectMapper mapper();

  ApiServer webService();

  DataManager dataManager();
}

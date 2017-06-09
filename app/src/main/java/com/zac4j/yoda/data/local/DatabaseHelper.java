package com.zac4j.yoda.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.zac4j.yoda.data.model.db.Profile;
import com.zac4j.yoda.di.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Database Open Helper
 * Created by zac on 4/24/2017.
 */
@Singleton public class DatabaseHelper extends SQLiteOpenHelper {
  private static final String NAME = "yoda.db";
  private static final int VERSION = 1;

  private static final String CREATE_USER_PROFILE = "CREATE TABLE "
      + Profile.TABLE
      + "("
      + Profile.ID
      + " INTEGER NOT NULL PRIMARY KEY,"
      + Profile.UID
      + " INTEGER NOT NULL DEFAULT 0,"
      + Profile.NICKNAME
      + " TEXT NOT NULL,"
      + Profile.USERNAME
      + " TEXT NOT NULL,"
      + Profile.DESCRIPTION
      + " TEXT NOT NULL,"
      + Profile.AVATAR_URL
      + " TEXT NOT NULL,"
      + Profile.BG_URL
      + " TEXT NOT NULL,"
      + Profile.LINK
      + " TEXT NOT NULL,"
      + Profile.LOCATION
      + " TEXT NOT NULL,"
      + Profile.FOLLOW
      + " INTEGER NOT NULL DEFAULT 0,"
      + Profile.FOLLOWER
      + " INTEGER NOT NULL DEFAULT 0,"
      + ")";

  @Inject public DatabaseHelper(@ApplicationContext Context context) {
    super(context, NAME, null, VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_USER_PROFILE);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion != newVersion) {
      String sql = "DROP TABLE IF EXISTS " + Profile.TABLE;
      db.execSQL(sql);
      onCreate(db);
    }
  }
}

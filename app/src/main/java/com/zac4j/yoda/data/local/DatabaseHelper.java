package com.zac4j.yoda.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.zac4j.yoda.data.model.db.Friend;
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
      + Friend.TABLE
      + "("
      + Friend.ID
      + " INTEGER NOT NULL PRIMARY KEY,"
      + Friend.UID
      + " INTEGER NOT NULL DEFAULT 0,"
      + Friend.NICKNAME
      + " TEXT NOT NULL,"
      + Friend.USERNAME
      + " TEXT NOT NULL,"
      + Friend.DESCRIPTION
      + " TEXT NOT NULL,"
      + Friend.AVATAR_URL
      + " TEXT NOT NULL,"
      + Friend.BG_URL
      + " TEXT NOT NULL,"
      + Friend.LINK
      + " TEXT NOT NULL,"
      + Friend.LOCATION
      + " TEXT NOT NULL,"
      + Friend.FOLLOW
      + " INTEGER NOT NULL DEFAULT 0,"
      + Friend.FOLLOWER
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
      String sql = "DROP TABLE IF EXISTS " + Friend.TABLE;
      db.execSQL(sql);
      onCreate(db);
    }
  }
}

package com.zac4j.yoda.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.zac4j.yoda.di.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Database Open Helper
 * Created by zac on 4/24/2017.
 */
@Singleton public class DatabaseHelper extends SQLiteOpenHelper {

  // Database info
  private static final String DB_NAME = "yoda_data";
  private static final int DB_VERSION = 1;

  // Table name
  public static final String TB_USER = "tb_user";

  // User table columns
  public static final String KEY_ID = "id";
  public static final String KEY_UID = "uid";
  public static final String KEY_NICKNAME = "nickname";
  public static final String KEY_USERNAME = "username";
  public static final String KEY_AVATAR_URL = "avatar_url";
  public static final String KEY_BG_URL = "bg_url";
  public static final String KEY_LINK = "link";
  public static final String KEY_LOCATION = "location";
  public static final String KEY_FOLLOW = "follow";
  public static final String KEY_FOLLOWER = "follower";

  @Inject public DatabaseHelper(@ApplicationContext Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    String sql = "CREATE TABLE "
        + TB_USER
        + "("
        + KEY_ID
        + " INTEGER PRIMARY KEY, "
        + KEY_UID
        + " TEXT, "
        + KEY_NICKNAME
        + " TEXT, "
        + KEY_USERNAME
        + " TEXT, "
        + KEY_AVATAR_URL
        + " TEXT, "
        + KEY_BG_URL
        + " TEXT, "
        + KEY_LINK
        + " TEXT, "
        + KEY_LOCATION
        + " TEXT, "
        + KEY_FOLLOW
        + " TEXT, "
        + KEY_FOLLOWER
        + " TEXT"
        + ")";
    db.execSQL(sql);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion != newVersion) {
      String sql = "DROP TABLE IF EXISTS " + TB_USER;
      db.execSQL(sql);
      onCreate(db);
    }
  }
}

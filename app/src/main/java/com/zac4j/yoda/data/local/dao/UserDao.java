package com.zac4j.yoda.data.local.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.zac4j.yoda.data.local.DatabaseHelper;
import com.zac4j.yoda.data.model.User;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * User info data access object
 * Created by zac on 4/24/2017.
 */

public class UserDao {

  @Inject DatabaseHelper mDatabaseHelper;

  public void addUser(User user) {
    // Create and/or open the database for writing
    SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();

    db.beginTransaction();
    try {
      ContentValues values = new ContentValues();
      values.put(DatabaseHelper.KEY_UID, user.getIdstr());
      values.put(DatabaseHelper.KEY_NICKNAME, user.getScreenName());
      values.put(DatabaseHelper.KEY_USERNAME, user.getDomain());
      values.put(DatabaseHelper.KEY_AVATAR_URL, user.getProfileImageUrl());
      values.put(DatabaseHelper.KEY_BG_URL, user.getProfileUrl());
      values.put(DatabaseHelper.KEY_LINK, user.getIdstr());
      values.put(DatabaseHelper.KEY_UID, user.getIdstr());
      values.put(DatabaseHelper.KEY_UID, user.getIdstr());

      db.insertOrThrow(DatabaseHelper.TB_USER, null, values);
      db.setTransactionSuccessful();
    } catch (Exception e) {
      Timber.e(e);
    } finally {
      db.endTransaction();
    }
  }
}

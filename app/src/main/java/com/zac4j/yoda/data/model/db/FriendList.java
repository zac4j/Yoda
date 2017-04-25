package com.zac4j.yoda.data.model.db;

import android.database.Cursor;
import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.zac4j.yoda.data.local.Database;
import com.zac4j.yoda.data.model.Friend;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

/**
 * Model for Friend List
 * Created by zac on 4/25/2017.
 */
@AutoValue public abstract class FriendList implements Parcelable {

  public static final String TABLE = "friend_list";

  public static final String ID = "_id";
  public static final String UID = "uid";
  public static final String AVATAR_URL = "avatar_url";
  public static final String DESCRIPTION = "description";

  public abstract long id();

  public abstract long uid();

  public abstract String avatarUrl();

  public abstract String description();

  public static Function<Cursor, List<Friend>> MAP = new Function<Cursor, List<Friend>>() {
    @Override public List<Friend> apply(@NonNull Cursor cursor) throws Exception {
      //try {
      //  List<Friend> values = new ArrayList<>(cursor.getCount());
      //
      //  while (cursor.moveToNext()) {
      //    long id = Database.getLong(cursor, ID);
      //
      //  }
      //}

      return null;
    }
  };
}

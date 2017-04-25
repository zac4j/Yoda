package com.zac4j.yoda.data.model.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;
import com.google.auto.value.AutoValue;
import com.zac4j.yoda.data.local.Database;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * User Profile
 * Created by zac on 4/25/2017.
 */
@AutoValue public abstract class UserProfile implements Parcelable {

  // Table name
  public static final String TABLE = "user_profile";

  // User table columns
  public static final String ID = "id";
  public static final String UID = "uid";
  public static final String NICKNAME = "nickname";
  public static final String USERNAME = "username";
  public static final String DESCRIPTION = "description";
  public static final String AVATAR_URL = "avatar_url";
  public static final String BG_URL = "bg_url";
  public static final String LINK = "link";
  public static final String LOCATION = "location";
  public static final String FOLLOW = "follow";
  public static final String FOLLOWER = "follower";

  public abstract long id();

  public abstract long uid();

  public abstract String nickname();

  public abstract String username();

  public abstract String description();

  public abstract String avatarUrl();

  public abstract String backgroundUrl();

  public abstract String link();

  public abstract String location();

  public abstract long follow();

  public abstract long follower();

  public static final Function<Cursor, UserProfile> MAPPER = new Function<Cursor, UserProfile>() {
    @Override public UserProfile apply(@NonNull Cursor cursor) throws Exception {
      long id = Database.getLong(cursor, ID);
      long uid = Database.getLong(cursor, UID);
      String nickname = Database.getString(cursor, NICKNAME);
      String username = Database.getString(cursor, USERNAME);
      String description = Database.getString(cursor, DESCRIPTION);
      String avatarUrl = Database.getString(cursor, AVATAR_URL);
      String backgroundUrl = Database.getString(cursor, BG_URL);
      String link = Database.getString(cursor, LINK);
      String location = Database.getString(cursor, LOCATION);
      String follow = Database.getString(cursor, FOLLOW);
      String follower = Database.getString(cursor, FOLLOWER);
      return new AutoValue_UserProfile(id, uid, nickname, username, description, avatarUrl,
          backgroundUrl, link, location, follow, follower);
    }
  };

  public static final class Builder {
    private final ContentValues values = new ContentValues();

    public Builder id(long id) {
      values.put(ID, id);
      return this;
    }

    public Builder uid(long uid) {
      values.put(UID, uid);
      return this;
    }

    public Builder nickname(String nickname) {
      values.put(NICKNAME, nickname);
      return this;
    }

    public Builder username(String username) {
      values.put(USERNAME, username);
      return this;
    }

    public Builder description(String description) {
      values.put(DESCRIPTION, description);
      return this;
    }

    public Builder avatarUrl(String avatarUrl) {
      values.put(AVATAR_URL, avatarUrl);
      return this;
    }

    public Builder backgroundUrl(String backgroundUrl) {
      values.put(BG_URL, backgroundUrl);
      return this;
    }

    public Builder link(String link) {
      values.put(LINK, link);
      return this;
    }

    public Builder location(String location) {
      values.put(LOCATION, location);
      return this;
    }

    public Builder follow(String follow) {
      values.put(FOLLOW, follow);
      return this;
    }

    public Builder follower(String follower) {
      values.put(FOLLOWER, follower);
      return this;
    }

    public ContentValues build() {
      return values;
    }

  }
}

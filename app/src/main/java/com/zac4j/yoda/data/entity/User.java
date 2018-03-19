package com.zac4j.yoda.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Zac on 2018/1/10.
 */

// Create a table named users

@Entity(tableName = "users") public class User {
  @PrimaryKey public int id;
}

package com.zac4j.yoda.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;
import com.zac4j.yoda.data.model.EmotionEntry;

/**
 * Created by zac on 2018/5/21.
 * Description: Database for the app including a emotion table for Emotion with the DAO.
 */
@Database(entities = { EmotionEntry.class }, version = 1)
public abstract class EmotionDatabase extends RoomDatabase {

    private static final String TAG = EmotionDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "emotion";

    private static final Object LOCK = new Object();
    private static EmotionDatabase sIntance;

    private static EmotionDatabase getInstance(Context context) {
        Log.d(TAG, "Getting the database");
        if (sIntance == null) {
            synchronized (LOCK) {
                sIntance =
                    Room.databaseBuilder(context.getApplicationContext(), EmotionDatabase.class,
                        EmotionDatabase.DATABASE_NAME).build();
                Log.d(TAG, "Made new database");
            }
        }
        return sIntance;
    }

    // The associated DAOs for the database
    public abstract EmotionDao emotionDao();
}
